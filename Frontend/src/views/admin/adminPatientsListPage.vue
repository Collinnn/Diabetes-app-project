<template>
    <title>Patients list page</title> 
    <div class="container">
        <UsersTable :columnNames="this.columnNames" :rows="this.patients" @rowClick="patientsPage"/>
    </div>
</template>

<script>
import UsersTable from "@/components/UsersTable.vue"

export default {
    name: "adminPatientsList",
    data() {
        return {
            patients: [],
            columnNames: ["Id","First Name", "Last Name", "Date of Birth"]
        }
    },
    async mounted() {
        await this.axios.get(this.$backend.getUrlGetPatients())
        .then((response) => this.patients = response.data)
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
            this.$router.push({name: 'patientsUpdatePage'})
        }
    }
}

</script>

<style>
    .container {
        margin:180px;
    }
</style>