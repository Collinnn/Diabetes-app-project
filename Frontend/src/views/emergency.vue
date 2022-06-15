<template>
    <title>Emergency page</title>
    <h1>Emergency Page</h1>
</template>

<script>
export default {
    name: "emergencyPage",
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