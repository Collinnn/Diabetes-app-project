<template>
    <title>Graph page</title>
    <div>
        <h1>Overview Page</h1>
        <div class="graph">
            <GraphChart />
        </div>
    </div>    
</template>

<script>
import GraphChart from '@/components/Graph.vue';

export default {
    name: "overviewPage",
    emits: ["logIn"],
    components: { GraphChart }
}
</script>

</script>

<style>
.graph {
    display: flex;
    justify-content: center;
}
</style>