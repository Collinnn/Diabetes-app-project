<template>
    <div>
        <h1>Overview Page</h1>
        <ModalBox v-if="this.isModalVisible" @close="this.isModalVisible = false" > </ModalBox>
        <button @click="this.isModalVisible = true"> Click to open modal </button>
    </div>    
</template>


<script>
import ModalBox from "@/components/Modal.vue"

export default {
    name: "overviewPage",
    components: { ModalBox },
    data() {
        return {
            isModalVisible: false
        }
    }
}

</script>