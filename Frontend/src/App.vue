<template>
  <div>
    <div id="appLayout" v-if="$route.name !== 'login'">
      <Topbar />
      <AdminSidebar />
    </div>
  
    <div id="app">
      <router-link to='/'></router-link>
      <router-view  />
    </div>
  </div>
</template>

<script>
import Topbar from "./components/Topbar.vue"
import AdminSidebar from "./components/AdminSidebar.vue"
export default {
    name: 'App',
    components: {
    Topbar,
    AdminSidebar
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