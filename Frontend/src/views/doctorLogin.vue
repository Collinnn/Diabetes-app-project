<template>
    <div class="login-Comp">
        <h1 class="title">Login Doctor</h1>
        <input type="number" name="id" v-model="input.id" placerholder="id" />
        <input type="password" name="password" v-model="input.password" palceholder="Password" />
        <button type="button" id="button" @click="login()">Login</button>
        <button type="button" id="button" @click="lazy()">Lazy</button>
        <h1></h1>
    </div>
</template>

<script>
import router from "@/router"
export default {
        name: 'login-doctor',
        emits: ['logIn'],
        data() {
            return {
                input: {
                    id: 1,
                    password: "password"
                }
            }
        },
        methods: {
            logInValidation(password){
                console.log(password);
                if(password == this.input.password){
                    console.log("Logged in succesfully")
                    router.push({name: "overview"});
                    this.$emit('logIn');
                }else{
                    console.log("username and/or password was wrong");
                }
            },
            login() {

                if(this.input.id != 0 || this.input.password != "") {
                    this.axios.get(this.$backend.getUrlPutDoctorById(this.input.id))
                    .then(response =>{
                        console.log(response.data.password);
                        this.logInValidation(response.data.password);
                    }).catch((error) => console.log(error));
                } else {
                    console.log("Username and/or password was empty");
            }
        },lazy(){
            this.$emit('logIn');
            router.push({name: "overview"});
        }

    }
}
</script>
<style scoped>
    .login-Comp{
        position:relative;
        background-color: var(--secondary-color);
        border-radius: 40px;
        width: 470px;
        left:20%;
        margin: 40px 2px;
        padding: 40px;
    }
    .login-Comp .title{ 
        padding: 0px 0px 0px 0;
        font-size: 60px;
        
    }
    #button{
        
    }
</style>