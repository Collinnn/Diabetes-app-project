<template>
    <div class ="topbar">
        <div class = "leftElement">
            <button class = "topbarButton" id = "homeButton" @click="goToPage(dashBoard)">
                <svg class="icon" id="homeIcon"></svg>
            </button>
            DiAPPbetes
        </div>
        <div class = "rightElements">
            <button class = "topbarButton" id = "userButton" @click="goToPage(profilePage)">
                <svg class="icon" id="userIcon"></svg>
            </button>
            <button class = "topbarButton" id = "settingsButton">
                <svg class="icon" id="settingsIcon"></svg>
            </button>
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
    height: 50px;
    width: 100%;
    background-color: #0010bb;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
}


.topbar .leftElement {
    float: left;
    padding-left: 20px;
}

.topbar .rightElements {
    float: right;
    margin-right: 20px;
}



.topbarButton {
    cursor: pointer;
    background-color: #5b6be4;
    width: 40px;
    height: 40px;
    border-radius: 10px;
    outline: none;
    border: none;
    
}


.topbarButton:hover{
    background: rgba(255, 255, 255, 0.60);
}

.topbarButton:active{
    background: rgba(100, 100, 255, 0.60);
}


.icon {
    width: 25px;
    height: 25px;
}


#userButton {
    margin-right: 20px;
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