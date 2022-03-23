<template>
    <div id="topbar">
        <h2>TOPBAR</h2> 
        <button id="settings">Settings</button>
    </div>
</template>

<script>

export default {
    name: "TopbarMenu",
    data() {
            return {
                title: 'Sidebar'
            }
        }
}

</script>

<style scoped>
#topbar {
    height: 50px;
    background: rgb(34, 8, 185);
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
}
#settings {
    float: right;
}
h2 {
    text-align: center;
}


</style>