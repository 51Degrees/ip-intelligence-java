param (
    [Parameter(Mandatory)][string]$RepoName,
    [string]$DataFile = "$RepoName/TAC-HashV41.hash"
)
$ErrorActionPreference = "Stop"

./tools/ci/generate-accessors.ps1 @PSBoundParameters

Copy-Item "tools/Java/IPIDataBase.java" "ip-intelligence-java/ip-intelligence.shared/src/main/java/fiftyone/ipintelligence/shared/"
Copy-Item "tools/Java/IPIData.java" "ip-intelligence-java/ip-intelligence.shared/src/main/java/fiftyone/ipintelligence/shared/"
