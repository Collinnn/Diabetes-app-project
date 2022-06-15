<template>
    <title>Page not found</title>
    <h1>404 page not found😡</h1>
</template>

<script>
export default {
    name: "pageNotFound",
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