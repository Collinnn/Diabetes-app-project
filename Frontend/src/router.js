
import {createRouter, createWebHistory} from 'vue-router'

import login from './views/login.vue'
import Dashboard from './views/Dashboard.vue'
const routes = [
    {
        path: '/',
        component: login,
        name: 'login'
      },
    {
        path: '/dashBoard',
        component: Dashboard,
        name: 'dashBoard'
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
