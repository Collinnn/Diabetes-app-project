<template>
    <div class="login-Comp">
        <h1 class="title">Login Patient</h1>
        <input type="text" name="id" v-model="input.id" placerholder="id" />
        <input type="password" name="password" v-model="input.password" palceholder="Password" />
        <button type="button" v-on:click="login()">Login</button>
        <h1></h1>
    </div>
</template>

<script>
import router from "@/router"

export default {
        name: 'login-patient',
        data() {
            return {
                input: {
                    id: "",
                    password: ""
                }
            }
        },
        methods: {
            login() {
                if(this.input.id != "" && this.input.password != "") {
                    this.axios.get(this.$backend.getUrlGetPatientId(this.input.id))
                    .then(function(response){
                        if(response.id==this.input.id && response.password == this.input.password){
                            console.log("Logged in succesfully")
                            router.push("overview");

                        }
                    });

                    if(this.input.id =="username" && this.input.password == "password") {
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
        position:relative;
        background-color: rgb(110, 120, 250);
        border-radius: 40px;
        max-width: 50%;
        left:20%;
        margin: 40px 2px;
    }
    .login-Comp .title{ 
        padding: 0px 0px 0px 0;
        font-size: 60px;
        
    }
</style>