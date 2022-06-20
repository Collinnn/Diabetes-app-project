<template>
    <div class="submission_container">
        <h3>Add a meals carbohydrate amount</h3>
        <!--Meals-->
        <p class="wrongInput" v-if="this.wrongInput">{{this.wrongInputString}}</p>
        <div class="input" id="timer">
            Hours:
            <select @change="selectHour($event)" class="form-control">
                <option disabled value="">Please select an hour</option>
                <option v-for="(id,title) in hourItems" :key="id" :value="title">{{title}}</option>
            </select>
            Minutes:
            <select @change="selectMinute($event)" class="form-control">
                <option disabled value="">Please select a min interval</option>
                <option v-for="{id,title} in minuteItems" :key="id" :value="title">{{title}}</option>
            </select>
        </div>
        <div class ="input" id="inputs">
            <input type =number step="0.000001" min="0" v-model="this.inputvalues.carbohydrates" placeholder="Carbohydrate in grams"/>
            <input type =number step="0.000001" min="0" v-model="this.inputvalues.bolus" placeholder="Insulin bolus in U"/>  
            <input type =number step="0.000001" min="0" v-model="this.inputvalues.basal" placeholder="Basal flowrate mU/min"/>  
        </div>
        <div class="submit">
            <button type="button" id="button" @click="sendUpdate()">Upload</button>
        </div>
    </div>
</template>

<script>


    let hours = 
            ['00','01','02','03','04'
            ,'05','06','07','08','09'
            ,'10','11','12','13','14'
            ,'15','16','17','18','19'
            ,'20','21','22','23','24'];
    let minutes = 
            ['0','5','10','15','20'
            ,'25','30','35','40','45'
            ,'50','55']

export default {
    name: "mealPage",
    data(){
        return{
            hourItems: hours.reduce((acc,elem) => {acc.push({id:elem,title:elem}); return acc},[]),
            minuteItems: minutes.reduce((acc,elem) => {acc.push({id:elem,title:elem}); return acc},[]),
            wrongInput: false,
            wrongInputString: "There is no logged measurement for the chosen hour and minute",
            inputvalues:{
                selectedHour: null,
                selectedMinute:null,
                carbohydrates:null,
                bolus: null,
                basal: null
            },
            measurement:{
                measurementId:{
                    timestamp: null,
                    patientId: null
                },
                glucoseLevel: null,
                bolus: null,
                basal: null,
                carbohydrates: null
            },
            getValues:{
                carbohydrates:null,
                bolus:null,
                basal:null,
                glucoseLevel:null
            }
        }
    },
    methods:{
        dateToString(date) {
            console.log(date)
            let tmp ="";
            tmp = date.toISOString();
            tmp = tmp.replaceAll(":","%3A")
            tmp = tmp.replace("T","%20")
            tmp = tmp.split(".")[0]
        
           return tmp
        },
        dateToGetString(date){
            let tmp =  date.toISOString();
            tmp = tmp.replace("T"," ");
            tmp = tmp.split(".")[0]
            return tmp;
        },
        selectHour(event){
            this.inputvalues.selectedHour = event.target.value
        },
        selectMinute(event){
            this.inputvalues.selectedMinute = event.target.value
        },
        async sendUpdate(){
            const id = this.$userController.getUserData().id
            let today = new Date();
            today.setUTCHours(this.inputvalues.selectedHour,this.inputvalues.selectedMinute,0,0)

            await this.axios.get(this.$backend.getUrlGetMeasurementsFromPatientById(id))
            .then(response =>{
                response.data.forEach(data => {
                    //console.log(data.measurementId.timestamp,"=",this.dateToGetString(today))
                    if(data.measurementId.timestamp ==this.dateToGetString(today)){
                        console.log(data.carbohydrates)
                        this.measurement.basal= data.basal;
                        this.measurement.bolus= data.bolus;
                        this.measurement.carbohydrates=data.carbohydrates;
                    }
 
                });
            }).catch((error) => console.log(error))
            console.log(this.inputvalues.basal)
            
            //Handling if no input has been made
            if(this.inputvalues.carbohydrates!=null || this.inputvalues.carbohydrates != ''){
                this.measurement.carbohydrates = this.inputvalues.carbohydrates;
            }
            if(this.inputvalues.basal!=null|| this.inputvalues.basal != ''){
                this.measurement.basal = this.inputvalues.basal;
            }
            if(this.inputvalues.bolus!=null|| this.inputvalues.bolus != ''){
                this.measurement.bolus = this.inputvalues.bolus;
            }
            
            this.measurement.measurementId.timestamp = this.today;
            this.measurement.measurementId.patientId= id;
            
            await this.axios.put(this.$backend.getUrlPutMeasurementByIdAndTimestamp(id,this.dateToString(today)),this.measurement)
            .then(response =>{
                console.log(response)
            }).catch(() =>
                        this.wrongInput = true
                    );
        }
    }
}

</script>

<style scoped>
.submission_container{
    display: flex;
    background-color:var(--secondary-color);
    flex-direction: column;

    width: 700px;
    gap: 30px;
    
}
.input{
    display:flex;
    flex-direction: row;
    background-color: var(--acent-color);
    justify-content: space-between;
    margin: 5%;
}
.wrongInput{
    color:red;
    font-size: 20px; 
}

</style>