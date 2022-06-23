 <!-- Responsible author: Christopher Zwinge
 * Contributors:
 -->
<template>
    <div>
        <div class="settingsMenu" v-if="(this.$userController.getUserType() == 'patient')">
            <button class="settingsButton" @click="this.$router.push('user')"> View profile </button>
            <button class="settingsButton" @click="this.$router.push('changePassword')"> Change password </button>
            <button class="settingsButton" @click="logOut('landing')"> Log out </button>
        </div>
        <div class="settingsMenu" v-if="(this.$userController.getUserType() == 'doctor')">
            <button class="settingsButton" @click="this.$router.push('user')"> View Doctor profile </button>
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
    position: fixed;
    border-radius: 10px;
    right: 5px;
    top: calc(var(--topbar-height) + 5px);
    width: 200px;
    height: 300px;
    background-color: var(--accent-color);
    display: flex;
    flex-direction: column;
    justify-content: space-around;
}

.settingsButton {
    margin: 0px 10px;
    padding: 10px 0px;
}
</style>

