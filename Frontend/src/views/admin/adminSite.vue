<template>
    <title> Admin site </title>
    <div class="page-container">
        <router-view />
    </div>
    <AdminSidebar />
    <Topbar />
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

