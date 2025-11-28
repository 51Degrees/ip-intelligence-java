param(
    [Parameter(Mandatory)][string]$RepoName,
    [string]$Name
)

./java/install-package.ps1 @PSBoundParameters

if ($IsMacOS) {
    $mvnLocalRepo = mvn help:evaluate -Dexpression="settings.localRepository" -q -DforceStdout
    Write-Host "Fixing permissions on native libraries..."

    # Remove quarantine attribute from all 51degrees files
    xattr -rd com.apple.quarantine "$mvnLocalRepo/com/51degrees" 2>$null

    # Ensure native libraries have correct permissions
    Get-ChildItem -Recurse "$mvnLocalRepo/com/51degrees" -Filter "*.dylib" | ForEach-Object {
        chmod +x $_.FullName
        Write-Host "Fixed permissions on: $($_.FullName)"
    }

    Write-Host "Permissions fixed."
}
