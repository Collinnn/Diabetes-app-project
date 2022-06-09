import { createRouter, createWebHistory } from 'vue-router'

import login from './views/login.vue'
import overview from './views/overview.vue'
import food from './views/food.vue'
import doctor from './views/doctor.vue'
import emergency from './views/emergency.vue'
import pageNotFound from './views/pageNotFound.vue'
import user from './views/user.vue'
import changePassword from './views/changePassword.vue'
import { loggedInStatus } from "./variables.js"



const routes = [{
        path: '/',
        component: login,
        name: 'login',
        meta: {
            requiredLoggedIn: false
        }
    },
    {
        path: '/overview',
        component: overview,
        name: 'overview',
        meta: {
            requiredLoggedIn: true
        }
    },
    {
        path: '/food',
        component: food,
        name: 'food',
        meta: {
            requiredLoggedIn: true
        }
    },
    {
        path: '/doctor',
        component: doctor,
        name: 'doctor',
        meta: {
            requiredLoggedIn: true
        }
    },
    {
        path: '/emergency',
        component: emergency,
        name: 'emergency',
        meta: {
            requiredLoggedIn: true
        }
    },
    {
        path: '/user',
        component: user,
        name: 'user',
        meta: {
            requiredLoggedIn: true
        }
    },
    {
        path: '/changePassword',
        component: changePassword,
        name: 'changePassword',
        meta: {
            requiredLoggedIn: true
        }
    },
    {
        path: '/:pathMatch(.*)',
        component: pageNotFound,
        name: 'pageNotFound',
        meta: {
            requiredLoggedIn: false
        }
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

router.beforeEach((to) => {
    if (to.matched.some(route => route.meta.requiredLoggedIn)) {
        if (!loggedInStatus.getStatus) {
            return { name: 'login' }
        }
    }
})




export default router