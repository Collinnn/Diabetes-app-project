<template>
    <title>User Page</title>
    <div class="content">
        <div id="container">
            <h3>User Page</h3>
            <div id="id">User id: {{this.User.id}}</div>
            <div id="id">User name: {{this.User.Name}}</div>
            <div id="id">Doctor's name: {{this.User.Doctor}}</div>
            <button class="settingsButton" @click="goToPage('changePassword')"> Change password </button>
        </div>
    </div>
</template>


<script>
import router from "@/router"

export default {
    name: "userPage",
    emits: ['logIn','logInasDoctor','logInasAdmin'],
    props:{
        User:{
            id:Number,
            Name:String,
            Doctor:String,
        }
    },
    methods:{
        goToPage(pageName) {
            router.push({ name: pageName})
        }
    }
}

</script>

<style>
.content {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}
#container {
    width: 400px;
    border-radius: 20px;
    background-color: rgb(192, 192, 192);
    padding: 10px;
}
</style>