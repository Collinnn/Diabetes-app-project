 <!-- Responsible author: Christopher Zwinge
 * Contributors: Tobias Collin contributed with darkmode
 -->

<template>
    <div class ="topbar">
        <div class = "leftElement">
            <button class = "topbarButton" @click="this.$router.push('/')">
                 <svg class="iconLeft" id="homeIcon"></svg>
            </button>
            <h3>DiAPPbetes</h3>
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

            <button class = "topbarButton" id = "userButton" v-if="!(this.$userController.getUserType() == 'admin')" @click="showDropdown()">
                <svg class="icon" id="userIcon"></svg>
            </button>
            <div id = "adminLogout" v-else>
                <h3>Logged in as admin</h3>
                <button id = "adminButton" @click="logOut('landing')"> Log out </button>
            </div>
        </div>
    </div>
    <div id="showDropdown" v-if="settingsMenu.isVisible" >
        <ProfileDropdown />
    </div>
</template>

<script>
import ProfileDropdown from "./ProfileDropdown.vue"

    export default {
        name: "TopbarMenu",
        components: {
           ProfileDropdown
        },
        data() {
            return {
                title: 'Sidebar', 
                darkMode: this.$userController.getDarkTheme(),
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
                this.$router.push({name: pageName})
            },
            modeToggle() {
            this.$userController.setDarkTheme(!(this.$userController.getDarkTheme()));
            this.darkMode = this.$userController.getDarkTheme();
            if(this.darkMode) {
                console.log("Dark theme enabled");
                document.getElementById('app').style.setProperty("--primary-color",'#424242');
                document.getElementById('app').style.setProperty("--secondary-color", '#212121');
                document.getElementById('app').style.setProperty("--accent-color", '#747474');
                document.getElementById('app').style.setProperty("--highlight-color", '#B4B4B4');
                document.getElementById('app').style.setProperty("--strong-text-color", '#911d1d');
                document.getElementById('app').style.setProperty("--text-color", '#DDDDDD');
            } else {
                console.log("Light theme enabled");
                document.getElementById('app').style.setProperty("--primary-color", '#EBEBF2');
                document.getElementById('app').style.setProperty("--secondary-color",'#6295D9');
                document.getElementById('app').style.setProperty("--accent-color", '#b1b8e3');
                document.getElementById('app').style.setProperty("--highlight-color", '#EEF');
                document.getElementById('app').style.setProperty("--strong-text-color", '#e00d0d');
                document.getElementById('app').style.setProperty("--text-color", '#2c3e50');
            }
            },
            showDropdown(){
                this.settingsMenu.isVisible = !this.settingsMenu.isVisible
            },
            logOut(pageName){
                this.$router.push({name: pageName});
                this.$userController.logOut()
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
    height: var(--topbar-height);
    width: 100%;
    background-color: var(--secondary-color);
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    color: var(--text-color);
}
.topbar .leftElement {
    display: flex;
    float: left;
    padding-left: 20px;
    align-items: center;
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
    margin-right: 5px;
    
}
.topbarButton:hover{
    background: var(--highlight-color);
}
.topbarButton:active{
    background: rgba(110, 110, 150, 0.8);
}
.icon {
    width: 25px;
    height: 25px;
}
.iconLeft {
    width: 30px;
    height: 30px;
}

#userButton {
    margin-right: 20px;
}
#homeIcon {
    display: block;
    background: url('@/assets/Home.svg') no-repeat center;
    background-size: 30px 30px;

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

#adminLogout{
    display:flex;
    align-items: center;
}        

#adminButton{
    margin-left: 5px;
    width: fit-content;
    height: 30px;
    border-radius: 5px;
    border: none;
    background-color: var(--accent-color);
    cursor: pointer;
}
#adminButton:hover {
    background-color: var(--highlight-color);
}
#adminButton:active {
    background-color: rgba(110, 110, 150, 0.8);
}


</style>



