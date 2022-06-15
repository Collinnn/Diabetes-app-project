<template>
    <title>Graph page</title>
    <div>
        <h1>Overview Page</h1>
    </div>    
</template>

<script>

export default {
    name: "overviewPage",
    emits: ['logIn','logInasDoctor','logInasAdmin'],
    props:{
        User:{
            id:Number,
            Name:String,
            Doctor:String,
        }
    }

}
</script>

<style>
</style>