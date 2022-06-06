<template>
    <div class="container">
        <button class="landingButton" id="patientButton" @click="goToPage('patientLogin')">
        Patient Login
        </button>

        <button class="landingButton" id="doctorButton" @click="goToPage('overview')">
        Doctor Login
        </button>
    </div> 
</template>

<script>
import router from "@/router"

export default {
    name: "landingPage",
    data() {
        return{
            title:'landingPage'
        }
    },
    methods: {
        goToPage(pageName) {
            router.push({name:pageName})
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
    background-color: #0010bb;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
    border-radius: 40px;
}
.landingButton{
    background-color: #5b6be4;
    border: 1cm;
    color: #002851;
    padding: 20 px 45px; 
    text-align: center;
    text-decoration: none;
    display: inline-flex;
    font-size: 42px;
    margin: 40px 2px;
    cursor: pointer;
}
.landingButton:hover{
    background: rgba(255, 255, 255, 0.60);
}
</style>