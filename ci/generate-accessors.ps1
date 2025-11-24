param (
    [Parameter(Mandatory)][string]$RepoName,
    [string]$MetaDataPath = "$PWD/common-metadata",
    [string]$DataType = "IpIntelligence"
)
$ErrorActionPreference = "Stop"

./tools/ci/generate-accessors.ps1 -RepoName:$RepoName -MetaDataPath:$MetaDataPath -DataType:$DataType

Copy-Item "tools/Java/IPIntelligenceDataBase.java" "ip-intelligence-java/ip-intelligence.shared/src/main/java/fiftyone/ipintelligence/shared/"
Copy-Item "tools/Java/IPIntelligenceData.java" "ip-intelligence-java/ip-intelligence.shared/src/main/java/fiftyone/ipintelligence/shared/"
