<template>
    <div>
        <h1>Overview Page</h1>
        <DoctorForm />
        <ModalBox v-if="this.isModalVisible" @close="this.isModalVisible = false" > </ModalBox>
        <button @click="this.isModalVisible = true"> Click to open modal </button> 
    </div>    
</template>


<script>
import DoctorForm from "@/components/DoctorForm.vue";
import ModalBox from "@/components/Modal.vue"

export default {
    name: "overviewPage",
    components: { DoctorForm, ModalBox },
    data() {
        return {
            isModalVisible: false
        }
    }
}

</script>