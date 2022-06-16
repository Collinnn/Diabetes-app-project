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
      timestamps: [],
      timer: null,

      chartOptions: {
        responsive: true,
        plugins: {}
      },
      chartId: 'glucose-chart',
      datasetIdKey: 'label',
      width: 1200,
      height: 800,
      cssClasses: '',
      styles: {},
      plugins: []
    }
  },
  async mounted() {
    this.loadData()
    this.timer = setInterval(async () => {
      this.loadData()
    }, 10000) // Every 10 sec
  },
  beforeUnmount() {
    clearInterval(this.timer)
  },
  methods: {
    async loadData() {
      let data = await this.getData()
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
      this.glucoseLevels.reverse()
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
    dateTimeHandling(tmpDate) {
      let dateTime = new Date();
      const [date, time] = tmpDate.split(' ');
      const [year, month, day] = date.split('-');
      const [hour, minute, second] = time.split(':')
      dateTime.setUTCFullYear(year);
      dateTime.setUTCMonth((month-1));
      dateTime.setUTCDate(day);
      dateTime.setUTCHours(hour);
      dateTime.setUTCMinutes(minute);
      dateTime.setUTCSeconds(second);
      return dateTime;
    },
    dateToString(date) {
      let tmp;
      tmp = date.toISOString().split('T');
      console.log(tmp)
      tmp = tmp[1].split('.');
      return tmp[0].substring(0, 5)
    }
  },
  computed: {
    chartData() {
      console.log("Updated")
      return {
        labels: this.timestamps,
        datasets: [ { data: this.glucoseLevels }]
      }
    }
  }
  
}
</script>