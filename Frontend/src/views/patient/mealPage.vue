<template>
    <title>Meal submission page</title>
    <h1>Meal Submission Page</h1>
    <div class="page_container">
        <div class="submission_container">
            <div class="input" id="meals">
                <div class="dropdownwrapper">
                    <DropdownArray id="hourDropdown" title="Hours" :items=" houritems"/>
                </div>
               <div class="dropdownwrapper">
                    <DropdownArray id="hourDropdown" title="Min" :items=" minitems"/> 
               </div>
               
            </div>



            <div class="input" id="Bolus">b </div>
            <div class="input" id="Basal">c </div>

        </div>
    </div>



</template>

<script>
import DropdownArray from "@/components/DropdownArray"
    let hours = 
            ['00','01','02','03','04'
            ,'05','06','07','08','09'
            ,'10','11','12','13','14'
            ,'15','16','17','18','19'
            ,'20','21','22','23','24'];
    let min = 
            ['00','05','10','15','20'
            ,'25','30','35','40','45'
            ,'50','55']

export default {
    name: "mealPage",
    data(){
        return{
            houritems: hours.reduce((acc,elem) => {acc.push({id:elem,title:elem,onClick:()=>""}); return acc},[]),
            minitems: min.reduce((acc,elem) => {acc.push({id:elem,title:elem,onClick:()=>""}); return acc},[])
        }
    },
    methods:{

    },
    components:{
        DropdownArray
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
    justify-content: center;
    width: 700px;
    gap: 30px;
    
}
.input{
    display:flex;
    flex-direction: row;
    gap:0px 10px;
    background-color: var(--acent-color);
    justify-content: space-between;
}
.dropdownwrapper{
    display: flex;
    flex-direction: column;
}

</style>
