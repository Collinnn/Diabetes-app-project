<template>
    <title>Login page</title>
    <div class="login-Comp">
        <h1 class="title">Login</h1>
        <p>Ps. code is username then password</p>
        <input type="text" name="username" v-model="input.username" placerholder="Username" />
        <input type="password" name="password" v-model="input.password" palceholder="Password" />
        <button type="button" v-on:click="login()">Login</button>
    </div>
</template>

<script>
    export default {
        name: 'login-Comp',
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
                        this.$router.replace({ name: "overview"});
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
    .login-Comp{
        text-align: center;
    }
    .login-Comp .title{ 
        padding: 0px 0px 0px 0;
        font-size: 60px;
        
    }
</style>