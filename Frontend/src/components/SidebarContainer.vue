<!--
 * Responsible author: Jacob Martens
 * Contributors:
 -->
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
    bottom: 0;
    left: 0;
    top: var(--topbar-height);
    height: 100%;
    width: var(--sidebar-width);
    min-width: var(--sidebar-min-width);
    max-width: var(--sidebar-max-width);
    background-color: var(--secondary-color);
}

</style>