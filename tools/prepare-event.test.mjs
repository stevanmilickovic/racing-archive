import test from 'node:test'
import assert from 'node:assert/strict'
import { mkdtemp, readFile, readdir, rm } from 'node:fs/promises'
import { join } from 'node:path'
import { tmpdir } from 'node:os'
import { prepareEvent } from './prepare-event.mjs'

test('prepares originals and review folders without overwriting an existing event', async (t) => {
  const root = await mkdtemp(join(tmpdir(), 'racing-archive-event-'))
  t.after(() => rm(root, { recursive: true, force: true }))
  const options = { name: 'Žabljak', startsOn: '2026-05-16', endsOn: '2026-05-17' }
  const folder = await prepareEvent(options, root)
  assert.ok(folder.endsWith('2026-05-16-zabljak'))
  const manifest = JSON.parse(await readFile(join(folder, 'event.json'), 'utf8'))
  assert.equal(manifest.name, 'Žabljak')
  assert.equal(manifest.endsOn, '2026-05-17')
  assert.equal(manifest.status, 'awaiting-sources')
  assert.deepEqual(manifest.documents, [])
  assert.match(await readFile(join(folder, 'review.md'), 'utf8'), /programmer verification required/)
  assert.deepEqual(await readdir(join(folder, 'files')), ['.gitkeep'])
  await assert.rejects(prepareEvent(options, root), /already exists/)
})

test('validates dates and slugs and dry-run creates no files', async (t) => {
  const root = await mkdtemp(join(tmpdir(), 'racing-archive-event-'))
  t.after(() => rm(root, { recursive: true, force: true }))
  const options = { name: 'Event', startsOn: '2026-05-16' }
  await assert.rejects(prepareEvent({ ...options, startsOn: '2026-02-30' }, root), /valid YYYY-MM-DD/)
  await assert.rejects(prepareEvent({ ...options, endsOn: '2026-05-15' }, root), /on or after/)
  await assert.rejects(prepareEvent({ ...options, slug: '../outside' }, root), /Supply --slug/)
  await prepareEvent({ ...options, dryRun: true }, root)
  assert.deepEqual(await readdir(root), [])
})
