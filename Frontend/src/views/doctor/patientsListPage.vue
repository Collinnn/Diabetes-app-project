<template>
    <title>Patients list page</title> 
    <div class="container">
        <UsersTable :columnNames="this.columnNames" :rows="this.patients" @rowClick="goToPatientGraphPage"/>
    </div>
</template>

<script>
import UsersTable from "@/components/UsersTable.vue"

export default {
    name: "patientsListPage",
    data() {
        return {
            patients: [],
            columnNames: ["Id","First Name", "Last Name", "Date of Birth"]
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
    },
    components: {
        UsersTable
    },
    methods: {
        goToPatientGraphPage(patient) {
            const patientId = Number(patient.id)
            this.$router.push({name: 'doctorGraphPage', params: {userId: patientId}})
        }
    }
}

</script>

<style>
    .container {
        margin:180px;
    }
</style>