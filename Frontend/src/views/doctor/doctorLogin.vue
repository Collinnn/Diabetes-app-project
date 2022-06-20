<template>
    <title>Doctor Login</title>
    <div class="page-wrapper">
        <div class="pictureContainer">
             <svg class="picture"></svg>
        </div>
        <div class="login-Comp">
            <div class="inputs">
                <h1 class="title">Doctor Login</h1>
                <input type="number" name="id" v-model="input.id" placeholder="Username..." />
                <input type="password" name="password" v-model="input.password" placeholder="Password..." />
                <p class="wrongInput" v-if="this.input.wrongInput">Invalid username and/or password</p>
            </div>
        <div class="buttons">
            <StandardButton name="Back" @click="back()"></StandardButton>
            <StandardButton name="Login" @click="login()"></StandardButton>
        </div>
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
                    wrongInput: false
                }
            }
        },
        methods: {
            async login() {
                if(this.input.id != null || this.input.password != "") {
                    let data = await this.getDoctorData()
                    if(this.isLoginValid(data.password)){
                        console.log("Logged in succesfully")
                        this.$router.push("doctorSite");
                        this.$userController.logIn("doctor", data) /* HUSK AT SÆTTE USER DATA */
                    }else{
                        this.input.wrongInput=true;
                        console.log("Username and/or password was wrong");
                    }
                } else {
                    this.input.wrongInput =true;
                    console.log("Username and/or password was empty");
            }
        },
        async getDoctorData() {
            let data;
            await this.axios.get(this.$backend.getUrlGetDoctorById(this.input.id))
                    .then(response =>{
                        data = response.data
                    }).catch((error) => error);
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

.page-wrapper{
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 40px;
    margin-top:10%;
}
.login-Comp{
    position:relative;
    background-color: var(--secondary-color);
    border-radius: 40px;
    width: 470px;
    padding: 40px;
}
.login-Comp .title{ 
    padding: 0px 0px 0px 0;
    font-size: 60px;
    
}
.pictureContainer{
    display: flex;
    align-items: center;
    height:300px;

}
.inputs{
    gap:10px
}
.picture{
    display: flex;
    height: 400px;
    background: url('@/assets/Home.svg') no-repeat center;
    background-size: 300px 300px;
}
.buttons{
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