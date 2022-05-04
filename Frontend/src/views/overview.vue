<template>
    <div>
        <h1>Overview Page</h1>
        <DoctorForm />       
    </div>    
</template>


<script>
import DoctorForm from "@/components/DoctorForm.vue";

export default {
    name: "overviewPage",
    components: { DoctorForm }
}

</script>