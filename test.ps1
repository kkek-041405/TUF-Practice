<#
.SYNOPSIS
    Runs TUF problem test cases in Java.
.EXAMPLE
    .\test.ps1 problems/01-arrays/two-sum
    .\test.ps1 two-sum
    .\test.ps1
#>
param (
    [string]$Problem = ""
)

$ErrorActionPreference = "Stop"

# Ensure we are in repo root
$RepoRoot = $PSScriptRoot
Set-Location $RepoRoot

# Find target problem folder
$TargetFolder = $null

if ($Problem) {
    if (Test-Path $Problem) {
        $TargetFolder = (Resolve-Path $Problem).Path
    } else {
        # Search inside problems/
        $match = Get-ChildItem -Path "$RepoRoot\problems" -Directory -Recurse | Where-Object { $_.Name -like "*$Problem*" } | Select-Object -First 1
        if ($match) {
            $TargetFolder = $match.FullName
        }
    }
} else {
    # Find the most recently modified problem folder containing Solution.java
    $solutions = Get-ChildItem -Path "$RepoRoot\problems" -Filter "Solution.java" -Recurse | Sort-Object LastWriteTime -Descending
    if ($solutions) {
        $TargetFolder = $solutions[0].DirectoryName
    }
}

if (-not $TargetFolder -or -not (Test-Path $TargetFolder)) {
    Write-Host "[ERROR] Could not find problem directory: '$Problem'" -ForegroundColor Red
    Write-Host "Usage: .\test.ps1 <problem-slug-or-path>" -ForegroundColor Yellow
    exit 1
}

$ProblemName = (Get-Item $TargetFolder).Name
Write-Host "Target Problem: $ProblemName" -ForegroundColor Cyan
Write-Host "Directory:      $TargetFolder" -ForegroundColor Gray

# Paths
$BinDir = "$RepoRoot\.bin"
if (-not (Test-Path $BinDir)) {
    New-Item -ItemType Directory -Path $BinDir -Force | Out-Null
}

$RunnerSrc = Get-ChildItem -Path "$RepoRoot\.runner" -Filter "*.java" | ForEach-Object { $_.FullName }
$SolutionSrc = "$TargetFolder\Solution.java"
$TestRunnerSrc = "$TargetFolder\TestRunner.java"

if (-not (Test-Path $SolutionSrc)) {
    Write-Host "[ERROR] Solution.java not found in $TargetFolder" -ForegroundColor Red
    exit 1
}

if (-not (Test-Path $TestRunnerSrc)) {
    Write-Host "[ERROR] TestRunner.java not found in $TargetFolder" -ForegroundColor Red
    exit 1
}

# 1. Compile .runner classes
Write-Host "[1/2] Compiling runner & solution..." -ForegroundColor DarkGray
$AllSources = @()
$AllSources += $RunnerSrc
$AllSources += $SolutionSrc
$AllSources += $TestRunnerSrc

$compileProcess = Start-Process -FilePath "javac" -ArgumentList (@("-encoding", "UTF-8", "-d", $BinDir) + $AllSources) -NoNewWindow -Wait -PassThru

if ($compileProcess.ExitCode -ne 0) {
    Write-Host "`n[COMPILATION FAILED]" -ForegroundColor Red -BackgroundColor DarkRed
    exit 1
}

# 2. Run TestRunner
Write-Host "[2/2] Running Test Cases...`n" -ForegroundColor DarkGray

$runProcess = Start-Process -FilePath "java" -ArgumentList @("-cp", "$BinDir;$TargetFolder", "TestRunner") -NoNewWindow -Wait -PassThru

if ($runProcess.ExitCode -ne 0) {
    exit $runProcess.ExitCode
}
