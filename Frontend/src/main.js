/*
 * Auto generated file
 * Contributions: Jacob Martens
 */
import { createApp } from 'vue'
import axios from 'axios'
import App from './App.vue'
import Backend from './services/backend'
import router from './services/router'
import requirement from './services/directives'
import UserController from './services/userController'

const app = createApp(App)
app.use(router)
app.config.globalProperties.axios = axios
app.config.globalProperties.$backend = new Backend() 
app.config.globalProperties.$userController = UserController //Jacob Martens
app.directive("requirement", requirement) //Jacob Martens

app.mount('#app')

// Jacob Martens
document.getElementById('app').style.setProperty("--sidebar-width", '18%')
document.getElementById('app').style.setProperty("--sidebar-max-width", '150px')
document.getElementById('app').style.setProperty("--sidebar-min-width", '120px')
document.getElementById('app').style.setProperty("--topbar-height", '50px')
