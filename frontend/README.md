# Racing Archive frontend

Read-only React + TypeScript + Vite interface for seasons, events, sessions,
drivers and downloadable event source documents.

Run `npm.cmd ci`, then `npm.cmd run dev`. The development server proxies
`/public` requests to the backend at `http://localhost:8080`.

Verify with `npm.cmd run build` and `npm.cmd run lint`.

Archive content is maintained through migrations. See the
[project README](../README.md) and [event curation workflow](../docs/curating-events.md).
