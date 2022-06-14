<template>
    <title> Admin site </title>
    <Topbar />
    <AdminSidebar />
    <div class="viewContainer">
      <router-view @logIn="logIn()"/>
    </div>
</template>

<script>
import Topbar from '@/components/Topbar'
import AdminSidebar from '@/components/AdminSidebar'

export default {
    name: "adminSite",
    components: { Topbar, AdminSidebar },
}
</script>

<style>
</style>

