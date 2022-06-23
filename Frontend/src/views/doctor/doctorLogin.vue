<!-- Responsible author: Collin
 * Contributors: Zwinge
 -->
<template>
    <title>Doctor Login</title>
    <svg class="large-logo"></svg>
    <div class="login-container">
        <div class="inputs">
            <h1 class="title">Doctor Login</h1>
            <input type="text" name="id" v-model="input.id" placeholder="Username..." autofocus/>
            <input type="password" name="password" v-model="input.password" placeholder="Password..." />
            <p class="wrongInput" v-if="this.input.wrongInput">{{this.input.wrongInputString}}</p>
        </div>
        <div class="standard-buttons">
            <StandardButton name="Back" @click="back()"></StandardButton>
            <StandardButton name="Login" @click="login()"></StandardButton>
        </div>
    </div>
</template>

<script>
import StandardButton from '@/components/StandardButton.vue'
export default {
        name: 'login-doctor',
        components:{
            StandardButton
        },
        data() {
            return {
                input: {
                    id: null,
                    password: "",
                    wrongInput: false,
                    wrongInputString: ""
                }
            }
        },
        methods: {
        async login() {
                if(this.input.id != null || this.input.password != "") {
                    let data = await this.getDoctorData()
                    if(data != null && this.isLoginValid(data.password)){
                        console.log("Logged in succesfully")
                        this.$router.push("doctorSite");
                        this.$userController.logIn("doctor", data) /* HUSK AT SÆTTE USER DATA */
                    }else{
                        this.input.wrongInput = true,
                        this.input.wrongInputString = "Username and/or password was wrong";
                        console.log("Username and/or password was wrong");
                    }
                } else {
                    this.input.wrongInput = true,
                    this.input.wrongInputString = "Username and/or password was empty";
                    console.log("Username and/or password was empty");
            }
        },
        async getDoctorData() {
            let data;
            await this.axios.get(this.$backend.getUrlGetDoctorById(this.input.id))
                    .then(response =>{
                        data = response.data
                    }).catch(() =>
                        this.input.wrongInput = true,
                        console.log("hello")
                    );
            return data
        },
        isLoginValid(password){
                console.log(password);
                return password == this.input.password
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
.title{ 
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