import { createRouter, createWebHistory } from 'vue-router'

import overview from './views/overview.vue'
import food from './views/food.vue'
import doctor from './views/doctor.vue'
import emergency from './views/emergency.vue'
import landing from './views/landing.vue'
import patientLogin from './views/patientLogin.vue'
import doctorLogin from './views/doctorLogin.vue'
import pageNotFound from './views/pageNotFound.vue'
import user from './views/user.vue'
import changePassword from './views/changePassword.vue'

const routes = [
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
    },
    {
        path:'/doctorLogin',
        component: doctorLogin,
        name: 'doctorLogin'
    },
    {
        path: '/user',
        component: user,
        name: 'user'
    },
    {
        path: '/changePassword',
        component: changePassword,
        name: 'changePassword'
    },
    {
        path: '/:pathMatch(.*)',
        component: pageNotFound,
        name: 'pageNotFound'
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router