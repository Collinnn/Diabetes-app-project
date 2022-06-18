<template>
    <title>Patients list page</title> 
    <div class="container">
        <UsersTable />
    </div>
</template>

<script>
import UsersTable from "@/components/UsersTable.vue"

export default {
    name: "patientsListPage",
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