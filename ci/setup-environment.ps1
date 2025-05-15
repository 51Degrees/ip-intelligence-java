param(
    [Parameter(Mandatory=$true)]
    [string]$RepoName,
    [Parameter(Mandatory=$true)]
    [string]$JavaSDKEnvVar,
    [string]$ProjectDir = "."
)

./java/setup-enviroment.ps1 -RepoName $RepoName -ProjectDir $ProjectDir -JavaSDKEnvVar $JavaSDKEnvVar

if ($LASTEXITCODE -ne 0) {
    exit $LASTEXITCODE
}

if ($IsLinux -or $IsMacOS) {
    Write-Output "Marking PreBuild.sh as executable..."
    chmod +x ip-intelligence.engine.on-premise/src/main/cxx/PreBuild.sh
    Write-Output "Marking PreBuild.sh as executable -- done."
}

exit $LASTEXITCODE
