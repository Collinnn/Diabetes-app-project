 <!-- Responsible author: Tobias Collin
 * Contributors: Christopher Zwinge and Jacob Martens helped with adding the usercontroller and general logged in behaviour 
 -->
<template>
    <title> Admin login page </title>
    <svg class="large-logo"></svg>
    <div class="login-container">
        <div class="inputs">
            <h1 class="title">Login Admin </h1>
            <input type="text" v-model="username" placeholder="Username..." autofocus />
            <input type="password" v-model="password" placeholder="Password..." />
            <p class="wrongInput" v-if="inputWrong">Invalid username and/or password</p>
        </div>
        <div class="standard-buttons">
            <standard-button name="Back" @click="back()"></standard-button>
            <standard-button name="Login" @click="login()"></standard-button>
        </div>
    </div>
</template>

<script>
import StandardButton from '@/components/StandardButton.vue'
export default {
    name: 'adminLogin',
    components:{
        StandardButton
    },
    data() {
        return {
            username: "",
            password: "",
            inputWrong:false
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
                this.inputWrong = true
                console.log("Invalid username and/or password")
            }
        },
        back(){
            this.$router.push({name: "landing"});
        }
    }
}
</script>
<style scoped>
.login-container {
    background-color: var(--secondary-color);
    border-radius: 40px;
    height: 280px;
    width: 470px;
    padding: 40px;
}
.title { 
    font-size: 60px; 
}
.inputs{
    gap:10px
}
.standard-buttons {
    display: flex;
    margin-top:5%;
    gap:10px;
    align-items: center;
    justify-content: center;
    flex-direction: row;
}
.wrongInput{
    color:red;
    font-size: 20px; 
}
</style>