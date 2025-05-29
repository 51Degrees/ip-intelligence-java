param (
    [Parameter(Mandatory)][string]$RepoName,
    [Parameter(Mandatory)][string]$DeviceDetection,
    [string]$DeviceDetectionUrl
)

$cxxCiDir = "$RepoName/ip-intelligence.engine.on-premise/src/main/cxx/ip-intelligence-cxx"

& $cxxCiDir/ci/fetch-assets.ps1 `
    -RepoName $cxxCiDir `
    -DeviceDetection $DeviceDetection `
    -DeviceDetectionUrl $DeviceDetectionUrl

if ($LASTEXITCODE -ne 0) {
    Write-Warning "LASTEXITCODE = $LASTEXITCODE"
    exit $LASTEXITCODE
}

$CxxDataDir = "$cxxCiDir/ip-intelligence-data"
Write-Output "Entering $CxxDataDir"
Push-Location $CxxDataDir
try {
    & ./get-lite-file-from-azure.ps1 -Force
} finally {
    Write-Output "Leaving $CxxDataDir"
    Pop-Location
}

exit $LASTEXITCODE
