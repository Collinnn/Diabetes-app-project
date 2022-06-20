<template>
    <title> Admin login page </title>
    <div class="page-wrapper"> 
        <div class="pictureContainer">
             <svg class="picture"></svg>
        </div>
        <div class="container">
            <h1 class="title">Login Admin </h1>
            <input type="text" v-model="username" placeholder="Username..." autofocus />
            <input type="password" v-model="password" placeholder="Password..." />
            <button @click="login">Login</button>
        </div>
    </div>
</template>

<script>

export default {
    name: 'adminLogin',
    data() {
        return {
            username: "",
            password: ""
        }
    },
    methods: {
        login() {
            if (this.username == "admin" && this.password == "admin") {
                console.log("Logged in succesfully")
                this.$router.push("adminSite");
                this.$userController.logIn("admin", null)
            }
            else {
                console.log("Invalid username and/or password")
            }
        }
    }
}
</script>
<style scoped>
.page-wrapper {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 40px;
    margin-top:10%;
    flex-direction: column;
}
.container {
    background-color: var(--secondary-color);
    border-radius: 40px;
    width: 470px;
    padding: 40px;
}
.title { 
    font-size: 60px; 
}
.pictureContainer{
    display: flex;
    align-items: center;
    height:300px;

}
.picture{
    display: flex;
    height: 400px;
    background: url('@/assets/Home.svg') no-repeat center;
    background-size: 300px 300px;
}
</style>