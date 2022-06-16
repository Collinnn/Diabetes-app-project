import User from "@/model/user"

const user = new User()

export default class UserController {
    static isUserLoggedIn() {
        return user.isLoggedIn
    }

    static getDarkTheme() {
        return user.darkTheme
    }

    static setDarkTheme(val) {
        user.darkTheme = val
    }

    static logIn(userType, userData) {
        user.isLoggedIn = true
        user.setType(userType)
        user.setData(userData)
    }

    static logOut() {
        user.isLoggedIn = false
        user.setType("")
        user.setData(null)
    }

    static getUserType() {
        return user.getType()
    }

    static getUserData() {
        return user.getData()
    }

}