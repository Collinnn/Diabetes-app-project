<template>
    <div>
        <div class="settingsMenu" v-if="(this.$userController.getUserType() == 'patient')">
            <button class="settingsButton" @click="this.$router.push('user')"> View profile </button>
            <button class="settingsButton" @click="this.$router.push('changePassword')"> Change password </button>
            <button class="settingsButton" @click="logOut('landing')"> Log out </button>
        </div>
        <div class="settingsMenu" v-if="(this.$userController.getUserType() == 'doctor')">
            <button class="settingsButton" @click="this.$router.push('doctor')"> View Doctor profile </button>
            <button class="settingsButton" @click="this.$router.push('changePassword')"> Change password </button>
            <button class="settingsButton" @click="logOut('landing')"> Log out </button>
        </div>
    </div>
</template>


<script>
    export default {
        name: "ProfileDropdown",
        methods: {
            logOut(pageName){
                this.$router.push({name: pageName});
                this.$userController.logOut();
            }
        }
}
</script>

<style scoped>
.settingsMenu {
    position: absolute;
    border-radius: 10px;
    right: 0%;
    top: 6%;
    width: 200px;
    height: 300px;
    background-color: rgba(106, 121, 187, 0.8);
    display: flex;
    flex-direction: column;
    justify-content: space-around;
}

.settingsButton {
    margin: 0px 10px;
    padding: 10px 0px;
}
</style>

