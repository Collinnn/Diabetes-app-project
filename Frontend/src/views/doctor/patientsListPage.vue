<template>
    <title>Patients list page</title> 
    <div class="container">
        <UsersTable :columnNames="this.columnNames" :rows="this.patients" @rowClick="patientsPage"/>
    </div>
</template>

<script>
import UsersTable from "@/components/UsersTable.vue"

export default {
    name: "patientsListPage",
    data() {
        return {
            patients: [],
            columnNames: ["Id","First Name", "Last Name", "Date of Birth"],
            randomVal: "0"
        }
    },
    async mounted() {
        await this.axios.get(this.$backend.getUrlGetPatientsFromDoctorById(this.$userController.getUserData().id))
        .then((response) => this.patients = response.data)
        console.log(this.patients)
        this.patients = this.patients.reduce((res,curr) => {
            res.push({id: curr.id, firstName: curr.firstName, lastName: curr.lastName, dateOfBirth: curr.dateOfBirth})
            return res
        },[])
        console.log(this.patients)
    },
    components: {
        UsersTable
    },
    methods: {
        patientsPage(patient) {
            console.log(patient)
            this.$router.push({name: 'doctorGraphPage'})
        }
    }
}

</script>

<style>
    .container {
        margin:180px;
    }
</style>