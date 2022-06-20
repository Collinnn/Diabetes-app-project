import { createRouter, createWebHistory } from 'vue-router'

import landing from '@/views/landing.vue'
import patientLogin from '@/views/patient/patientLogin.vue'
import doctorLogin from '@/views/doctor/doctorLogin.vue'
import adminLogin from '@/views/admin/adminLogin.vue'

import patientSite from '@/views/patient/patientSite.vue'
import graphPage from '@/views/patient/graphPage.vue'
import mealPage from '@/views/patient/mealPage.vue'
import userPage from '@/views/patient/userPage.vue'
import changePasswordPage from '@/views/patient/changePasswordPage.vue'

import doctorSite from '@/views/doctor/doctorSite.vue'
import doctorPage from '@/views/doctor/doctorPage.vue'
import patientsListPage from '@/views/doctor/patientsListPage.vue'
import doctorGraphPage from '@/views/doctor/doctorGraphPage.vue'

import adminSite from '@/views/admin/adminSite.vue'
import addPatientPage from '@/views/admin/addPatientPage.vue'
import addDoctorPage from '@/views/admin/addDoctorPage.vue'
import adminPatientsListPage from '@/views/admin/adminPatientsListPage.vue'
import adminDoctorsListPage from '@/views/admin/adminDoctorsListPage.vue'

import pageNotFound from '@/views/pageNotFound.vue'

import UserController from "@/globals.js"


const routes = [
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
        path: '/patient',
        redirect: { name: 'graph' },
        component: patientSite,
        name: 'patientSite',
        meta: {
            requiredLoggedIn: true
        },
        children: [
            {
                path: 'graph',
                component: graphPage,
                name: 'graph'
            },
            {
                path: 'meal',
                component: mealPage,
                name: 'meal'
            },
            {
                path: 'user',
                component: userPage,
                name: 'user'
            },
            {
                path: 'changePassword',
                component: changePasswordPage,
                name: 'changePassword'
            },
        ]
    },
    {
        path: '/doctor',
        redirect: { name: 'patients' },
        component: doctorSite,
        name: 'doctorSite',
        meta: {
            requiredLoggedIn: true
        },
        children: [
            {
                path: 'user',
                component: doctorPage,
                name: "doctor",
            },
            {
                path: 'patients',
                component: patientsListPage,
                name: "patients"
            },
            {
                path: 'patientGraph',
                component: doctorGraphPage,
                name: 'doctorGraphPage'
            }
        ]
    },
    {
        path: '/admin',
        redirect: { name: 'addPatient' },
        component: adminSite,
        name: 'adminSite',
        meta: {
            requiredLoggedIn: true
        },
        children: [
            {
                path: 'addPatient',
                component: addPatientPage,
                name: 'addPatient'
            },
            {
                path: 'addDoctor',
                component: addDoctorPage,
                name: 'addDoctor'
            },
            {
                path: 'adminPatientsList',
                component: adminPatientsListPage,
                name: 'adminPatientsListPage'
            },
            {
                path: 'adminDoctorsList',
                component: adminDoctorsListPage,
                name: 'adminDoctorsListPage'
            }
        ]
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
        if (!UserController.isUserLoggedIn()) {
            return { name: 'landing' }
        }
    } else if (to.matched.some(route => !route.meta.requiredLoggedIn)) {
        if (UserController.getUserType() == "patient") {
            return { name: 'patientSite' }
        }
        else if (UserController.getUserType() == "doctor") {
            return { name: 'doctorSite' }
        }
        else if (UserController.getUserType() == "admin") {
            return { name: 'adminSite' }
        }
    }
})


export default router