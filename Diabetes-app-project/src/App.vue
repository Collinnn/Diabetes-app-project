<template>
  <div id="app">
    <router-link to='/'></router-link>
    <router-view  />
  </div>
</template>

<script>
    export default {
        name: 'App',
        component: {

        }
    }
    
</script>

<style>
    body {
        background-color: #ffffff;
    }
</style>