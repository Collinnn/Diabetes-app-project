<template>
  <div id="appcontainer">
    <div id="appLayout" v-if="app.loggedIn">
      <Topbar @showDropdown="showDropdown.isVisible=!showDropdown.isVisible" @darkMode="toggleDarkmode()" />
      <AdminSidebar />
    </div>

    <div id="app">
      <router-link to='/'></router-link>
      <router-view :User="User" @logIn="logIn()" />
    </div>

    <div id=showDropdown v-if="showDropdown.isVisible">
        <ProfileDropdown @logOut="logOut()"/>
    </div>
  </div>
</template>

<script>
import Topbar from "./components/Topbar.vue"
import AdminSidebar from "./components/AdminSidebar.vue"
import ProfileDropdown from "./components/ProfileDropdown.vue"
import {loggedInStatus} from "./variables.js"
export default {
    name: 'App',  
    components: {
    Topbar,
    AdminSidebar,
    ProfileDropdown
    },
    //Remember theme of the user
    mounted(){
      this.darktheme= this.getMediaPreference();
      this.toggleDarkmode();
     
    },
    data(){
      return{
        showDropdown:{
          isVisible: false
        },
        app:{
          loggedIn: loggedInStatus.getStatus
        },
        User:{
            id: 0,
            Name: "Full name",
            Doctor:"Doctor name",
        },
        darktheme: null,
      }
    },
    methods:{
      logIn(){
        this.app.loggedIn = true
        loggedInStatus.setLoggedIn = true;
      },
      logOut(){
        this.showDropdown.isVisible = false;
        this.app.loggedIn = false
        loggedInStatus.setLoggedIn = false;
      },
      toggleDarkmode: function(){
          if(this.darktheme){
            console.log("dark-theme");
            this.darktheme=false; 
            this.primarycolor   = '#424242'; 
            this.secondarycolor = '#212121';
            this.accentcolor    = '#747474';
            this.variantcolor   = '';
            this.textcolor      = '#DDDDDD';
            document.getElementById('app').style.setProperty("--primary-color", this.primarycolor);
            document.getElementById('app').style.setProperty("--secondary-color", this.secondarycolor);
            document.getElementById('app').style.setProperty("--accent-color", this.accentcolor);
            document.getElementById('app').style.setProperty("--variant-color", this.variantcolor);
            document.getElementById('app').style.setProperty("--text-color", this.textcolor);

          } else {
            console.log("light-theme");
            this.darktheme=true; 
            this.primarycolor   = '#EBEBF2'; 
            this.secondarycolor = '#6295D9';
            this.accentcolor    = '#A0C4F2';
            this.variantcolor   = '';
            this.textcolor      = '#2c3e50';
            document.getElementById('app').style.setProperty("--primary-color", this.primarycolor);
            document.getElementById('app').style.setProperty("--secondary-color", this.secondarycolor);
            document.getElementById('app').style.setProperty("--accent-color", this.accentcolor);
            document.getElementById('app').style.setProperty("--variant-color", this.variantcolor);
            document.getElementById('app').style.setProperty("--text-color", this.textcolor);

          }
      },
      getMediaPreference() {
        console.log("Gets user preference");
        const hasDarkPreference = window.matchMedia("(prefers-color-scheme: dark)").matches;
        return hasDarkPreference
      },
      getTheme(){
        return localStorage.getItem("user-theme");
      }
    }
}
</script>

<style scoped>
 /* For the entire app*/
#app {
  position: fixed;
  top: 50px;
  left: 150px;
  right: 0;
  bottom: 0;
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: var(--text-color);
  
}
#appcontainer{
  position: fixed;
  top:0;
  left:0;
  width: 100%;
  height: 100%;
  background-color: var(--primary-color);
}



</style>