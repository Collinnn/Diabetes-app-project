<template>
    <title>Patient Login</title>
    <div class="page-wrapper">
        <div class="pictureContainer">
             <svg class="picture"></svg>
        </div>

        <div class="login-Comp">
            <h1 class="title">Patient Login</h1>
            <input type="number" name="id" v-model="input.id" placeholder="Username..." />
            <input type="password" name="password" v-model="input.password" placeholder="Password..." />
            <button type="button" v-on:click="login()">Login</button>
            <button type="button" @click="lazy()">Lazy</button>
        </div>
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
                        //let doctor = await this.getDoctor()  
                        //data.doctor = doctor
                        console.log("Logged in succesfully")
                        this.$router.push("patientSite");
                        this.$userController.logIn("patient", data)
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
.page-wrapper{
    display: flex;
    align-items: center;
    gap: 40px;
    margin-top:10%;
    flex-direction: column;
}
.login-Comp{
        background-color: var(--secondary-color);
        border-radius: 40px;
        width: 470px;
        padding: 40px;
}
.title{
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