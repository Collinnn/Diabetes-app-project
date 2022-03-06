<template>
    <div id="login-comp">
        <h1>Login</h1>
        <input type="text" name="username" v-model="input.username" placerholder="Username" />
        <input type="password" name="password" v-model="input.password" palceholder="Password" />
        <button type="button" v-on:click="login()">Login</button>

    </div>
</template>

<script>
    export default {
        name: 'Login',
        data() {
            return {
                input: {
                    username: "",
                    password: ""
                }
            }
        },
        methods: {
            login() {
                if(this.input.username != "" && this.input.password != "") {
                    if(this.input.username =="username" && this.input.password == "password") {
                        this.$router.replace({ name: "DashBoard"});
                    } else {
                        console.log("The username and / or password is incorrect");
                    }
                } else {
                    console.log("Username and/or password was empty");
                }
            }
        }
    }

</script>
<style scoped>


</style>