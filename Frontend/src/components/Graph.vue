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
      totalDataPoints: 0,
      glucoseLevels: [],
      glucoseLevelsPerTimestamp: [], // Array of arrays with glucose levels for each minute in measurements data
      lowerConfidenceLevels: [],
      upperConfidenceLevels: [],
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
    this.initializeData()
    this.timer = setInterval(async () => {
      this.initializeData()
      //this.updateData()
    }, 30000) // Every 30 sec
  },
  beforeUnmount() {
    clearInterval(this.timer)
  },
  methods: {
    async initializeData() {
      let data = await this.fetchData()
      this.totalDataPoints = data.length
      await this.initializeGraphData(data)
      await this.initializeGlucoseLevelsPerTimestamp(data)
      await this.initializeConfidenceIntervals()
      
    },
    async fetchData() {
      let data;
      await this.axios.get(this.$backend.getUrlGetMeasurementsFromPatientById(1))
       .then( response => {
          data = response.data
       })
      return data
    },
    async initializeGraphData(data) {
      let tmpDate;
      let today = new Date()
      for (let i = 0; i < data.length; i++) {
          tmpDate = data[data.length-1-i].measurementId.timestamp;
          if (today.getTime() - 2*24*60*60*1000 > this.dateTimeHandling(tmpDate).getTime()) {
              break
          }
          this.timestamps[i] = this.dateToString(this.dateTimeHandling(tmpDate))
          this.glucoseLevels[i] = data[i].glucoseLevel
      }
      this.timestamps.reverse()
    },
    async initializeGlucoseLevelsPerTimestamp(data) {
      let timeStamp;
      this.glucoseLevelsPerTimestamp = data.reduce( (res, curr) => {
        timeStamp = this.dateTimeHandling(curr.measurementId.timeStamp) 
        if (res[timeStamp] === undefined) {
          res[timeStamp] = [curr.glucoseLevel]
        } else {
          res[timeStamp].push(curr.glucoseLevel)
        }
        return res
      }, [])
    },
    async initializeConfidenceIntervals() {
      let sampleSize, sampleMean, sampleVariance, sampleStandardDeviation;
      let meanDeviation;
      this.glucoseLevelsPerTimestamp.forEach( (sample) => {
        sampleSize = sample.length 
        sampleMean = sample.reduce((res, curr) => res + curr.glucoseLevel, 0) / sampleSize
        sampleVariance = sample.reduce((res, curr) => res + (curr.glucoseLevel - sampleMean)**2, 0) / (sampleSize - 1)
        sampleStandardDeviation = Math.sqrt(sampleVariance)
        meanDeviation = 1.96 * sampleStandardDeviation / Math.sqrt(sampleSize)
        this.lowerConfidenceLevels.push(sampleMean - meanDeviation)
        this.upperConfidenceLevels.push(sampleMean + meanDeviation)
      })
    },
    updateData() {
      let data = this.fetchData()
      if (data.length > this.totalDataPoints) {
        // fetch new datapoint and update all arrays
        return
      }
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
  position: relative;
  width: 75%;
  height: 75%;
  background-color: var(--secondary-color);
}

</style>