<template>
    <title>Landing page</title>
    <div class="pagecontainer">
    <div class="pictureContainer" >
        <svg class="picture"></svg>
    </div>
    <div class="container">
        <button class="landingButton" @click="goToPage('patientLogin')">
        Patient Login
        </button>
        <button class="landingButton" @click="goToPage('doctorLogin')">
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

.pagecontainer{
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 40px;
    margin-top:2%;
    
    

}

.container{
    background-color: var(--secondary-color);
    display: flex;
    flex-direction: column;
    align-items: center;
    border-radius: 40px;
    width: 400px;
    height: 300px;
    flex-grow: 1;
}
.landingButton{
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
.landingButton:hover{
    background: rgba(255, 255, 255, 0.60);
}
#adminLoginLink {
    cursor: pointer;
    color: var(--text-color);
    text-decoration: underline;
    margin-bottom: 10px;
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