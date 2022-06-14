<template>
        <Topbar />
        <AdminSidebar />
        <router-view />
</template>

<script>
import Topbar from '@/components/Topbar.vue'

export default {
  components: { Topbar, AdminSidebar },

}
</script>

<style>

</style>

