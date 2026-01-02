# Start script for 05-mcp module (SupervisorAgentDemo)
# Loads environment variables from parent .env file

$ErrorActionPreference = "Stop"

$ScriptDir = $PSScriptRoot
$RootDir = Split-Path $ScriptDir -Parent
$EnvFile = Join-Path $RootDir ".env"

# Change to module directory
Set-Location $ScriptDir

# Check if .env exists
if (-not (Test-Path $EnvFile)) {
    Write-Error ".env file not found at $EnvFile. Please copy .env.example to .env in the root directory"
    exit 1
}

# Load environment variables
Write-Host "Loading environment variables from $EnvFile"
Get-Content $EnvFile | ForEach-Object {
    if ($_ -match '^([^=]+)=(.*)$') {
        [Environment]::SetEnvironmentVariable($matches[1], $matches[2], 'Process')
    }
}

# Verify required variables
$endpoint = [Environment]::GetEnvironmentVariable('AZURE_OPENAI_ENDPOINT', 'Process')
$apiKey = [Environment]::GetEnvironmentVariable('AZURE_OPENAI_API_KEY', 'Process')
$deployment = [Environment]::GetEnvironmentVariable('AZURE_OPENAI_DEPLOYMENT', 'Process')

if (-not $endpoint -or -not $apiKey -or -not $deployment) {
    Write-Error "Missing required environment variables (AZURE_OPENAI_ENDPOINT, AZURE_OPENAI_API_KEY, AZURE_OPENAI_DEPLOYMENT)"
    exit 1
}

Write-Host ""
Write-Host "Running SupervisorAgentDemo..."
Write-Host ""

mvn compile exec:java "-Dexec.mainClass=com.example.langchain4j.mcp.SupervisorAgentDemo" -q

if ($LASTEXITCODE -ne 0) {
    Write-Error "Demo failed"
    exit 1
}
