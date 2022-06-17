<template>
    <title>Login Patient</title>
    <div class="login-Comp">
        <h1 class="title">Login Patient</h1>
        <input type="number" name="id" v-model="input.id" placerholder="id" />
        <input type="password" name="password" v-model="input.password" palceholder="Password" />
        <button type="button" id="button" v-on:click="login()">Login</button>
        <button type="button" id="button" @click="lazy()">Lazy</button>
        <h1></h1>
    </div>
</template>

<script>
export default {
        name: 'login-patient',
        data() {
            return {
                input: {
                    id: 1,
                    password: "password"
                }
            }
        },
        methods: {
            login() {
                if(this.input.id != 0 || this.input.password != "") {
                    let data;
                    this.axios.get(this.$backend.getUrlGetPatientById(this.input.id))
                    .then(response =>{
                        console.log(response.data.password);
                        data = response.data
                    }).catch((error) => console.log(error));
                    if(this.isLoginValid(data.password)){
                        console.log("Logged in succesfully")
                        this.$router.push("patientSite");
                        this.$userController.logIn("patient", data) /* HUSK AT SÆTTE USER DATA */
                    }else{
                        console.log("username and/or password was wrong");
                    }
                } else {
                    console.log("Username and/or password was empty");
            }
        },
        isLoginValid(password){
                console.log(password);
                if(password == this.input.password){
                    return true;
                }else{
                    return false;
                }
        },
        lazy(){
            this.$router.push("patientSite");
            this.$userController.logIn("patient", null) /* HUSK AT SÆTTE USER DATA */
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
        text-align: center;
        padding: 0px 0px 0px 0;
        font-size: 60px;
        
    }
    #button{
        
    }
</style>