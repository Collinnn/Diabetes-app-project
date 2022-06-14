<template>
    <title> Admin login page </title>
    <div class="page-wrapper"> 
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
    emits: ['logIn'],
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
                this.$router.push("addPatient");
                this.$emit('logIn');
            }
        }
    }
}
</script>
<style scoped>
.page-wrapper {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
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
</style>