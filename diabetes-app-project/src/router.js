
import {createRouter, createWebHistory} from 'vue-router'

import login from './views/login.vue'
import Dashboard from './views/dashBoard.vue'
import overview from './views/overview.vue'
import food from './views/food.vue'
import doctor from './views/doctor.vue'
import emergency from './views/emergency.vue'

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
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
