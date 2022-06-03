<template>
    <div class="container">
        <button class="landingButton" id="patientButton" @click="goToPage('patientLogin')">
        Patient Login
        </button>

        <button class="landingButton" id="doctorButton" @click="goToPage('doctorLogin')">
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
    left:30%;
    align-content: center;
    max-width: 35%;
    background-color: #ffffff;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
    border-radius: 40px;
    padding: 30px 0px;
}
.landingButton{
    background-color: #5b6be4;
    border: 1cm;
    text-align: center;
    text-decoration: none;
    display: inline-flex;
    font-size: 42px;
    margin: 20px 2px;
    cursor: pointer;
    padding: 40px 50px;
    border-radius: 40px;
    color: #ffffff;
}
.landingButton:hover{
    background: #E5E5E5;;
}

</style>