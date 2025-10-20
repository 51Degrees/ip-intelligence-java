param (
    [Parameter(Mandatory)][string]$RepoName,
    [string]$IpIntelligenceUrl
)

$cxxCiDir = "$RepoName/ip-intelligence.engine.on-premise/src/main/cxx/ip-intelligence-cxx"

# Skip fetching enterprise data file if URL not provided (e.g., in pull request builds)
# Unit tests can use cached or lite data files
if ($IpIntelligenceUrl) {
    # The C++ submodule's fetch-assets.ps1 still uses old parameter names for backwards compatibility
    # DeviceDetection parameter is not actually used, only the URL
    & $cxxCiDir/ci/fetch-assets.ps1 `
        -RepoName $cxxCiDir `
        -DeviceDetection "not-used" `
        -DeviceDetectionUrl $IpIntelligenceUrl

    if ($LASTEXITCODE -ne 0) {
        Write-Warning "LASTEXITCODE = $LASTEXITCODE"
        exit $LASTEXITCODE
    }
} else {
    Write-Output "IpIntelligenceUrl not provided - skipping enterprise data file fetch"
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
