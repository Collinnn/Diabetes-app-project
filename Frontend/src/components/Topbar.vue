<template>
    <div class ="topbar">
        <div class = "leftElement">
            <button class = "topbarButton" id = "homeButton" @click="goToPage('overview')">
                <svg class="icon" id="homeIcon"></svg>
            </button>
            DiAPPbetes
        </div>
       <div class = "rightElements">
            
            <div class="mode-toggle" @click="modeToggle()">
                <div id="sun" v-if="this.darkMode">
                    <div class="toggle">
                        <div id="dark-mode" type="checkbox"></div>
                    </div>
                </div>
                <div id="moon" v-if="!this.darkMode">
                    <div class="toggleRight">
                        <div id="light-mode" type="checkbox"></div>
                    </div>
                </div>
            </div>

            <div id = "rightbutton">
                <button class = "topbarButton" id = "userButton" @click="showDropdown()">
                    <svg class="icon" id="userIcon"></svg>
                </button>
            </div>

        </div>
    </div>

</template>

<script>

    import router from "@/router"

    export default {
        emits:['darkMode','showDropdown'],
        name: "TopbarMenu",
        data() {
            return {
                title: 'Sidebar',
                darkMode: false, 
                settings: [
                    {
                        title: 'Interface settings', 
                        link: '#'
                    },
                    {
                        title: 'DarkMode', 
                        link: '#'
                    },
                    {
                        title: 'Advanced settings', 
                        link: '#'
                    }
                ],
                settingsMenu: {
                    isVisible: false
                }
            }
        },
        methods: {
            goToPage(pageName) {
                router.push({ name: pageName})
            },
            modeToggle() {
            this.$emit('darkMode');
            if(this.darkMode) {
                this.darkMode = false;
            } else {
                this.darkMode = true;
            }
            },
            showDropdown(){
                this.$emit('showDropdown')
                console.log("Trying to display dropdown")
            },
        }
    }
</script>

<style scoped>
.topbar {
    position: fixed;
    top: 0;
    right: 0;
    left: 0;
    height: 50px;
    width: 100%;
    background-color: var(--secondary-color);
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    color: var(--text-color);
}
.topbar .leftElement {
    float: left;
    padding-left: 20px;
}
.topbar .rightElements {
    display: flex;
    gap: 15px;
    align-items: center;
    float: right;
    margin-right: 20px;
}
.topbarButton {
    cursor: pointer;
    background-color: var(--accent-color);
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
.mode-toggle {
    width: 44px;
    height: 24px;
    min-width: 36px;
    min-height: 20px;
    border-radius: 24px;
    background-color: #2c3e50;
    display: block;
    z-index: 2;
    -webkit-tap-highlight-color: rgba(0,0,0,0);
    -webkit-touch-callout: none;

    cursor: pointer;
}
.toggle {
    position: relative;
    width: 20px;
    height: 20px;
    border-radius: 50%;
    border: 3px solid transparent;
    box-shadow: inset 0 0 0 2px #a5abba;
    overflow: hidden;
    transform: .5s ease;
}

.toggleRight{
    position: relative;
    width: 20px;
    height: 20px;
    border-radius: 50%;
    border: 3px solid transparent;
    box-shadow: inset 0 0 0 2px #a5abba;
    overflow: hidden;
    transform: .5s ease;
    cursor: pointer;
    float: right;
}


#light-mode {
    position: relative;
    width: 100%;
    height: 100%;
    overflow: hidden;
    left: 50%;
    background-color: #a5abba;
    
    
}        


#dark-mode{
    content: '';
    position: absolute;
    overflow: hidden;
    border-radius: 50%;
    left: 50%;
    float: left;
    background-color: #a5abba;
    transition: border-radius .5s ease, width .5s ease, height .5s ease, left .5s ease, transform .5s ease;
    border-radius: 50%;
    width: 150%;
    height: 85%;
    left: 40%;
    transform: translate(-10%, -40%), rotate(-35deg);
}        


</style>



