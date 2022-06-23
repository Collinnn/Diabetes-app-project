<!--
 * Responsible author: Jacob Martens
 * Contributors:
-->
<template>
    <title>Graph page</title>
    <ModalBox v-if="isModalVisible" @close="isModalVisible = false" >
        <template #heading>
            <h1> {{modalHeading}} </h1>
        </template>
        <template #body>
            <p> {{modalBody}} </p>
        </template>
    </ModalBox>
    <div class="graph-container">
        <GlucoseGraph :patientId="userId" @low-blood-sugar="showLowBloodSugarMessage" @high-blood-sugar="showHighBloodSugarMessage"/>
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
            modalBody: "",
            userId: this.$userController.getUserData().id
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
.graph-container {
    padding: 10px;
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 80%;
    width: 80%;
    border: solid;
    border-width: 2px;
    border-color: black;
    border-radius: 10px;
}

</style>