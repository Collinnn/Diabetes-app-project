<template>
    <title>Graph page</title>
    <div class="graph-container">
        <GlucoseGraph :patientId="Number(userId)"/>
    </div>   
</template>


<script>
import GlucoseGraph from '@/components/GlucoseGraph.vue';

export default {
    name: "doctorGraphPage",
    components: { GlucoseGraph },
    props: {
        userId: Number
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