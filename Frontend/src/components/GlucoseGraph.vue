<template>
<div>
    <h1>HELLO WORLD</h1>
</div>
</template>


<script>
   // import {Line} from 'vue-chartjs'
    //import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from 'chart.js'



    //ChartJS.register(Title,Tooltip,Legend,BarElement,CategoryScale,LinearScale)
    export default{
        name: 'GlucoseGraph',
        componets: {/*Line*/},
        async created(){
        const {data} = this.axios.get(this.$backend.getUrlGetMeasurementsFromPatientById(1))
        .then(response =>{
            console.log(response.data.length);
            console.log(response.data[0])
            for (let i = 0; i < response.data.length; i++) {
                this.glucoseArray[i] = response.data[i].glucoseLevel;
                this.timestampArray[i] = response.data[i].measurementId.timestamp
                console.log(this.glucoseArray[i]);
                console.log(this.timestampArray[i]);
                
            }
        }).catch((error) => console.log(error));
        console.log(data);
        },
        data(){
            return{
                glucoseArray: [],
                timestampArray:[]
            }
        }
    }
</script>

data(){
            return {
                labels: [],
                datasets: [
                    {
                        datapoint: null
                    }
                ]
            }
        },
        computed:{
            labeldata: function(){
                return this.labels;
            },
            chartData: function(){
                return this.datasets;
            }
        },
        methods: {
            renderLineChart:function(){
                this.renderChart(
                    {
                        labels: this.labeldata,

                    }
                )
            }
        }



<style scoped>

</style>