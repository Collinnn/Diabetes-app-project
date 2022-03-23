<template>
  <Topbar />
  <div id="app">
    <router-link to='/'></router-link>
    <router-view  />
  </div>
</template>

<script>
import Topbar from "./components/Topbar.vue"
export default {
    name: 'App',
    components: {
    Topbar
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