param(
    [Parameter(Mandatory)][string]$RepoName,
    [Parameter(Mandatory)][string]$Version,
    [Parameter(Mandatory)][hashtable]$Keys
)

Write-Error "Disabled till ready to release."
exit 1

# ./java/publish-package-maven.ps1 -RepoName $RepoName -MavenSettings $Keys['MavenSettings'] -Version $Version


exit $LASTEXITCODE
