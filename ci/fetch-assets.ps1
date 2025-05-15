
param (
    [string]$RepoName,
    [Parameter(Mandatory=$true)]
    [string]$DeviceDetection,
    [string]$DeviceDetectionUrl
)

$CxxCiDir = Join-Path $RepoName "ip-intelligence.engine.on-premise" "src" "main" "cxx" "ip-intelligence-cxx"
$CxxCiScript = Join-Path $pwd $CxxCiDir "ci" "fetch-assets.ps1"

& $CxxCiScript `
    -RepoName $CxxCiDir `
    -DeviceDetection $DeviceDetection `
    -DeviceDetectionUrl $DeviceDetectionUrl

exit $LASTEXITCODE
