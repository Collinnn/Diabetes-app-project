<template>
<div>
    <h1>HELLO WORLD</h1>
    <canvas id="glucoseChart" width="400" height="400"></canvas>
</div>
</template>


<script>
    import {Line} from 'vue-chartjs'
    import { Chart,registerables } from 'chart.js'
    Chart.register(...registerables)
    export default {
        name: 'GlucoseGraph',
        componets: {Line},

        async created(){
            this.axios.get(this.$backend.getUrlGetMeasurementsFromPatientById(1))
            .then(response =>{



                for (let i = 0; i < response.data.length; i++) {
                    this.glucoseArray[i] = response.data[i].glucoseLevel;
                    this.timestampArray[i] = response.data[i].measurementId.timestamp;
                }




            }).catch((error) => console.log(error));
        },
        data(){
            return{
                glucoseArray: [],
                timestampArray:[],
                average: 0.0,
                variance: 0.0
            }
        },
        mounted(){
            console.log('We got it mounted boys')
            const ctx = document.getElementById('glucoseChart');
            const glucoseChart = new Chart(ctx,{
                type: 'line',
                data: {
                    labels: this.timestampArray,
                    datasets: [{
                        label: 'datasetTesting',
                        data: this.glucoseArray,
                        borderColor: ['rgb(75, 192, 192)'],
                        tension: 0.1
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
            glucoseChart;

        }
    };
</script>





<style scoped>

</style>