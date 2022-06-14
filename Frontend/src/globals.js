

const loggedInStatus = {
    isLoggedIn: false,

    get getStatus() {
        return this.isLoggedIn
    },

    set setLoggedIn(val) {
        this.isLoggedIn = val
    }
}

export { loggedInStatus }