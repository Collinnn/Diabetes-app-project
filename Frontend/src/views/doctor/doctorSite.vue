<template>
    <title> Doctor site </title>
    <div class="page-container">
        <div class="page-wrapper">
            <router-view />
        </div>
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

