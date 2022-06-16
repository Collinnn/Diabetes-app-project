<template>
    <title> Admin site </title>
    <Topbar />
    <AdminSidebar />
    <div class="viewContainer" id="adminViews">
        <router-view />
    </div>
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

