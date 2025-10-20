param(
    [Parameter(Mandatory)][string]$RepoName,
    [Parameter(Mandatory)][string]$OrgName,
    [string]$IpIntelligenceUrl,
    [string]$Name,
    [string]$Version,
    [string]$Branch = "main", # this is actually the examples branch, but the name has to just be 'Branch' to be recognized by run-repo-script.ps1
    [string]$ExamplesRepo = "$RepoName-examples"
)
$ErrorActionPreference = "Stop"
$PSNativeCommandUseErrorActionPreference = $true

# Skip performance tests if IpIntelligenceUrl is not provided
if (!$IpIntelligenceUrl) {
    Write-Output "Skipping performance tests - IpIntelligenceUrl not provided"
    exit 0
}

if (Test-Path $ExamplesRepo) {
    Write-Host "Examples already cloned, skipping"
} else {
    Write-Host "Cloning '$ExamplesRepo'"
    ./steps/clone-repo.ps1 -RepoName $ExamplesRepo -OrgName $OrgName -Branch $Branch
    & "./$ExamplesRepo/ci/fetch-assets.ps1" -RepoName $ExamplesRepo -IpIntelligenceUrl $IpIntelligenceUrl
}

if (!$Version) {
    $Version = mvn -f $RepoName/pom.xml org.apache.maven.plugins:maven-help-plugin:3.1.0:evaluate -Dexpression="project.version" -q -DforceStdout
}

$failed = $false
# Enter the Java project examples directory
Write-Host "Entering '$ExamplesRepo'"
Push-Location $ExamplesRepo
try {
    Write-Host "Setting examples ip-intelligence package dependency to version '$Version'"
    mvn --batch-mode --no-transfer-progress versions:set-property "-Dproperty=ip-intelligence.version" "-DnewVersion=$Version"

    Write-Host "Testing performance"
    & {
        $ErrorActionPreference = "Continue"
        mvn --batch-mode --no-transfer-progress clean test "-DfailIfNoTests=false" "-Dtest=*Performance*"
    }
    if ($LASTEXITCODE -ne 0) {
        Write-Warning "Tests failed"
        $failed = $true
    }

    Write-Host "Copying test results"
    $destDir = New-Item -ItemType directory -Force -Path "../$RepoName/test-results/performance"
    Get-ChildItem -File -Depth 1 -Filter 'pom.xml' | ForEach-Object {
        $targetDir = "$($_.DirectoryName)/target/surefire-reports"
        if (Test-Path $targetDir) {
            Copy-Item -Filter "*Performance*" $targetDir/* $destDir
        }
    }
    # Copy the test results into the test-results/performance-summary folder for performance comparison 
    Copy-Item -Recurse $destDir "../$RepoName/test-results/performance-summary"
} finally {
    Write-Host "Leaving '$ExamplesRepo'"
    Pop-Location
}

# Initilise variables for storing data
$profileNum = 0
$profiles = @{}
$currentThreadNum = 1

# Read the content of the performance results file line by line
Get-Content "$RepoName/test-results/performance-summary/fiftyone.ipintelligence.examples.console.PerformanceBenchmarkTest-output.txt" | ForEach-Object {
     # If the line matches a profile header, extract the profile properties and create a new profile.
    if($_ -match "MaxPerformance AllProperties: (true|false), performanceGraph: (true|false), predictiveGraph (true|false)") {
        $allProperties = [string]$Matches[1]
        $performanceGraph = [string]$Matches[2]
        $predictiveGraph = [string]$Matches[3]

        $profileNum++
        $currentProfile = $profiles["MaxPerformance-$allProperties-$performanceGraph-$predictiveGraph"] = @{ "Threads" = @{} }
        $currentThreadNum = 1
    }
    # If the line matches a thread data line, extract the thread data and add it to the current profile.
    elseif($_ -match "Thread:  ([\d,]+) detections, elapsed ([\d.]+) seconds, ([\d,]+) Detections per second") {
        $currentProfile["Threads"]["Thread_$currentThreadNum"] = @{
            "Detections" = [int]($Matches[1].Replace(",", ""))
            "ElapsedSeconds" = [double]$Matches[2]
            "DetectionsPerSecond" = [int]($Matches[3].Replace(",", ""))
        }
        $currentThreadNum++
    }
    # If the line matches an overall data line, extract the overall data and add it to the current profile.
    elseif($_ -match "Overall: ([\d,]+) detections, Average millisecs per detection: ([\d.]+), Detections per second: ([\d,]+)") {
        $currentProfile["Overall"] = @{
            "Detections" = [int]($Matches[1].Replace(",", ""))
            "AvgMillisecsPerDetection" = [double]$Matches[2]
            "DetectionsPerSecond" = [int]($Matches[3].Replace(",", ""))
        }
    }
}

# Create a JSON object with specific performance metrics and write it to the output file.
Write-Host $profiles
Write-Output "{
    'HigherIsBetter': {
        'DetectionsPerSecond': $($profiles['MaxPerformance-false-true-false'].Overall.DetectionsPerSecond)
    },
    'LowerIsBetter': {
        'AvgMillisecsPerDetection' : $($profiles['MaxPerformance-false-true-false'].Overall.AvgMillisecsPerDetection)
    }
}" > "$RepoName/test-results/performance-summary/results_$Name.json"

exit $failed ? 1 : 0
