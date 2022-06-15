const loggedInStatus = {
    isLoggedIn: false,
    isLoggedInasDoctor: false,
    isLoggedInasAdmin: false,

    get getUserStatus() {
        return this.isLoggedIn
    },

    get getDoctorStatus() {
        return this.isLoggedInasDoctor
    },

    get getAdminStatus() {
        return this.isLoggedInasAdmin
    },

    set setUserLoggedIn(val) {
        this.isLoggedIn = val
    },

    set setDoctorLoggedIn(val) {
        this.isLoggedInasDoctor = val
    },

    set setAdminLoggedIn(val) {
        this.isLoggedInasAdmin = val
    }
}

export { loggedInStatus }