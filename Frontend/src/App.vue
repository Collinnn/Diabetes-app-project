<template>
  <div>
    <div id="appLayout" v-if="$route.name !== 'login'">
      <Topbar @darkMode="toggleDarkmode()" />
      <Sidebar />
    </div>
  
    <div id="app">
      <router-link to='/'></router-link>
      <router-view  />
    </div>
  </div>
</template>

<script>
import Topbar from "./components/Topbar.vue"
import Sidebar from "./components/Sidebar.vue"
export default {
    name: 'App',  
    components: {
    Topbar,
    Sidebar
    },
    //Remember theme of the user
    mounted(){
      this.darktheme= this.getMediaPreference();
      this.toggleDarkmode();
     
    },
    data(){
      return{
        darktheme: null,
      };
    },
    methods: {
      toggleDarkmode: function(){
          if(this.darktheme){
            console.log("dark-theme");
            this.darktheme=false; 
            this.primarycolor   = '#101010'; 
            this.secondarycolor = '#090909';
            this.accentcolor    = '#747474';
            this.variantcolor   = '';
            this.textcolor      = '#2c3e50';
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
        if (hasDarkPreference) {
          return true;
        } else {
          return false;
        }
      },
      getTheme(){
        return localStorage.getItem("user-theme");
      },
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
  background-color: var(--primary-color);
}


</style>