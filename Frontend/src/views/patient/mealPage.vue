<template>
    <title>Meal submission page</title>
    <h1>Meal & Insulin Submission Page</h1>
    <div class="page_container">
        <MeasurementUpdate/>
    </div>



</template>

<script>
import MeasurementUpdate from '@/components/MeasurementUpdate.vue';



export default {
    name: "mealPage",
    components:{
    MeasurementUpdate
}
}

</script>

<style scoped>

.page_container{
    display:flex;
    justify-content: center;
}

</style>
