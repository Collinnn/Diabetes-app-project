<template>
  <div id="appLayout" v-if="$route.name !== 'login'">
    <Sidebar />
  </div>
  
  <div id="app">
    <router-link to='/'></router-link>
    <router-view  />
  </div>
</template>

<script>
import Sidebar from "./components/Sidebar.vue"

export default {
    name: 'App',
    components: {
    Sidebar
    }
}
    
</script>

<style scoped>
 /* For the entire app*/
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>