<template>
    <title> Admin site </title>
    <div class="viewContainer">
        <router-view />
    </div>
    <Topbar />
    <AdminSidebar />
</template>

<script>
import Topbar from '@/components/Topbar'
import AdminSidebar from '@/components/AdminSidebar'

export default {
    name: "adminSite",
    components: { Topbar, AdminSidebar }
}
</script>

<style>



</style>

