<template>
    <title>Graph page</title>
    <div>
        <h1>Graph Page</h1>
        <ModalBox v-if="isModalVisible" @close="isModalVisible = false" >
            <template #heading>
                <h1> {{modalHeading}} </h1>
            </template>
            <template #body>
                <p> {{modalBody}} </p>
            </template>
        </ModalBox>
        <div class="graph">
            <GlucoseGraph @low-blood-sugar="showLowBloodSugarMessage" @high-blood-sugar="showHighBloodSugarMessage"/>
        </div>
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
.graph {
    display: flex;
    justify-content: center;
}
</style>