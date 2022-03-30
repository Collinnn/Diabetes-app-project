<template>
    <div class ="topbar">
        <button class = "topbarButton" id = "homeButton" @click="goToPage(dashBoard)">
            <span class="icon" id="homeIcon"></span>
        </button>
        <button class = "topbarButton" id = "userButton" @click="goToPage(profilePage)">
            <span class="icon" id="userIcon"></span>
        </button>
        <button class = "topbarButton" id = "settingsButton">
            <span class="icon" id="settingsIcon"></span>
        </button>
        <div class="sub-menu">
                <div v-for= "(setting,i) in settings"  :key="i" class="menu-item">
                    {{ setting.title}}
                </div>
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
    bottom: 0;
    left: 0;
    height: 15%;
    width: 100%;
    background: rgb(34, 8, 185);
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    padding-top: 1%;
}



.topbarButton {
    background-color: rgb(0, 60, 255);
    width: 100px;
    height: 100px;
    border-radius: 10px;

}


.topbarButton:hover{
    background: rgb(106, 121, 187);
}

.topbarButton:active{
    background: rgb(1, 1, 150);
}



.icon {
    padding: 30px;
}

#settings {
    float: right;
}
h2 {
    text-align: left;
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




</style>