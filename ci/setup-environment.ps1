param(
    [Parameter(Mandatory)][string]$RepoName,
    [Parameter(Mandatory)][string]$JavaSDKEnvVar
)
$ErrorActionPreference = "Stop"
$PSNativeCommandUseErrorActionPreference = $true

./java/setup-enviroment.ps1 @PSBoundParameters

if ($IsLinux -or $IsMacOS) {
    Write-Host "Marking PreBuild.sh as executable..."
    chmod +x "$RepoName/ip-intelligence.engine.on-premise/src/main/cxx/PreBuild.sh"
    Write-Host "Marking PreBuild.sh as executable -- done."
}
