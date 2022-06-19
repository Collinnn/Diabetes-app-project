<template>
    <title>Landing page</title>
    <div class="container">
        <button class="landingButton" @click="goToPage('patientLogin')">
        Patient Login
        </button>

        <button class="landingButton" @click="goToPage('doctorLogin')">
        Doctor Login
        </button>
        <router-link id="adminLoginLink" to="/adminLogin"> Login as administrator </router-link>
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
.container{
    position: relative;
    top:300px;
    left:20%;
    align-content: center;
    max-width: 50%;
    background-color: var(--secondary-color);
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
    border-radius: 40px;
}
.landingButton{
    background-color: var(--accent-color);
    border: 1cm;
    color: #002851;
    padding: 20 px 45px; 
    text-align: center;
    text-decoration: none;
    display: inline-flex;
    font-size: 42px;
    margin: 40px 6px;
    cursor: pointer;
    border-radius: 20px;
    float:inherit;
}
.landingButton:hover{
    background: rgba(255, 255, 255, 0.60);
}
#adminLoginLink {
    cursor: pointer;
    color: var(--text-color);
    text-decoration: underline;
    margin-bottom: 10px;
}
</style>