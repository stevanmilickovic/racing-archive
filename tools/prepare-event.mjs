import { mkdir, readFile, writeFile } from 'node:fs/promises'
import { existsSync } from 'node:fs'
import { dirname, join, resolve } from 'node:path'
import { fileURLToPath, pathToFileURL } from 'node:url'

const repository = resolve(dirname(fileURLToPath(import.meta.url)), '..')

function date(value, field) {
  if (!/^\d{4}-\d{2}-\d{2}$/.test(value ?? '')
      || Number.isNaN(Date.parse(value))
      || new Date(value).toISOString().slice(0, 10) !== value) {
    throw new Error(field + ' must be a valid YYYY-MM-DD date.')
  }
  return value
}

export async function prepareEvent({ name, startsOn, endsOn, slug, dryRun = false }, root = repository) {
  if (!name?.trim()) throw new Error('An event name is required.')
  startsOn = date(startsOn, 'starts-on')
  endsOn = date(endsOn ?? startsOn, 'ends-on')
  if (endsOn < startsOn) throw new Error('ends-on must be on or after starts-on.')
  slug ??= name.normalize('NFD').replace(/\p{M}/gu, '').replace(/[đĐ]/g, 'd')
    .toLowerCase().replace(/[^a-z0-9]+/g, '-').replace(/^-|-$/g, '')
  if (!/^[a-z0-9]+(-[a-z0-9]+)*$/.test(slug)) {
    throw new Error('Supply --slug with lowercase ASCII letters, numbers and single hyphens.')
  }
  const eventKey = startsOn + '-' + slug
  const yearPath = join(root, 'archive', 'events', startsOn.slice(0, 4))
  const eventPath = join(yearPath, eventKey)
  if (existsSync(eventPath)) throw new Error('Event folder already exists: ' + eventPath)
  if (dryRun) return eventPath
  const review = await readFile(join(repository, 'archive', 'templates', 'review.md'))
  await mkdir(yearPath, { recursive: true })
  await mkdir(eventPath)
  await mkdir(join(eventPath, 'files'))
  const manifest = {
    eventKey, name: name.trim(), startsOn, endsOn, status: 'awaiting-sources',
    championshipSeasons: [], trackKey: null, documents: [], migrations: [],
  }
  await writeFile(join(eventPath, 'event.json'), JSON.stringify(manifest, null, 2) + '\n', { flag: 'wx' })
  await writeFile(join(eventPath, 'review.md'), review, { flag: 'wx' })
  await writeFile(join(eventPath, 'files', '.gitkeep'), '', { flag: 'wx' })
  return eventPath
}

if (process.argv[1] && pathToFileURL(resolve(process.argv[1])).href === import.meta.url) {
  try {
    const options = {}
    const keys = { '--name': 'name', '--starts-on': 'startsOn', '--ends-on': 'endsOn', '--slug': 'slug' }
    const arguments_ = process.argv.slice(2)
    for (let index = 0; index < arguments_.length; index++) {
      const argument = arguments_[index]
      if (argument === '--dry-run') options.dryRun = true
      else if (keys[argument] && arguments_[index + 1] && !arguments_[index + 1].startsWith('--')) {
        options[keys[argument]] = arguments_[++index]
      } else throw new Error('Unknown option or missing value: ' + argument)
    }
    const eventPath = await prepareEvent(options)
    console.log((options.dryRun ? 'Would prepare: ' : 'Copy source documents into: ') + join(eventPath, 'files'))
  } catch (error) {
    console.error(error.message)
    process.exitCode = 1
  }
}
