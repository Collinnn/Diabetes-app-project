import { createRouter, createWebHistory } from 'vue-router'

import landing from '@/views/landing.vue'
import patientLogin from '@/views/patient/patientLogin.vue'
import doctorLogin from '@/views/doctor/doctorLogin.vue'
import adminLogin from '@/views/admin/adminLogin.vue'
import patientSite from '@/views/patient/patientSite.vue'
import overview from '@/views/patient/overview.vue'
import food from '@/views/patient/food.vue'
import user from '@/views/user.vue'
import changePassword from '@/views/patient/changePassword.vue'
import doctorSite from '@/views/doctor/doctorSite.vue'
import adminSite from '@/views/admin/adminSite.vue'
import addPatient from '@/views/admin/addPatient.vue'
import addDoctor from '@/views/admin/addDoctor.vue'
import pageNotFound from '@/views/pageNotFound.vue'

import { loggedInStatus } from "@/globals.js"



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
        components: { patientSite },
        name: 'patientSite',
        meta: {
            requiredLoggedIn: true
        },
        children: [
            {
                path: 'overview',
                component: overview,
                name: 'overview',
                meta: {
                    requiredLoggedIn: true
                }
            },
            {
                path: 'food',
                component: food,
                name: 'food',
                meta: {
                    requiredLoggedIn: true
                }
            },
            {
                path: 'user',
                component: user,
                name: 'user',
                meta: {
                    requiredLoggedIn: true
                }
            },
            {
                path: 'changePassword',
                component: changePassword,
                name: 'changePassword',
                meta: {
                    requiredLoggedIn: true
                }
            },
        ]
    },
    {
        path: '/doctor',
        components: { doctorSite },
        name: 'doctorSite',
        meta: {
            requiredLoggedIn: true
        },
        children: [

        ]
    },
    {
        path: '/admin',
        components: { adminSite },
        name: 'adminSite',
        meta: {
            requiredLoggedIn: true
        },
        children: [
            {
                path: 'addPatient',
                component: addPatient,
                name: 'addPatient',
                meta: {
                    requiredLoggedIn: true
                }
            },
            {
                path: 'addDoctor',
                component: addDoctor,
                name: 'addDoctor',
                meta: {
                    requiredLoggedIn: true
                }
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
        if (!loggedInStatus.getStatus) {
            return { name: 'landing' }
        }
    } else if (to.matched.some(route => !route.meta.requiredLoggedIn)) {
        if (loggedInStatus.getStatus) {
            return { name: 'doctorSite' }
        }
    }
})


export default router