import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/admin': 'http://localhost:8080',
      '/csrf': 'http://localhost:8080',
      '/login': {
        target: 'http://localhost:8080',
        configure: (proxy) => {
          proxy.on('proxyRes', (response) => {
            const location = response.headers.location
            if (location?.startsWith('http://localhost:8080/')) {
              response.headers.location = location.replace('http://localhost:8080/', '/')
            }
          })
        },
      },
    },
  },
})
