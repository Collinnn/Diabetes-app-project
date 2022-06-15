<template>
    <title>Food submission page</title>
    <h1>Food Page</h1>
</template>

<script>
export default {
    name: "foodPage",
    emits: ['logIn'],
    props:{
        User:{
            id:Number,
            Name:String,
            Doctor:String,
        }
    }
}

</script>