<template>
    <title> Patient site </title>
    <Topbar />
    <PatientSidebar />
    <div class="viewContainer">
        <router-view name="patientSite"> </router-view>
    </div>
</template>

<script>
import Topbar from '@/components/Topbar'
import PatientSidebar from '@/components/PatientSidebar'

export default {
    name: "patientSite",
    components: { Topbar, PatientSidebar },
}
</script>

<style>
</style>

