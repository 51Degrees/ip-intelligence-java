param(
    [string]$ProjectDir = ".",
    [string]$Name,
    [hashtable]$Keys,
    [Parameter(Mandatory=$true)]
    [string]$RepoName
)

& ./java/run-unit-tests.ps1 -RepoName $RepoName -ProjectDir $ProjectDir -Name $Name -ExtraArgs @(
    "-DTestResourceKey=$($Keys.TestResourceKey)"
)
Write-Output "[ip-intelligence-java/ci/run-unit-tests.ps1] LASTEXITCODE = $LASTEXITCODE"

exit $LASTEXITCODE
