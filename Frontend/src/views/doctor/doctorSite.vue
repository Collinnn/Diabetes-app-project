<template>
    <title> Doctor site </title>
    <div class="page-container">
        <router-view />
    </div>
    <Topbar />
</template>

<script>
import Topbar from '@/components/Topbar'

export default {
    name: "doctorSite",
    components: { Topbar }
}
</script>

<style>
</style>

