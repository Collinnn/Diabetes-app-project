<template>
    <title>Graph page</title>
    <div>
        <h1>Overview Page</h1>
        <ModalBox v-if="isModalVisible" @close="isModalVisible = false" > </ModalBox>
        <div class="graph">
            <GraphChart @lowBloodSugar="isModalVisible = true"/>
        </div>
    </div>    
</template>


<script>
import GraphChart from '@/components/Graph.vue';
import ModalBox from "@/components/Modal.vue"

export default {
    name: "overviewPage",
    emits: ["logIn"],
    components: { GraphChart, ModalBox },
    data() {
        return {
            isModalVisible: false
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