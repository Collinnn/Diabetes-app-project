<template>
    <h1>Overview Page</h1>
    <DoctorForm/>
    <PatientForm/>
</template>

<script>
import DoctorForm from "@/components/DoctorForm"
import PatientForm from "@/components/PatientForm"

export default {
    name: "overviewPage",
    components: {DoctorForm, PatientForm}
}
</script>

<style>
</style>