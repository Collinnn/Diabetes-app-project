<template>
    <div class="container">
        <h1>Overview Page</h1>
        <PatientForm />
        <DoctorForm />
    </div>    
</template>


<script>
import DoctorForm from "@/components/DoctorForm.vue"
import PatientForm from "@/components/PatientForm.vue"

export default {
    name: "overviewPage",
    components: { DoctorForm, PatientForm }
}

</script>

<style>
.container {
    flex-direction: column;   
    justify-content: space-around;
    align-items: center;
    width: 100%;
}

</style>