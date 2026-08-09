# Session Results Import

The first import format is UTF-8 CSV. It imports the raw results for one existing race session.

## Required manual setup

Create these records through the administrator API before importing results:

1. Federation
2. Discipline
3. Championship
4. Season
5. Competition classes
6. Championship round
7. Race session within that round

All setup endpoints are under `/admin` and require the administrator login.

## Setup endpoints

Each resource supports `GET`, `POST`, `PUT /{id}`, and `DELETE /{id}`:

- `/admin/federations`
- `/admin/disciplines`
- `/admin/championships`
- `/admin/seasons`
- `/admin/competition-classes`
- `/admin/rounds`
- `/admin/sessions`

Requests and responses use JSON and return the IDs required by child records.

## CSV template

Download the header-only template from:

```text
GET /admin/import-templates/session-results
```

The columns must remain exactly in this order:

```text
driver_first_name,driver_last_name,club_name,vehicle_manufacturer,vehicle_model,vehicle_display_name,class_code,race_number,elapsed_time_millis,position,result_status
```

`class_code` must already exist. `elapsed_time_millis`, `position`, `race_number`, and `vehicle_display_name` may be empty where they do not apply. `result_status` is one of `FINISHED`, `DNF`, `DNS`, or `DSQ`.

Values containing commas must be wrapped in double quotes. A double quote inside a quoted value must be written twice.

## Import endpoint

Upload the CSV as multipart form field `file`:

```text
POST /admin/sessions/{sessionId}/results/import
```

For each row, the importer:

1. Finds the global competition class.
2. Reuses a driver with the same first and last name; otherwise creates one.
3. Rejects the complete import if multiple existing driver records have that same name, including records with different nationalities.
4. Reuses a club with the same name; otherwise creates one.
5. Reuses a vehicle with the same manufacturer, model, and display name; otherwise creates one.
6. Reuses or creates the driver's round entry, then creates or updates the raw result for that session.

The import runs as one transaction. A validation error rejects the complete file, so no partial results are saved.

Name-based matching is an intentional first version. Duplicate driver names must be resolved before import. A stable source identifier can be added later if the archive requires distinct drivers with the same name.
