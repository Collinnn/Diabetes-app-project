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
        data(){
            return{
                glucoseArray: [],
                timestampArray:[]
            }
        },
        async mounted(){
            console.log('We got it mounted boys')
            console.log(document.getElementById('app').style.getPropertyValue("--primary-color"))
            console.log(document.getElementById('app').style.getPropertyValue("--text-color"))
            await this.axios.get(this.$backend.getUrlGetMeasurementsFromPatientById(1))
            .then(response =>{
                let tmpDate;
                let today = new Date()
                for (let i = 0; i < response.data.length; i++) {
                    tmpDate = response.data[response.data.length-1-i].measurementId.timestamp;
                    if(today.getTime() - 2*24*60*60*1000 > this.dateTimeHandling(tmpDate).getTime()){
                    break;
                    }
                    this.timestampArray[i] = this.dateToString(this.dateTimeHandling(tmpDate));
                    this.glucoseArray[i] = response.data[i].glucoseLevel;
                }
                this.glucoseArray.reverse();
                this.timestampArray.reverse();



            }).catch((error) => console.log(error));
            

            const ctx = document.getElementById('glucoseChart');
            const glucoseChart = new Chart(ctx,{
                type: 'line',
                data: {
                    labels: this.timestampArray,
                    datasets: [{
                        label: 'datasetTesting',
                        data: this.glucoseArray,
                        borderColor: ['#6295D9'],
                        tension: 0.1
                    }]
                },
                options: {
                    plugins:{
                        legend:{
                            labels:{
                                color: document.getElementById('app').style.getPropertyValue("--text-color")
                            }
                        }
                    },
                    scales: {
                        y: {
                            beginAtZero: true,
                             color: "purple"
                        },
                        x:{
                            ticks:{
                                color: document.getElementById('app').style.getPropertyValue("--text-color")
                            }
                            
                        }
                    }
                }
            });
            glucoseChart;   
        },
        methods:{
            dateTimeHandling: function(tmpDate){
                let dateTime = new Date();
                const [date, time] = tmpDate.split(' ');
                const [year, month, day] = date.split('-');
                const[hour, minute, second] = time.split(':')
                dateTime.setUTCFullYear(year);
                dateTime.setUTCMonth((month-1));
                dateTime.setUTCDate(day);
                dateTime.setUTCHours(hour);
                dateTime.setUTCMinutes(minute);
                dateTime.setUTCSeconds(second);
                return dateTime;
            },
            dateToString: function(date){
                let tmp;
                tmp = date.toISOString().split('T');
                tmp = tmp[1].split('.');
                return tmp[0]
            }
        }
    };
</script>





<style scoped>




</style>