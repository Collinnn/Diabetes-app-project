<template>
    <title> Doctor site </title>
    <Topbar />
    <div class="viewContainer">
        <router-view />
    </div>
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

