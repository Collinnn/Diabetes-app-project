<template>
    <div class ="profile dropdown">
        <div id="settingsMenu" v-if="this.app.loggedIn">
            <button class="settingsButton" @click="goToPage('user')"> View profile </button>
            <button class="settingsButton" @click="goToPage('changePassword')"> Change password </button>
            <button class="settingsButton" @click="logOut('landing')"> Log out </button>
        </div>
        <div id="settingsMenu" v-if="this.app.loggedInasDoctor">
            <button class="settingsButton" @click="goToPage('doctor')"> View Doctor profile </button>
            <button class="settingsButton" @click="goToPage('changePassword')"> Change password </button>
            <button class="settingsButton" @click="logOut('landing')"> Log out </button>
        </div>
    </div>
</template>


<script>
    export default {
        name: "ProfileDropdown",
        emits:['logOut'],
        props:{
            app:{
                loggedIn:Boolean,
                loggedInasDoctor:Boolean,
                loggedInasAdmin:Boolean
            }
        },
        methods: {
            goToPage(pageName) {
                this.$router.push({ name: pageName})
            },

            logOut(pageName){
                this.$router.push({name: pageName});
                this.$emit('logOut');
            }
        }
}
</script>

<style scoped>
#settingsMenu {
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

