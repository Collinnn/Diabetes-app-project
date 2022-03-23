<template>
    <div class="container">
        <div class="title">
            <h1 @click="$router.push({ name: 'dashboard'})"> {{title}} </h1>
        </div>
        <div class="buttons">
            <router-link to="/pageOne"></router-link>
            <ul>
                <li><button @click="$router.push({ name: 'PageOne'})"> Button 1 </button></li>
                <li><button> Button 2 </button></li>
                <li><button> Button 3 </button></li>
                <li><button> Button 4 </button></li>
            </ul>
        </div>
        <div class="routers">
        </div>   
    </div>
</template>

<script>
    
    export default {
        name: 'SidebarMenu',
        data() {
            return {
                title: 'Sidebar'
            }
        }
    }
</script>

<style scoped>
.container {
    float: left;
    width: 20%;
    min-width: 120px;
    max-width: 150px;
    background-color: rgb(52, 11, 235);
    position: fixed;
    top: 50px;
    left: 0;
    bottom: 0;
    text-align: center;
}   
ul {
    list-style-type: none;
    padding: 0 0 0 0;
    text-align: center;
}
li {
    padding-top: 10%;
}
</style>