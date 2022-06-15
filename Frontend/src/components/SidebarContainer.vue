<template >
    <div id="sidebarContainer">
        <slot>

        </slot>      
    </div>
</template>

<script>

export default {
    name: 'SidebarMenu',
    data() {
        return {
            title: 'SidebarContainer'
        }
    }
}
</script>

<style scoped>
#sidebarContainer {
    position: fixed;
    top: 50px;
    bottom: 0;
    left: 0;
    width: 18%;
    min-width: 120px;
    max-width: 150px;
    background-color: var(--secondary-color);
}

</style>