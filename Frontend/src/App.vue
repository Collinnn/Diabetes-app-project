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
    methods: {
      toggleDarkmode() {
          if(this.$userController.getDarkTheme()){
            console.log("dark-theme"); 
            document.getElementById('app').style.setProperty("--primary-color",'#424242');
            document.getElementById('app').style.setProperty("--secondary-color", '#212121');
            document.getElementById('app').style.setProperty("--accent-color", '#747474');
            document.getElementById('app').style.setProperty("--variant-color", '');
            document.getElementById('app').style.setProperty("--highlight-color", '');
            document.getElementById('app').style.setProperty("--text-color", '#DDDDDD');

          } else {
            console.log("light-theme"); 
            document.getElementById('app').style.setProperty("--primary-color", '#F0F0F2');
            document.getElementById('app').style.setProperty("--secondary-color",'#0367A6');
            document.getElementById('app').style.setProperty("--accent-color", '#c5ccfa');
            document.getElementById('app').style.setProperty("--variant-color", '#50F2E2');
            document.getElementById('app').style.setProperty("--highlight-color", '#EEF');
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