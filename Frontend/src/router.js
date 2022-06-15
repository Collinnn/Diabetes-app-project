import { createRouter, createWebHistory } from 'vue-router'


import overview from './views/overview.vue'
import food from './views/food.vue'
import landing from './views/landing.vue'
import patientLogin from './views/patientLogin.vue'
import doctorLogin from './views/doctorLogin.vue'
import adminLogin from './views/adminLogin'
import pageNotFound from './views/pageNotFound.vue'
import user from './views/user.vue'
import changePassword from './views/changePassword.vue'
import { loggedInStatus } from "./variables.js"
import addPatient from './views/addPatient'
import addDoctor from './views/addDoctor'


const routes = [{
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
        path: '/',
        component: landing,
        name: 'landing',
        meta: {
            requiredLoggedIn: false
        }
    },
    {
        path: '/patientLogin',
        component: patientLogin,
        name: 'patientLogin',
        meta: {
            requiredLoggedIn: false
        }
    },
    {
        path: '/doctorLogin',
        component: doctorLogin,
        name: 'doctorLogin',
        meta: {
            requiredLoggedIn: false
        }
    },
    {
        path: '/adminLogin',
        component: adminLogin,
        name: 'adminLogin',
        meta: {
            requiredLoggedIn: false
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
    },
    {
        path: '/addPatient',
        component: addPatient,
        name: 'addPatient',
        meta: {
            requiredLoggedIn: true
        }
    },
    {
        path: '/addDoctor',
        component: addDoctor,
        name: 'addDoctor',
        meta: {
            requiredLoggedIn: true
        }
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

router.beforeEach((to) => {
    if (to.matched.some(route => route.meta.requiredLoggedIn)) {
        if (!(loggedInStatus.getUserStatus || loggedInStatus.getDocterStatus || loggedInStatus.getAdminStatus)) {
            return { name: 'landing' }
        }
    } else if (to.matched.some(route => !route.meta.requiredLoggedIn)) {
        if (loggedInStatus.getStatus) {
            return { name: 'overview' }
        }
    }
})


export default router