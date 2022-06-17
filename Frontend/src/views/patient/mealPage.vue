<template>
    <title>Meal submission page</title>
    <h1>Meal Submission Page</h1>
    <div class="page_container">
        <div class="submission_container">
            <h3>Add a meals carbohydrate amount</h3>
            <!--Meals-->
            <div class="input" id="meals">
                Hours:
                <select @change="selectHourMeal($event)" class="form-control">
                    <option disabled value="">Please select an hour</option>
                    <option v-for="(id,title) in hourItems" :key="id" :value="title">{{title}}</option>
                </select>
                Minutes:
                <select @change="selectMinuteMeal($event)" class="form-control">
                    <option disabled value="">Please select an min interval</option>
                    <option v-for="{id,title} in minuteItems" :key="id" :value="title">{{title}}</option>
                </select>
                <input type =number step="0.000001" min="0" v-model="meal.carbohydrate" placeholder="Carbohydrate in grams"/>
                <button type="button" id="button" @click="sendMealUpdate()">Upload</button>
            </div>
            <h3>Add bolus amount for a specific timefram</h3>
            <!--Bolus insulin-->
            <div class="input" id="Bolus">
                Hours:
                    <select @change="selectHourBolus($event)" class="form-control">
                    <option disabled value="">Please select an hour</option>
                    <option v-for="(id,title) in hourItems" :key="id" :value="title">{{title}}</option>
                </select>
                Minutes:
                <select @change="selectMinuteBolus($event)" class="form-control">
                    <option disabled value="">Please select an min interval</option>
                    <option v-for="{id,title} in minuteItems" :key="id" :value="title">{{title}}</option>
                </select>
                <input type =number step="0.000001" min="0" v-model="bolus.bolusValue" placeholder="Insulin bolus in U"/>    
            </div>
            <h3>Add basal amount for a specfic time frame</h3>
            <!--Basal insulin-->
            <div class="input" id="Basal">
                Hours:
                    <select @change="selectHourBasal($event)" class="form-control">
                    <option disabled value="">Please select an hour</option>
                    <option v-for="(id,title) in hourItems" :key="id" :value="title">{{title}}</option>
                </select>
                Minutes:
                <select @change="selectMinuteBasal($event)" class="form-control">
                    <option disabled value="">Please select an min interval</option>
                    <option v-for="{id,title} in minuteItems" :key="id" :value="title">{{title}}</option>
                </select>
                <input type =number step="0.000001" min="0" v-model="basal.basalValue" placeholder="Basal flowrate mU/min"/>  
            </div>
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
            meal:{
            selectedHourMeal: null,
            selectedMinuteMeal:null,
            carbohydrate:null
            },
            bolus:{
                selectedHourBolus: null,
                selectedMinuteBolus:null,
                bolusValue:null
            },
            basal:{
                selectedHourBasal: null,
                selectedMinuteBasal:null,
                basalValue:null
            } 
        }
    },
    methods:{
        selectHourMeal(event){
            this.meal.selectedHourMeal = event.target.value
        },
        selectMinuteMeal(event){
            this.meal.selectedMinuteMeal = event.target.value
        },
        selectHourBolus(event){
            this.bolus.selectedHourBolus = event.target.value
        },
        selectMinuteBolus(event){
            this.bolus.selectedMinuteBolus = event.target.value
        },
        selectHourBasal(event){
            this.basal.selectedHourBasal = event.target.value
        },
        selectMinuteBasal(event){
            this.basal.selectedMinuteBasal = event.target.value
        },async sendMealUpdate(){
            const userData = this.$userController.getUserData()
            console.log("Hello",userData);

        }
    },
    components:{
    }
}

</script>

<style scoped>

.page_container{
    display:flex;
    justify-content: center;
}
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
}
.dropdownwrapper{
    display: flex;
    flex-direction: column;
}

</style>
