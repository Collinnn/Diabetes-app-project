<!--
 * Responsible author: Jacob Martens
 * Contributors:
-->
<template>
    <title> Add doctor </title>
    <div class="container">
        <DoctorForm />
    </div>
</template>

<script>

import DoctorForm from '@/components/DoctorForm.vue'

export default {
    name: "adminPage",
    components: {
        DoctorForm
    }
}

</script>

<style scoped>
.container {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
