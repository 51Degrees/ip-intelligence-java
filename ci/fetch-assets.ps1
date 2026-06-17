param (
    [string]$IpIntelligenceUrl
)
$ErrorActionPreference = "Stop"

$ipIntelligenceData = "$PSSCriptRoot/../ip-intelligence.engine.on-premise/src/main/cxx/ip-intelligence-cxx/ip-intelligence-data"

./steps/fetch-assets.ps1 -IpIntelligenceUrl $IpIntelligenceUrl -Assets '51Degrees-EnterpriseIpiV41.ipi', '51Degrees-LiteIpiV41.ipi'
New-Item -ItemType SymbolicLink -Force -Target "$PWD/assets/51Degrees-EnterpriseIpiV41.ipi" -Path "$ipIntelligenceData/51Degrees-EnterpriseIpiV41.ipi"
# The asset is fetched under the blob name, but FileUtils and the data repo
# scripts use the unpacked name 51Degrees-LiteV41.ipi, so link it as that.
New-Item -ItemType SymbolicLink -Force -Target "$PWD/assets/51Degrees-LiteIpiV41.ipi" -Path "$ipIntelligenceData/51Degrees-LiteV41.ipi"

Write-Host "Assets hashes:"
Get-FileHash -Algorithm MD5 -Path assets/*

Push-Location $ipIntelligenceData
try {
    ./evidence-gen.ps1 -v4 10000 -v6 10000
    ./evidence-gen.ps1 -v4 10000 -v6 10000 -csv -path "evidence.csv"
} finally {
    Pop-Location
}
