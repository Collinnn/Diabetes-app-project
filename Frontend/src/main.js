import {createApp} from 'vue'
import axios from 'axios'
import App from './App.vue'
import Backend from './services/backend'
import router from './router'
import {requirement, required} from './services/directives'

const app = createApp(App)
app.use(router)
app.config.globalProperties.axios = axios
app.config.globalProperties.$backend = new Backend()
app.directive("requirement", requirement)
app.directive("required", required)

app.mount('#app')