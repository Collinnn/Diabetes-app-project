import { createApp } from 'vue'
import axios from 'axios'
import App from './App.vue'
import Backend from './services/backend'
import router from './services/router'

const app = createApp(App)
app.use(router)
app.config.globalProperties.axios = axios
app.config.globalProperties.$backend = new Backend()

app.mount('#app')