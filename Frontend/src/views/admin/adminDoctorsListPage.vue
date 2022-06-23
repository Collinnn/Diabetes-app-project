<!-- Responsible author: Simon Poulsen
 * Contributors:
 -->

<template>
    <title>Patients list page</title> 
    <div>
        <UsersTable :columnNames="this.columnNames" :rows="this.doctors" @rowClick="editPage"/>
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
    },
    components: {
        UsersTable
    },
    methods: {
        editPage() {
            this.$router.push({name: 'adminEditUserPage'})
        }
    }
}

</script>

<style>
</style>