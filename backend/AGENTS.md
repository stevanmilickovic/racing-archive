# AGENTS.md

## Project

Racing Archive is a backend service for storing and serving motorsport archive data, including championships, events, competitors, vehicles, classes, and results.

The initial use case is Montenegrin hillclimb racing, but the domain model should remain generic enough to support other countries, federations, championships, and motorsport disciplines later.

## Stack

* Java 21
* Spring Boot
* Maven
* Spring Data JPA / Hibernate
* MySQL
* Liquibase
* Lombok

## Principles

Simplicity is the highest priority.

Prefer code that is:

* clear
* readable
* explicit
* small
* well separated
* easy to modify

Prefer straightforward implementations over clever, condensed, highly abstract, or prematurely optimized solutions.

Do not introduce abstractions, patterns, dependencies, or infrastructure unless they solve a concrete current problem.

Keep responsibilities separated between controllers, services, repositories, persistence entities, DTOs, and mapping code.

Avoid large classes and methods. Split functionality when responsibilities become unclear.

## Database

Liquibase owns the database schema.

All schema changes must be introduced through versioned Liquibase migrations.

Do not rely on Hibernate to create or modify the schema automatically.

Design the data model around the domain rather than around individual frontend screens or current race data.

## API

Keep REST APIs predictable and consistent.

Do not expose persistence entities directly through the API.

Prefer explicit DTOs for requests and responses.

## Changes

Keep changes focused on the requested task.

Do not perform unrelated refactors or rename existing structures without a clear reason.

Follow existing project conventions before introducing new ones.
