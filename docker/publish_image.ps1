[CmdletBinding()]
param(
    [Parameter(Mandatory = $true)]
    [string]$DockerHubUser,

    [string]$Tag = "latest",

    [switch]$SkipLogin
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

function Invoke-Docker {
    param([string[]]$Arguments)

    & docker @Arguments
    if ($LASTEXITCODE -ne 0) {
        throw "Docker command failed: docker $($Arguments -join ' ')"
    }
}

if ([string]::IsNullOrWhiteSpace($DockerHubUser)) {
    throw "DockerHubUser cannot be empty."
}

$dockerHubNamespace = $DockerHubUser.Trim().TrimEnd("/")
$scriptPath = if ($PSCommandPath) { $PSCommandPath } else { $MyInvocation.MyCommand.Path }
$scriptDir = Split-Path -Parent $scriptPath
$repoRoot = (Resolve-Path (Join-Path $scriptDir "..")).Path

$services = @(
    @{ Name = "app-service"; Dockerfile = "docker/app-service.Dockerfile" },
    @{ Name = "utility-service"; Dockerfile = "docker/utility-service.Dockerfile" }
)

Assert-DockerAvailable

if (-not $SkipLogin) {
    Write-Host "Logging in to DockerHub as $dockerHubNamespace..." -ForegroundColor Cyan
    Invoke-Docker -Arguments @("login", "--username", $dockerHubNamespace)
}

Push-Location $repoRoot
try {
    foreach ($service in $services) {
        $fullTag = "${dockerHubNamespace}/$($service.Name):${Tag}"

        Write-Host "Building $fullTag from $($service.Dockerfile)..." -ForegroundColor Cyan
        Invoke-Docker -Arguments @("build", "-f", $service.Dockerfile, "-t", $fullTag, ".")

        Write-Host "Publishing $fullTag..." -ForegroundColor Cyan
        Invoke-Docker -Arguments @("push", $fullTag)
    }
}
finally {
    Pop-Location
}

Write-Host "Docker images published successfully:" -ForegroundColor Green
foreach ($service in $services) {
    Write-Host " - ${dockerHubNamespace}/$($service.Name):${Tag}"
}
