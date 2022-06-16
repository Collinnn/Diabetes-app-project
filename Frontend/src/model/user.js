export default class User {
    constructor() {
        this.isLoggedIn = false
        this.type = ""
        this.darkTheme = null
        this.data = null
    }

    getType() {
        return this.type
    }

    setType(type) {
        this.type = type
    }

    getData() {
        return this.data
    }

    setData(data) {
        this.data = data
    }
}