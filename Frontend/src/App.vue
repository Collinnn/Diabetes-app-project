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
        console.log(this.darktheme);
          if(this.darktheme){
            console.log("dark-theme");
            this.darktheme=false; 
            this.primarycolor   = 'grey'; 
            this.secondarycolor = 'white';
            this.accentcolor    = 'white';
            document.getElementById('app').style.setProperty("--primary-color", this.primarycolor);
            document.getElementById('app').style.setProperty("--secondary-color", this.secondarycolor);
            document.getElementById('app').style.setProperty("--accent-color",this.accentcolor);

          } else {
            console.log("light-theme");
            this.darktheme=true; 
            this.primarycolor   = 'white'; 
            this.secondarycolor = 'white';
            this.accentcolor    = 'white';
            document.getElementById('app').style.setProperty("--primary-color", this.primarycolor);
            document.getElementById('app').style.setProperty("--secondary-color", this.secondarycolor);
            document.getElementById('app').style.setProperty("--accent-color",this.accentcolor);

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
  color: #2c3e50;
  background-color: var(--primary-color);
}


</style>