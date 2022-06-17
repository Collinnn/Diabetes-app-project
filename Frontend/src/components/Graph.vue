<template>
  <Line
    :chart-options="chartOptions"
    :chart-data="chartData"
    :chart-id="chartId"
    :dataset-id-key="datasetIdKey"
    :plugins="plugins"
    :css-classes="cssClasses"
    :styles="styles"
    :width="width"
    :height="height"
  />
</template>

<script>
import { Line } from 'vue-chartjs'
import { Chart as ChartJS, registerables} from 'chart.js'

ChartJS.register(...registerables)


export default {
  name: 'GraphChart',
  components: { Line },
  data() {
    return {
      glucoseLevels: [],
      upperConfidenceLevels
      timestamps: [],
      timer: null,

      chartOptions: {
        responsive: true,
        plugins: {
          legend: {
            display: false
          }
        }
      },
      chartId: 'glucose-chart',
      datasetIdKey: 'label',
      width: 1200,
      height: 800,
      cssClasses: 'graphContainer',
      styles: {},
      plugins: []
    }
  },
  async mounted() {
    this.loadData()
    this.timer = setInterval(async () => {
      this.loadData()
    }, 60000) // Every 60 sec
  },
  beforeUnmount() {
    clearInterval(this.timer)
  },
  methods: {
    async loadData() {
      let data = await this.getData()
      loadConfidenceIntervals(data)
      let tmpDate;
      let today = new Date()
      for (let i = 0; i < data.length; i++) {
          tmpDate = data[data.length-1-i].measurementId.timestamp;
          if(today.getTime() - 2*24*60*60*1000 > this.dateTimeHandling(tmpDate).getTime()){
              break
          }
          this.timestamps[i] = this.dateToString(this.dateTimeHandling(tmpDate))
          this.glucoseLevels[i] = data[i].glucoseLevel
      }
      this.timestamps.reverse()
    },
    async getData() {
      let data;
      await this.axios.get(this.$backend.getUrlGetMeasurementsFromPatientById(3))
       .then( response => {
          data = response.data
       })
      return data
    },
    loadConfidenceIntervals(data) {

    },
    dateTimeHandling(tmpDate) {
      let dateTime = new Date();
      const [date, time] = tmpDate.split(' ');
      const [year, month, day] = date.split('-');
      const [hour, minute, second] = time.split(':')
      dateTime.setUTCFullYear(year);
      dateTime.setUTCMonth((month-1));
      dateTime.setUTCDate(day);
      dateTime.setUTCHours(hour, minute, second);
      return dateTime;
    },
    dateToString(date) {
      let tmp;
      tmp = date.toISOString().split('T');
      tmp = tmp[1].split('.');
      return tmp[0].substring(0, 5)
    }
  },
  computed: {
    chartData() {
      return {
        labels: this.timestamps,
        datasets: [
          { 
            label: "",
            fill: 2,
            data: this.glucoseLevels.reduce((res, curr) => { res.push(curr - 0.2); return res} , []), 
            backgroundColor: 'rgba(255, 0, 0, 0.2)',
            pointRadius: 0,
            pointHitRadius: 0
          },
          { 
            label: 'Glucose levels',
            fill: 0,
            data: this.glucoseLevels, 
            backgroundColor: 'blue',
            borderColor: 'rgb(75, 192, 192)',
          },
          {
            data: this.glucoseLevels.reduce((res) => { res.push(3); return res} , []),
            pointRadius: 0,
            pointHitRadius: 0
          },
          {
            data: this.glucoseLevels.reduce((res) => { res.push(7); return res} , []),
            fill: 1,
            backgroundColor: 'rgba(0, 255, 0, 0.2)',
            pointRadius: 0,
            pointHitRadius: 0

          }
        ]
      }
    }
  }
  
}
</script>

<style>
.graphContainer {
  background-color: var(--secondary-color);
}

</style>