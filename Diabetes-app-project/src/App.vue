<script setup>
//import HelloWorld from './components/HelloWorld.vue'
//import TheWelcome from './components/TheWelcome.vue'
</script>

<template>
  <header>
    <h1>Hello world, this is the start of something great</h1>
    
  </header>

  <main>
    
  </main>
</template>

<style>
@import './assets/base.css';

header {
  line-height: 1.5;
}

@media (min-width: 1024px) {

  header {
    display: flex;
    place-items: center;
    margin-left: 500px;
    padding: 200 px;
    padding-right: calc(var(--section-gap) / 2);
  }
  

}
</style>
