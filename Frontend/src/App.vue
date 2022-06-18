<template>
    <router-view />
</template>

<script>



export default {
    name: 'App',  
    //Remember theme of the user
    mounted() {
      this.$userController.setDarkTheme(this.getMediaPreference());
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
            document.getElementById('app').style.setProperty("--primary-color", '#EBEBF2');
            document.getElementById('app').style.setProperty("--secondary-color",'#6295D9');
            document.getElementById('app').style.setProperty("--accent-color", '#A0C4F2');
            document.getElementById('app').style.setProperty("--variant-color", '');
            document.getElementById('app').style.setProperty("--text-color", '#2c3e50');
          }
      },
      getMediaPreference() {
        console.log("Gets user preference");
        const hasDarkPreference = window.matchMedia("(prefers-color-scheme: dark)").matches;
        console.log(hasDarkPreference)
        return hasDarkPreference
      },
      getTheme(){
        return localStorage.getItem("user-theme");
      }
    }
}
</script>

<style>
#app {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: var(--text-color);
  background-color: var(--primary-color);
}

.viewContainer { /* Applies to all viewContainers (in all views) */
  position: relative;
}

</style>