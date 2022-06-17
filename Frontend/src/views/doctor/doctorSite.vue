<template>
    <title> Doctor site </title>
    <div class="viewContainer">
        <router-view />
        <UsersTable />
    </div>
    <Topbar />
</template>

<script>
import Topbar from '@/components/Topbar'
import UsersTable from '@/components/UsersTable'

export default {
    name: "doctorSite",
    components: { Topbar, UsersTable }
}
</script>

<style>
</style>

