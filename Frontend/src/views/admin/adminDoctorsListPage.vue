<template>
    <title>Patients list page</title> 
    <div class="container">
        <UsersTable :columnNames="this.columnNames" :rows="this.doctors" @rowClick="patientsPage"/>
    </div>
</template>

<script>
import UsersTable from "@/components/UsersTable.vue"

export default {
    name: "adminDoctorsList",
    data() {
        return {
            doctors: [],
            columnNames: ["Id","First Name", "Last Name"]
        }
    },
    async mounted() {
        await this.axios.get(this.$backend.getUrlGetDoctors())
        .then((response) => this.doctors = response.data)
        this.doctors = this.doctors.reduce((res,curr) => {
            res.push({id: curr.id, firstName: curr.firstName, lastName: curr.lastName})
            return res
        },[])
        console.log(this.doctors)
    },
    components: {
        UsersTable
    },
    methods: {
        doctorsPage(doctor) {
            console.log(doctor)
            this.$router.push({name: 'doctorsUpdatePage'})
        }
    }
}

</script>

<style>
    .container {
        margin:180px;
    }
</style>