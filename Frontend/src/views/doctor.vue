<template>
    <title>Doctor's page</title>
    <h1>Doctor Page</h1>
</template>


<script>
export default {
    name: "doctorPage",
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