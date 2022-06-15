export default class User {
    constructor() {
        this.isLoggedIn = false
        this.userType = ""
    }

    setUserType(userType) {
        this.userType = userType
    }

    getUserType() {
        return this.userType
    }
}