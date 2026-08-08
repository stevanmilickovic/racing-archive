# Racing Archive

Racing Archive is a web application for storing, managing, and displaying motorsport racing data and results.

The project is designed to support different championships, disciplines, organizations, seasons, events, competitors, vehicles, classes, and results.

## Tech Stack

* React
* Spring Boot
* MySQL
* Liquibase

## Project Structure

* `backend/` — Spring Boot REST API
* `frontend/` — React web application
* `docker-compose.yml` — local infrastructure

## Status

Early development.

## Initial administrator

The backend creates one administrator on its first successful startup. Set a strong password before starting the backend; the password is stored only as a BCrypt hash.

```powershell
$env:RACING_ARCHIVE_ADMIN_PASSWORD = "choose-a-strong-password"
docker compose up --build
```

The default username is `admin`. Set `RACING_ARCHIVE_ADMIN_USERNAME` before the first startup to use another username.

There is no public registration. The future `/admin/**` area, including result imports, requires the administrator role.
