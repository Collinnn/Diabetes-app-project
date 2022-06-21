<template>
    <title>Patient Page</title>
    <div class="content">
        <div id="container">
            <h3>Patient Page</h3>
            <div>User id: {{this.$userController.getUserData().id}}</div>
            <div>User name: {{this.$userController.getUserData().firstName}} {{this.$userController.getUserData().lastName}}</div>
            <div v-if="this.$userController.getUserData().doctor != null">Doctor's name: {{this.$userController.getUserData().doctor.firstName}} {{this.$userController.getUserData().doctor.lastName}}</div>
            <div v-else>No doctor assigned</div>
            <button @click="goToPage('changePatientPassword')"> Change password </button>
        </div>
    </div>
</template>


<script>

export default {
    name: "patientPage",
    methods:{
        goToPage(pageName) {
            this.$router.push({ name: pageName})
        }
    }
}

</script>

<style>
.content {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}
#container {
    width: 400px;
    border-radius: 20px;
    background-color: rgb(192, 192, 192);
    padding: 10px;
}
</style>