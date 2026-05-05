[CmdletBinding()]
param(
    [string]$Tag = "latest",
    [string]$ImagePrefix = ""
)

Set-StrictMode -Version Latest
$ErrorActionPreference = "Stop"

function Assert-DockerAvailable {
    if (-not (Get-Command docker -ErrorAction SilentlyContinue)) {
        throw "Docker is not installed or is not available in PATH."
    }

    & docker info *> $null
    if ($LASTEXITCODE -ne 0) {
        throw "Docker is installed, but the Docker daemon is not running or is not reachable."
    }
}

function Get-ImageName {
    param([string]$ServiceName)

    if ([string]::IsNullOrWhiteSpace($ImagePrefix)) {
        return $ServiceName
    }

    $prefix = $ImagePrefix.TrimEnd("/")
    return "$prefix/$ServiceName"
}

function Invoke-Docker {
    param([string[]]$Arguments)

    & docker @Arguments
    if ($LASTEXITCODE -ne 0) {
        throw "Docker command failed: docker $($Arguments -join ' ')"
    }
}

$scriptPath = if ($PSCommandPath) { $PSCommandPath } else { $MyInvocation.MyCommand.Path }
$scriptDir = Split-Path -Parent $scriptPath
$repoRoot = (Resolve-Path (Join-Path $scriptDir "..")).Path

$services = @(
    @{ Name = "app-service"; Dockerfile = "docker/app-service.Dockerfile" },
    @{ Name = "utility-service"; Dockerfile = "docker/utility-service.Dockerfile" }
)

Assert-DockerAvailable

Push-Location $repoRoot
try {
    foreach ($service in $services) {
        $imageName = Get-ImageName -ServiceName $service.Name
        $fullTag = "${imageName}:${Tag}"

        Write-Host "Building $fullTag from $($service.Dockerfile)..." -ForegroundColor Cyan
        Invoke-Docker -Arguments @("build", "-f", $service.Dockerfile, "-t", $fullTag, ".")
    }
}
finally {
    Pop-Location
}

Write-Host "Docker images created successfully:" -ForegroundColor Green
foreach ($service in $services) {
    $imageName = Get-ImageName -ServiceName $service.Name
    Write-Host " - ${imageName}:${Tag}"
}
