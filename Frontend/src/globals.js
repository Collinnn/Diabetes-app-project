import User from "./services/user"

const user = new User()

const loggedInStatus = {
    isLoggedIn: false,

    get getStatus() {
        return this.isLoggedIn
    },

    
    set setLoggedIn(val) {
        this.isLoggedIn = val
    }
}

export { loggedInStatus, user }