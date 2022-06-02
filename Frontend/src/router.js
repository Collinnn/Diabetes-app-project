
import {createRouter, createWebHistory} from 'vue-router'

import login from './views/login.vue'
import overview from './views/overview.vue'
import food from './views/food.vue'
import doctor from './views/doctor.vue'
import emergency from './views/emergency.vue'
import landing from './views/landing.vue'
import patientLogin from './views/patientLogin.vue'

const routes = [
    {
        path: '/login',
        component: login,
        name: 'login'
    },
    {
        path: '/overview',
        component: overview,
        name: 'overview'
    },
    {
        path: '/food',
        component: food,
        name: 'food'
    },
    {
        path: '/doctor',
        component: doctor,
        name: 'doctor'
    },
    {
        path: '/emergency',
        component: emergency,
        name: 'emergency'
    },
    {
        path: '/',
        component: landing,
        name: 'landing'
    },
    {
        path:'/patientLogin',
        component: patientLogin,
        name: 'patientLogin'
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
