@echo off
setlocal
pushd "%~dp0"
if errorlevel 1 exit /b 1

where docker >nul 2>&1
if errorlevel 1 (
    echo Docker is required. Install Docker Desktop and try again.
    goto failed
)
where node >nul 2>&1
if errorlevel 1 (
    echo Node.js is required. Install Node.js and try again.
    goto failed
)
where npm.cmd >nul 2>&1
if errorlevel 1 (
    echo npm is required. Install Node.js with npm and try again.
    goto failed
)
docker info >nul 2>&1
if errorlevel 1 (
    echo Docker is not available. Start Docker Desktop and try again.
    goto failed
)

echo Installing frontend dependencies and rebuilding...
pushd frontend
if errorlevel 1 goto failed
call npm.cmd ci
if errorlevel 1 goto frontendFailed
call npm.cmd run build
if errorlevel 1 goto frontendFailed
popd

echo Rebuilding and starting MySQL and the backend...
docker compose up --detach --build --wait --wait-timeout 180
if errorlevel 1 goto backendFailed

echo Waiting for the backend API...
powershell.exe -NoProfile -Command "$startupDeadline = (Get-Date).AddSeconds(120); while ((Get-Date) -lt $startupDeadline) { try { $startupResponse = Invoke-WebRequest -Uri 'http://localhost:8080/public/dashboard' -UseBasicParsing -TimeoutSec 3; if ($startupResponse.StatusCode -eq 200) { exit 0 } } catch {}; Start-Sleep -Seconds 2 }; Write-Error 'The backend API did not become ready within 120 seconds.'; exit 1"
if errorlevel 1 goto backendFailed

echo.
echo Racing Archive: http://localhost:5173
echo Keep this terminal open. Ctrl+C stops the frontend.
echo MySQL and the backend stay running in Docker.
echo To stop those later, run: docker compose stop
echo.
pushd frontend
if errorlevel 1 goto failed
call npm.cmd run dev -- --host 127.0.0.1 --port 5173 --strictPort
set "RACING_START_EXIT=%ERRORLEVEL%"
popd
popd
exit /b %RACING_START_EXIT%

:frontendFailed
popd
echo Frontend build failed. See the error above.
goto failed

:backendFailed
echo Backend startup failed. Recent container logs:
docker compose logs --tail 60 backend mysql

:failed
popd
exit /b 1
