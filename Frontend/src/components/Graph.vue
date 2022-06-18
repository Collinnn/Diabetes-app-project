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
      sampleGlucoseLevels: [],
      sampleTimestamps: [],
      glucoseLevelsPerDailyTimestamp: [], // Array of arrays with glucose levels for each minute in measurements data
      lowerConfidenceLevels: [],
      upperConfidenceLevels: [],
      
      dataTimeSpan: 2*24*60*60*1000,  // *2 days* in milliseconds
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
    await this.loadData()
    this.timer = setInterval(async () => {
      this.updateData()
      //this.updateData()
    }, 30000) // Every 30 sec
  },
  beforeUnmount() {
    clearInterval(this.timer)
  },
  methods: {
    async loadData() {
      let data = await this.fetchData()
      this.totalDataPoints = data.length
      await this.loadGraphData(data)
      await this.loadGlucoseLevelsPerDailyTimestamp(data)
      await this.loadConfidenceIntervals()
      
    },
    async fetchData() {
      let data;
      await this.axios.get(this.$backend.getUrlGetMeasurementsFromPatientById(1))
       .then( response => {
          data = response.data
       })
      return data
    },
    async loadGraphData(data) {
      let tmpDate;
      let today = new Date()
      for (let i = 0; i < data.length; i++) {
          tmpDate = data[data.length-1-i].measurementId.timestamp;
          if (today.getTime() - this.dataTimeSpan > this.dateTimeHandling(tmpDate).getTime()) {
              break
          }
          this.sampleTimestamps[i] = this.dateToString(this.dateTimeHandling(tmpDate))
          this.sampleGlucoseLevels[i] = data[data.length-1-i].glucoseLevel
      }
      this.sampleTimestamps.reverse()
      this.sampleGlucoseLevels.reverse()
    },
    async loadGlucoseLevelsPerDailyTimestamp(data) {
      let timestamp;
      this.glucoseLevelsPerDailyTimestamp = data.reduce( (res, curr) => {
        timestamp = this.dateToString(this.dateTimeHandling(curr.measurementId.timestamp))
        if (res[timestamp] === undefined) {
          res[timestamp] = [curr.glucoseLevel]
        } else {
          res[timestamp].push(curr.glucoseLevel)
        }
        return res
      }, [])
    },
    async loadConfidenceIntervals() {
      let sampleConfidenceInterval;
      Object.entries(this.glucoseLevelsPerDailyTimestamp).forEach( ([timestamp, sample]) => {
        sampleConfidenceInterval = this.getSampleConfidenceInterval(sample)
        this.lowerConfidenceLevels[timestamp] = sampleConfidenceInterval.lowerBound
        this.upperConfidenceLevels[timestamp] = sampleConfidenceInterval.upperBound
      })
    },
    getSampleConfidenceInterval(sample) {
      let sampleSize, sampleMean, sampleVariance, sampleStandardDeviation, meanDeviation;
      let confidenceInterval = { lowerBound: 0, upperBound: 0 }
      sampleSize = sample.length
      sampleMean = sample.reduce((res, curr) => res + curr.glucoseLevel, 0) / sampleSize
      if (sampleSize == 1) {
        confidenceInterval = { lowerBound: sampleMean, upperBound: sampleMean}
      } else {
        sampleVariance = sample.reduce((res, curr) => res + (curr.glucoseLevel - sampleMean)**2, 0) / (sampleSize - 1)
        sampleStandardDeviation = Math.sqrt(sampleVariance)
        meanDeviation = 1.96 * sampleStandardDeviation / Math.sqrt(sampleSize)
        confidenceInterval.lowerBound = sampleMean - meanDeviation
        confidenceInterval.upperBound = sampleMean + meanDeviation
      }
      return confidenceInterval
    },

    async updateData() { 
      let data = await this.fetchData()
      this.loadGraphData(data)
      if (data.length > this.totalDataPoints) {
        let newDataPoints = data.slice(this.totalDataPoints)
        await this.updateGlucoseLevelsPerDailyTimestamp(newDataPoints)
        await this.updateConfidenceIntervals(newDataPoints)
        this.totalDataPoints = data.length
      }
    },
    async updateGlucoseLevelsPerDailyTimestamp(data) {
      let timestamp, currDataPoint;
        for (let i = 0; i < data.length; i++) {
          currDataPoint = data[data.length-1-i]
          timestamp = this.dateToString(this.dateTimeHandling(currDataPoint.measurementId.timestamp))
          if (this.glucoseLevelsPerDailyTimestamp[timestamp] === undefined) {
            this.glucoseLevelsPerDailyTimestamp[timestamp] = [currDataPoint.glucoseLevel]
          } else {
            this.glucoseLevelsPerDailyTimestamp[timestamp].push(currDataPoint.glucoseLevel)
          }
        }
    },
    async updateConfidenceIntervals(data) {
      let timestamp, sample, confidenceInterval;
      data.forEach((currDataPoint) => {
        timestamp = this.dateToString(this.dateTimeHandling(currDataPoint.measurementId.timestamp))
        sample = this.glucoseLevelsPerDailyTimestamp[timestamp]
        confidenceInterval = this.getSampleConfidenceInterval(sample)
        this.lowerConfidenceLevels[timestamp] = confidenceInterval.lowerBound
        this.upperConfidenceLevels[timestamp] = confidenceInterval.upperBound
      })
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
        labels: this.sampleTimestamps,
        datasets: [
          { 
            label: "Lower confidence levels",
            fill: false,
            data: this.lowerConfidenceLevels, 
            backgroundColor: 'rgba(255, 0, 0, 0.2)',
            borderColor: 'rgba(255, 0, 0, 0.2)',
            pointRadius: 0,
            pointHitRadius: 0
          },
          { 
            label: 'Glucose levels',
            fill: 0,
            data: this.sampleGlucoseLevels, 
            backgroundColor: 'blue',
            borderColor: 'rgb(75, 192, 192)',
          },
          { 
            label: "Upper confidence levels",
            fill: false,
            data: this.upperConfidenceLevels, 
            backgroundColor: 'rgba(0, 255, 0, 0.2)',
            borderColor: 'rgba(0, 255, 0, 0.2)',
            pointRadius: 0,
            pointHitRadius: 0
          },
          {
            label: "Lower bound for healthy level",
            data: this.sampleGlucoseLevels.reduce((res) => { res.push(3); return res} , []),
            fill: false,
            backgroundColor: 'rgba(0, 255, 0, 0.2)',
            showLine: false,
            pointRadius: 0,
            pointHitRadius: 0
          },
          {
            label: "Upper bound for healthy level",
            data: this.sampleGlucoseLevels.reduce((res) => { res.push(7); return res} , []),
            fill: false,
            backgroundColor: 'rgba(0, 255, 0, 0.2)',
            showLine: false,
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