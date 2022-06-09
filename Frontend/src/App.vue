<template>
  <div>
    <div id="appLayout" v-if="app.loggedIn">
      <Topbar @showDropdown="showDropdown.isVisible=!showDropdown.isVisible" />
      <Sidebar />
    </div>
  
    <div id="app">
      <router-link to='/'></router-link>
      <router-view @logIn="logIn()" />
    </div>

    <div id=showDropdown v-if="showDropdown.isVisible">
        <ProfileDropdown @logOut="logOut()"/>
    </div>
  </div>
</template>

<script>
import Topbar from "./components/Topbar.vue"
import Sidebar from "./components/Sidebar.vue"
import ProfileDropdown from "./components/ProfileDropdown.vue"
import {loggedInStatus} from "./variables.js"
export default {
    name: 'App',
    data(){
      return{
        showDropdown:{
          isVisible: false
        },
        app:{
          loggedIn: loggedInStatus.getStatus
        }
      }
    },
    components: {
    Topbar,
    Sidebar,
    ProfileDropdown
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
  color: #2c3e50;
}
</style>