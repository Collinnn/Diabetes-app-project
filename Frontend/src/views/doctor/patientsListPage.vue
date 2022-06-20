<template>
    <title>Patients list page</title> 
    <div class="container">
        <UsersTable :columnNames="this.columnNames" :rows="this.rows"/>
    </div>
</template>

<script>
import UsersTable from "@/components/UsersTable.vue"

export default {
    name: "patientsListPage",
    data() {
        return {
            rows: [
                {id: 1, firstName: "Gauss", lastName: "Jordan", dateOfBirth: "1998-12-07"},
                {id: 2, firstName: "Jørgen", lastName: "Villadsen", dateOfBirth: "1979-10-10"},
                {id: 3, firstName: "Michael", lastName: "Pedersen", dateOfBirth: "1969-06-09"},
                {id: 4, firstName: "Banana", lastName: "Split", dateOfBirth: "1911-11-11"},
                {id: 5, firstName: "Lille", lastName: "Meyer", dateOfBirth: "2020-12-24"}

            ],
            columnNames: ["Id","First Name", "Last Name", "Date of Birth"]
        }
    },
    components: {
        UsersTable
    }
}

</script>

<style>
    .container {
        margin:180px;
    }
</style>