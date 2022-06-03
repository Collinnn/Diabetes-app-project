<template>
    <div class ="profile dropdown">
        <div id="settingsMenu">
            <button class="settingsButton" @click="goToPage(profilePage)"> View profile </button>
            <button class="settingsButton" @click="goToPage(passwordPage)"> Change password </button>
            <button class="settingsButton" @click="logOut('login')"> Log out </button>
        </div>
    </div>
</template>


<script>
    import router from "@/router"

    export default {
        name: "ProfileDropdown",
        emits:['logOut'],
        methods: {
            goToPage(pageName) {
                router.push({ name: pageName})
            },

            logOut(pageName){
                router.push({name: pageName});
                this.$emit('logOut');
            }
        }
}
</script>

<style scoped>
#settingsMenu {
    position: absolute;
    border-radius: 10px;
    right: calc((100% - 300px)/6);
    top: 85%;
    width: 200px;
    height: 300px;
    background-color: rgba(106, 121, 187, 0.8);
    display: flex;
    flex-direction: column;
    justify-content: space-around;
}
</style>

