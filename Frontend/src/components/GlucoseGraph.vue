<template>
    <Line
        :chart-options="chartOptions"
        :chart-data="chartData"
        :chart-id="chartId"
        :dataset-id-key="datasetIdKey"
        :plugins="plugins"
        :css-classes="cssClasses"
        :styles="styles"
    />
  
</template>

<script>
import { Line } from 'vue-chartjs'
import { Chart as ChartJS, registerables} from 'chart.js'

ChartJS.register(...registerables)


export default {
  name: 'GraphChart',
  components: { Line },
  emits: ["lowBloodSugar", "highBloodSugar"],
  props: {
    patientId: Number
  },
  data() {
    return {
      totalDataPoints: 0,
      sampleGlucoseLevels: [],
      sampleTimestamps: [],
      glucoseLevelsPerDailyTimestamp: [], // Array of arrays with glucose levels for each minute in measurements data
      lowerConfidenceLevels: [],
      upperConfidenceLevels: [],
      
      lowGlucoseLevel: 4,
      highGlucoseLevel: 7,
      dataTimeSpan: 1*1*60*60*1000,  // *60 minutes* in milliseconds
      timer: null,

      chartOptions: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          y: {
            min: 0,
            ticks:{
                  color: "black"
              },
              title: {
                display: true,
                text: "Glucose level [mmol/L]",
                color: "black"
              }
          },
          x:{
              ticks:{
                  color: "black"
              },
              title: {
                display: true,
                text: "Time of the hour [min]",
                color: "black"
              }
              
          }
        },
        plugins: {
          title: {
            display: true,
            text: "Glucose levels",
            color: "black",
            font: {
              family: "'arial'",
              size: 30,
              weight: "bold"
            }
          },
          legend: {
            display: false
          },
        }
      },
      chartId: 'glucose-chart',
      datasetIdKey: 'label',
      cssClasses: 'graph-background',
      styles: {},
      plugins: []
    }
  },
  async mounted() {
    await this.loadData()
    this.timer = setInterval(async () => {
      this.updateData()
    }, 60000) // Every 60 sec
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
      console.log("Patient data loaded successfully")
    },
    async fetchData() {
      let data;
      await this.axios.get(this.$backend.getUrlGetMeasurementsFromPatientById(this.patientId))
       .then( response => {
          data = response.data
       })
      return data
    },
    async loadGraphData(data) {
      let tmpDate, sampleTimestamp;
      let today = new Date()
      for (let i = 0; i < data.length; i++) {
          tmpDate = data[data.length-1-i].measurementId.timestamp;
          if ((today.getTime() - this.dataTimeSpan) > this.dateTimeHandling(tmpDate).getTime()) {
              break
          }
          sampleTimestamp = this.dateToString(this.dateTimeHandling(tmpDate))
          this.sampleTimestamps[i] = sampleTimestamp
          this.sampleGlucoseLevels[sampleTimestamp] = data[data.length-1-i].glucoseLevel
      }
      this.sampleTimestamps.reverse()
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
      sampleMean = sample.reduce((res, curr) => res + curr, 0) / sampleSize
      if (sampleSize == 1) {
        confidenceInterval = { lowerBound: sampleMean, upperBound: sampleMean}
      } else {
        sampleVariance = sample.reduce((res, curr) => res + (curr - sampleMean)**2, 0) / (sampleSize - 1)
        sampleStandardDeviation = Math.sqrt(sampleVariance)
        meanDeviation = 1.96 * sampleStandardDeviation / Math.sqrt(sampleSize)
        confidenceInterval.lowerBound = sampleMean - meanDeviation
        confidenceInterval.upperBound = sampleMean + meanDeviation
      }
      return confidenceInterval
    },

    async updateData() { 
      let data = await this.fetchData()
      await this.loadGraphData(data)
      if (data.length > this.totalDataPoints) {
        let newDataPoints = data.slice(this.totalDataPoints)
        newDataPoints.forEach((dataPoint) => {
          if (dataPoint.glucoseLevel < this.lowGlucoseLevel) {
            this.$emit("lowBloodSugar", dataPoint.glucoseLevel.toFixed(2))
          } 
          else if (dataPoint.glucoseLevel > this.highGlucoseLevel) {
            this.$emit("highBloodSugar", dataPoint.glucoseLevel.toFixed(2))
          }
        })
        await this.updateGlucoseLevelsPerDailyTimestamp(newDataPoints)
        await this.updateConfidenceIntervals(newDataPoints)
        this.totalDataPoints = data.length
        console.log("Patient data updated successfully")
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
      const [date, time] = tmpDate.slice(0, tmpDate.length-1).split('T');
      const [year, month, day] = date.split('-');
      const [hour, minute, second] = time.split(':')
      dateTime.setFullYear(year);
      dateTime.setMonth((month-1));
      dateTime.setDate(day);
      dateTime.setHours(hour, minute, second);
      return dateTime;
    },
    dateToString(date) {
      let tmp;
      tmp = date.toISOString().split('T');
      tmp = tmp[1].split('.');
      return tmp[0].substring(3, 5)
    }
  },
  computed: {
    chartData() {
      return {
        labels: this.sampleTimestamps,
        datasets: [
          {
            label: "Lower bound for healthy level",
            data: this.sampleTimestamps.map(() => this.lowGlucoseLevel),
            borderColor: 'rgba(0, 200, 0, 0.8)',
            pointRadius: 0,
            pointHitRadius: 0
          },
          {
            label: "Upper bound for healthy level",
            data: this.sampleTimestamps.map(() => this.highGlucoseLevel),
            borderColor: 'rgba(0, 200, 0, 0.8)',
            pointRadius: 0,
            pointHitRadius: 0

          },
          { 
            label: "Lower confidence levels",
            fill: false,
            data: this.sampleTimestamps.map((ts) => this.lowerConfidenceLevels[ts]), 
            backgroundColor: 'rgba(255, 0, 0, 0.2)',
            borderColor: 'rgba(0, 255, 0, 0.2)',
            pointRadius: 0,
            pointHitRadius: 0,
            lineTension: 0.2
          },
          { 
            label: 'Glucose levels',
            fill: {
              target: 2,
              below: 'transparent',
              above: 'rgba(0, 255, 0, 0.3)'
            },
            data: this.sampleTimestamps.map((ts) => this.sampleGlucoseLevels[ts]), 
            backgroundColor: 'rgb(0, 0, 255)',
            borderColor: 'rgb(75, 192, 192)',
          },
          { 
            label: "Upper confidence levels",
            fill: {
              target: 3,
              below: 'transparent',
              above: 'rgba(255, 0, 0, 0.3)'
            },
            data: this.sampleTimestamps.map((ts) => this.upperConfidenceLevels[ts]), 
            backgroundColor: 'rgba(255, 0, 0, 0.2)',
            borderColor: 'rgba(0, 255, 0, 0.2)',
            pointRadius: 0,
            pointHitRadius: 0,
            lineTension: 0.2
          }, 
        ]
      }
    }
  }
  
}
</script>

<style>
.graph-background {
  position: relative;
  background-color: var(--primary-color);
  width: 100%;
  height: 100%;
  border-radius: 5px;
}

</style>