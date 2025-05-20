
param(
    [string]$Name = "Windows_Java_8",
    [string]$Version = "0.0.0",
    # Keys contain the License and Resource Keys needed to run the tests.
    [Parameter(Mandatory=$true)]
    [Hashtable]$Keys,
    [Parameter(Mandatory=$true)]
    [string]$RepoName,
    [string]$OrgName

    
)

$RepoPath = [IO.Path]::Combine($pwd, $RepoName)
$ExamplesRepoName = "$RepoName-examples"

try {
    Write-Output "Cloning '$ExamplesRepoName'"
    ./steps/clone-repo.ps1 -RepoName "ip-intelligence-java-examples" -OrgName $OrgName
    
    Write-Output "Moving TAC file for examples"
    $TacFile = [IO.Path]::Combine($RepoPath, "TAC-HashV41.hash") 
    Copy-Item $TacFile ip-intelligence-java-examples/ip-intelligence-data/TAC-HashV41.hash

    Write-Output "Moving evidence files for examples"
    $UAFile = [IO.Path]::Combine($RepoPath, "20000 User Agents.csv") 
    $EvidenceFile = [IO.Path]::Combine($RepoPath, "evidence.yml")
    Copy-Item $UAFile "ip-intelligence-java-examples/ip-intelligence-data/20000 User Agents.csv"
    Copy-Item $EvidenceFile "ip-intelligence-java-examples/ip-intelligence-data/evidence.yml"
    
    Write-Output "Entering ip-intelligence-java directory"
    Push-Location $RepoPath
    # If the Version parameter is set to "0.0.0", set the Version variable to the version specified in the pom.xml file
    if ($Version -eq "0.0.0"){
        $Version = mvn org.apache.maven.plugins:maven-help-plugin:3.1.0:evaluate -Dexpression="project.version" -q -DforceStdout
    }

    Pop-Location

    Write-Output "Entering ip-intelligence-examples directory"
    Push-Location $ExamplesRepoName


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

    Write-Output "Leaving '$ExamplesRepoName'"
    Pop-Location

}

exit $LASTEXITCODE
