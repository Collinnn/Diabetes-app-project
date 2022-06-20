<template>
    <title>Landing page</title>
    <svg class="large-logo"></svg>
    <div class="landing-container">
        <button class="login-button" @click="goToPage('patientLogin')">
        Patient Login
        </button>
        <button class="login-button" @click="goToPage('doctorLogin')">
        Doctor Login
        </button>
        <router-link id="adminLoginLink" to="/adminLogin"> Login as administrator </router-link>
    </div>

    </div>

   
</template>

<script>

export default {
    name: "landingPage",
    data() {
        return{
            title:'landingPage'
        }
    },
    methods: {
        goToPage(pageName) {
            this.$router.push({name: pageName})
        }
    }
}
</script>
<style scoped>
.landing-container{
    background-color: var(--secondary-color);
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    border-radius: 40px;
    width: 550px;
    height: 360px;
}
.login-button{
    background-color: var(--accent-color);
    border: 1cm;
    color: #002851;
    padding: 20 px 45px; 
    text-align: center;
    text-decoration: none;
    display: flex;
    font-size: 42px;
    margin: 40px 6px;
    cursor: pointer;
    border-radius: 20px;

}
.login-button:hover{
    background: rgba(255, 255, 255, 0.60);
}
#adminLoginLink {
    cursor: pointer;
    color: var(--text-color);
    text-decoration: underline;
    margin-bottom: 10px;
}


</style>