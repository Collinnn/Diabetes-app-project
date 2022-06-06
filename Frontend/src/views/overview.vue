<template>
    <div>
        <h1>Overview Page</h1>
        <DoctorForm />
        <ModalBox />   
    </div>    
</template>


<script>
import DoctorForm from "@/components/DoctorForm.vue";
import ModalBox from "@/components/Modal.vue"

export default {
    name: "overviewPage",
    components: { DoctorForm, ModalBox }
}

</script>