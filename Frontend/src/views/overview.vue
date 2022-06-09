<template>
    <title>Graph page</title>
    <div>
        <h1>Overview Page</h1>
        <DoughnutChart ref="donut" :chart-data="chartData"/>
    </div>    
</template>


<script>

import { DoughnutChart } from "vue-chart-3"
import { Chart, registerables } from 'chart.js'

Chart.register(...registerables)
export default {
    name: "overviewPage",
    components: { DoughnutChart },
    data() {
        return {
            chartData: {
                labels: ["Jacob", "Simon", "Tobias", "Zwinge"],
                datasets: [{
                    data: [50, 40, 30, 20],
                    backgroundColor: ['#77CEFF', '#0079AF', '#123E6B', '#97B0C4']
                }]
            }
        }
    }
}

</script>