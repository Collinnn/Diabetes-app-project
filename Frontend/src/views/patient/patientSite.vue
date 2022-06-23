<!--
 * Responsible author: Jacob Martens
 * Contributors:
-->
<template>
    <title> Patient site </title>
    <div class="page-container">
        <div class="page-wrapper">
            <router-view />
        </div>
    </div>
    <PatientSidebar />
    <Topbar />
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

</style>

