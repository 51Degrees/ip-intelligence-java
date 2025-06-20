param(
    [Parameter(Mandatory)][string]$RepoName,
    [Parameter(Mandatory)][string]$OrgName,
    [Parameter(Mandatory)][hashtable]$Keys,
    [Parameter(Mandatory)][string]$DeviceDetection,
    [Parameter(Mandatory)][string]$DeviceDetectionUrl,
    [string]$Name,
    [string]$Version,
    [string]$Branch = "main", # this is actually the examples branch, but the name has to just be 'Branch' to be recognized by run-repo-script.ps1
    [string]$ExamplesRepo = "$RepoName-examples",
    [string]$ExamplesBranch = $Branch
)

$RepoPath = [IO.Path]::Combine($pwd, $RepoName)

try {
    Write-Output "Cloning '$ExamplesRepo'"
    ./steps/clone-repo.ps1 -RepoName $ExamplesRepo -OrgName $OrgName -Branch $ExamplesBranch
    & "./$ExamplesRepo/ci/fetch-assets.ps1" -RepoName $ExamplesRepo -DeviceDetection $DeviceDetection -DeviceDetectionUrl $DeviceDetectionUrl
    
    Write-Output "Entering ip-intelligence-java directory"
    Push-Location $RepoPath
    # If the Version parameter is set to "0.0.0", set the Version variable to the version specified in the pom.xml file
    if (!$Version){
        $Version = mvn org.apache.maven.plugins:maven-help-plugin:3.1.0:evaluate -Dexpression="project.version" -q -DforceStdout
    }

    Pop-Location

    Write-Output "Entering ip-intelligence-examples directory"
    Push-Location $ExamplesRepo


    Write-Output "Setting examples ip-intelligence package dependency to version '$Version'"
    mvn versions:set-property -Dproperty="ip-intelligence.version" "-DnewVersion=$Version"

    Write-Output "Testing Examples"
    mvn clean test "-DTestResourceKey=$($Keys.TestResourceKey)" "-DSuperResourceKey=$($Keys.TestResourceKey)" "-DLicenseKey=$($Keys.IPIntelligence)"

    Write-Output "Copying test results".
    # Copy the test results into the test-results folder
    Get-ChildItem -Path . -Directory -Depth 1 | 
    Where-Object { Test-Path "$($_.FullName)\pom.xml" } | 

    ForEach-Object { 
        $targetDir = "$($_.FullName)\target\surefire-reports"
        $destDir = "..\$RepoName\test-results\integration"
        if(!(Test-Path $destDir)) { New-Item -ItemType Directory -Path $destDir }
        if(Test-Path $targetDir) {
            Get-ChildItem -Path $targetDir |
            ForEach-Object {
                Copy-Item -Path $_.FullName -Destination $destDir
            }
        }
    }
}

finally {

    Write-Output "Leaving '$ExamplesRepo'"
    Pop-Location

}

exit $LASTEXITCODE
