[CmdletBinding()]
param(
    [Parameter(Mandatory = $true)]
    [string]$DockerHubUser,

    [string]$Tag = "latest"
)

Set-StrictMode -Version Latest
$ErrorActionPreference = "Stop"

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
$fullTag = "${dockerHubNamespace}/byebye-compose:${Tag}"

Write-Host "Publicando el archivo docker-compose.yml como OCI Artifact en Docker Hub..." -ForegroundColor Cyan

$scriptPath = if ($PSCommandPath) { $PSCommandPath } else { $MyInvocation.MyCommand.Path }
$scriptDir = Split-Path -Parent $scriptPath

Push-Location $scriptDir
try {
    Invoke-Docker -Arguments @("compose", "alpha", "publish", $fullTag)
}
finally {
    Pop-Location
}

Write-Host "¡Docker Compose publicado correctamente en: $fullTag!" -ForegroundColor Green