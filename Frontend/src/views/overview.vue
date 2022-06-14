<template>
    <title>Graph page</title>
    <div>
        <h1>Overview Page</h1>
        <div class="graph">
            <GlucoseGraph/>
        </div>
    </div>    
</template>


<script>
import GlucoseGraph from '@/components/GlucoseGraph.vue';

export default {
    name: "overviewPage",
    emits: ["logIn"],
    components: { GlucoseGraph }
}

</script>