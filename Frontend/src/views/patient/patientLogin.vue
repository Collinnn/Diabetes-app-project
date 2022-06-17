<template>
    <title>Login Patient</title>
    <div class="login-Comp">
        <h1 class="title">Login Patient</h1>
        <input type="number" name="id" v-model="input.id" placeholder="Username..." />
        <input type="password" name="password" v-model="input.password" placeholder="Password..." />
        <button type="button" id="button" v-on:click="login()">Login</button>
        <button type="button" id="button" @click="lazy()">Lazy</button>
    </div>
</template>

<script>
export default {
        name: 'login-patient',
        data() {
            return {
                input: {
                    id: null,
                    password: ""
                }
            }
        },
        methods: {
            async login() {
                if(this.input.id != null || this.input.password != "") {
                    let data = await this.getPatientData()
                    if(this.isLoginValid(data.password)){
                        let doctor = await this.getDoctor()
                        data.doctor = doctor
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
        async getPatientData() {
            let data;
            await this.axios.get(this.$backend.getUrlGetPatientById(this.input.id))
                    .then(response =>{
                        data = response.data
                    }).catch((error) => console.log(error));
            return data
        },
        isLoginValid(password){
                console.log(password);
                return password == this.input.password
        },
        async getDoctor() {
            let doctor;
            await this.axios.get(this.$backend.getUrlGetDoctorFromPatientById(this.input.id))
                    .then(response =>{
                        doctor = response.data
                    }).catch((error) => console.log(error));
            return doctor
        },
        lazy() {
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
</style>