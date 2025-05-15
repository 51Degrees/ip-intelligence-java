
param(
    [Parameter(Mandatory=$true)]
    [Hashtable]$Keys,
    [Parameter(Mandatory=$true)]
    $Version,
    [Parameter(Mandatory=$true)]
    [string]$RepoName
    

)

Write-Error "Disabled till ready to release."
exit 1

# ./java/publish-package-maven.ps1 -RepoName $RepoName -MavenSettings $Keys['MavenSettings'] -Version $Version


exit $LASTEXITCODE
