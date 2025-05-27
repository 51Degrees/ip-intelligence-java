param (
    [Parameter(Mandatory)][string]$RepoName,
    [string]$DataFile = "$RepoName/TAC-IpIntelligenceV41.ipi"
)
$ErrorActionPreference = "Stop"

./tools/ci/generate-accessors.ps1 @PSBoundParameters

Copy-Item "tools/Java/IPIDataBase.java" "ip-intelligence-java/ip-intelligence.shared/src/main/java/fiftyone/ipintelligence/shared/"
Copy-Item "tools/Java/IPIntelligenceData.java" "ip-intelligence-java/ip-intelligence.shared/src/main/java/fiftyone/ipintelligence/shared/"
