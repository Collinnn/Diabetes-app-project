<template>
    <title> Add patient </title>
    <div class="container">
        <PatientForm />
    </div>
</template>

<script>

import PatientForm from '@/components/PatientForm.vue'

export default {
    name: "adminPage",
    emits: ['logIn'],
    props:{
        User:{
            id:Number,
            Name:String,
            Doctor:String,
        }
    },
    components: {
        PatientForm
    }
}

</script>

<style scoped>
.container {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
