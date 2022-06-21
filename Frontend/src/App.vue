<template>
  <div class="page-wrapper">
      <router-view />
  </div>
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
            console.log("Dark theme enabled");
            document.getElementById('app').style.setProperty("--primary-color",'#424242');
            document.getElementById('app').style.setProperty("--secondary-color", '#212121');
            document.getElementById('app').style.setProperty("--accent-color", '#747474');
            document.getElementById('app').style.setProperty("--highlight-color", '#B4B4B4');
            document.getElementById('app').style.setProperty("--strong-text-color", '#911d1d');
            document.getElementById('app').style.setProperty("--text-color", '#DDDDDD');

          } else {
            console.log("Light theme enabled");
            document.getElementById('app').style.setProperty("--primary-color", '#EBEBF2');
            document.getElementById('app').style.setProperty("--secondary-color",'#6295D9');
            document.getElementById('app').style.setProperty("--accent-color", '#b1b8e3');
            document.getElementById('app').style.setProperty("--highlight-color", '#EEF');
            document.getElementById('app').style.setProperty("--strong-text-color", '#e00d0d');
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

<style> /* Applies to all pages (in all views) */
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
.page-container {  
    position: absolute;
    top: var(--topbar-height);
    left: var(--sidebar-max-width);
    width: 100%;
    height: 100%;
}
.page-wrapper {
    position: relative;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: calc(100vw - var(--sidebar-max-width));
    height: calc(100vh - var(--topbar-height));
}
.large-logo {
  height: 300px;
  background: url('@/assets/Home.svg') no-repeat center/300px 300px;
  padding-bottom: 30px;
}

</style>