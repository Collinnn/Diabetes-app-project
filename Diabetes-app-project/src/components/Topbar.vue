<template>
    <div class ="topbar">
        <button class = "topbarButton" id = "homeButton" @click="goToPage(dashBoard)">
            <svg class="icon" id="homeIcon"></svg>
        </button>
        <button class = "topbarButton" id = "userButton" @click="goToPage(profilePage)">
            <svg class="icon" id="userIcon"></svg>
        </button>
        <button class = "topbarButton" id = "settingsButton">
            <svg class="icon" id="settingsIcon"></svg>
        </button>
        <div id="settingsMenu">
            <button class="settingsButton"> Settings 1 </button>
            <button class="settingsButton"> Settings 2 </button>
            <button class="settingsButton"> Settings 3 </button>
            <button class="settingsButton"> Settings 4 </button>
        </div>
    </div>
</template>

<script>

    import router from "@/router"

    export default {
        name: "TopbarMenu",
        data() {
            return {
                title: 'Sidebar',
                settings: [
                    {
                        title: 'Interface settings', 
                        link: '#'
                    },
                    {
                        title: 'Darkmode', 
                        link: '#'
                    },
                    {
                        title: 'Advanced settings', 
                        link: '#'
                    }
                ]
            }
        },
        methods: {
            goToPage(pageName) {
                router.push({ name: pageName})
            }
        }
}

</script>

<style scoped>

.topbar {
    position: fixed;
    top: 0;
    right: 0;
    left: 0;
    height: 15%;
    width: 100%;
    min-height: 120px;
    max-height: 150px;
    background: rgb(34, 8, 185);
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    padding-top: 1%;
}

.topbarButton {
    cursor: pointer;
    background-color: rgb(0, 60, 255);
    width: 100px;
    height: 100px;
    border-radius: 10px;
    outline: none;
    border: none;
}

.topbarButton:hover{
    background: rgb(106, 121, 187);
}

.topbarButton:active{
    background: rgb(1, 1, 150);
}

.icon {
    width: 75px;
    height: 75px;
}

.settingsButton {
    margin: 0px 10px;
    padding: 10px 0px;
}

#homeIcon {
    background: url('@/assets/Home.svg') no-repeat center;
}
#userIcon {
    background: url('@/assets/User.svg') no-repeat center;
}
#settingsIcon {
    background: url('@/assets/Settings.svg') no-repeat center;
}
#settings {
    position: relative;
}
#settingsMenu {
    position: absolute;
    border-radius: 10px;
    right: calc((100% - 300px)/6);
    top: 85%;
    width: 200px;
    height: 300px;
    background-color: rgba(135, 135, 135, 0.98);
    display: flex;
    flex-direction: column;
    justify-content: space-around;
}


</style>