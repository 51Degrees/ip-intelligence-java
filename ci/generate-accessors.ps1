param (
    [Parameter(Mandatory)][string]$RepoName,
    [string]$DataFile = "$PWD/$RepoName/51Degrees-EnterpriseV4.ipi"
)
$ErrorActionPreference = "Stop"

./tools/ci/generate-accessors.ps1 @PSBoundParameters

Copy-Item "tools/Java/IPIntelligenceDataBase.java" "ip-intelligence-java/ip-intelligence.shared/src/main/java/fiftyone/ipintelligence/shared/"
Copy-Item "tools/Java/IPIntelligenceData.java" "ip-intelligence-java/ip-intelligence.shared/src/main/java/fiftyone/ipintelligence/shared/"
