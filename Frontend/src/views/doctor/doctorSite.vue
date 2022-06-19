<template>
    <title> Doctor site </title>
    <div class="viewContainer">
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

