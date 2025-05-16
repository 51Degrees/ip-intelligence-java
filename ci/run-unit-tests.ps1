param(
    [string]$ProjectDir = ".",
    [string]$Name,
    [hashtable]$Keys,
    [Parameter(Mandatory=$true)]
    [string]$RepoName
)

./java/run-unit-tests.ps1 -RepoName $RepoName -ProjectDir $ProjectDir -Name $Name -ExtraArgs @(
    "-X",
    "-e",
    "-Djunit.jupiter.extensions.autodetection.enabled=true",
    "-DTestResourceKey=$($Keys.TestResourceKey)"
)

exit $LASTEXITCODE
