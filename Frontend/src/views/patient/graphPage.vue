<template>
    <title>Graph page</title>
    <div class="page-container">
        <ModalBox v-if="isModalVisible" @close="isModalVisible = false" >
            <template #heading>
                <h1> {{modalHeading}} </h1>
            </template>
            <template #body>
                <p> {{modalBody}} </p>
            </template>
        </ModalBox>
        <GlucoseGraph @low-blood-sugar="showLowBloodSugarMessage" @high-blood-sugar="showHighBloodSugarMessage"/>
    </div>    
</template>


<script>
import GlucoseGraph from '@/components/GlucoseGraph.vue';
import ModalBox from "@/components/Modal.vue"

export default {
    name: "graphPage",
    components: { GlucoseGraph, ModalBox },
    data() {
        return {
            isModalVisible: false,
            modalHeading: "",
            modalBody: ""
        }
    },
    methods: {
        showLowBloodSugarMessage(glucoseLevel) {
            this.isModalVisible = true
            this.modalHeading = "❗Alert: Low blood sugar❗"
            this.modalBody = "Your blood sugar has reached a low level of " + glucoseLevel + " mmol/L"
        },
        showHighBloodSugarMessage(glucoseLevel) {
            this.isModalVisible = true
            this.modalHeading = "❗Alert: High blood sugar❗"
            this.modalBody = "Your blood sugar has reached a high level of " + glucoseLevel + " mmol/L"

        }
    }
}

</script>

<style>
.page-container {
    position: absolute;
    padding: 20px;
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
}

</style>