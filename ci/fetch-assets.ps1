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

exit $LASTEXITCODE
