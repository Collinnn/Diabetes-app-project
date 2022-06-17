<template>
    <title>User Page</title>
    <div class="content">
        <div id="container">
            <h3>User Page</h3>
            <div id="id">User id: {{this.$userController.getUserData().id}}</div>
            <div id="id">User name: {{this.$userController.getUserData().firstName}} {{this.$userController.getUserData().lastName}}</div>
            <div id="id" v-if="this.$userController.getUserData().doctor != null">Doctor's name: {{this.$userController.getUserData().doctor.firstName}} {{this.$userController.getUserData().doctor.lastName}}</div>
            <div id="id" v-else>No doctor assigned</div>
            <button class="settingsButton" @click="goToPage('changePassword')"> Change password </button>
        </div>
    </div>
</template>


<script>

export default {
    name: "userPage",
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