import User from "@/model/user"

const user = new User()

export default class UserController {
    static isUserLoggedIn() {
        return user.isLoggedIn
    }

    static setUserLoggedIn(loggedIn) {
        user.isLoggedIn = loggedIn
    }

    static getUserType() {
        return user.getType()
    }

    static setUserType(userType) {
        user.setType(userType)
    }

    static getUserData() {
        return user.getData()
    }

    static setUserData(userData) {
        user.setData(userData)
    }

}