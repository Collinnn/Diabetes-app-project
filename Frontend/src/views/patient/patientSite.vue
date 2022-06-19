<template>
    <title> Patient site </title>
    <div class="viewContainer">
        <router-view />
    </div>
    <Topbar />
    <PatientSidebar />
</template>

<script>
import Topbar from '@/components/Topbar'
import PatientSidebar from '@/components/PatientSidebar'

export default {
    name: "patientSite",
    components: { Topbar, PatientSidebar }
    
}
</script>

<style>

.viewContainer {
    top: 50px;
    left: 150px;
}

</style>

