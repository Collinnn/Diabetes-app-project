<template >
    <SidebarContainer>
        <div id="sidebarWrapper"> 
            <button title="Graph" class="sidebar-button" id="graphButton" @click="$router.push({name: 'graph'})"> 
                <svg class="icon" id="graphIcon"></svg>
            </button>
            <button title="Meals" class="sidebar-button" id="mealButton" @click="$router.push({name: 'meal'})">
                <svg class="icon" id="mealIcon"></svg>
            </button>
        </div>      
    </SidebarContainer>
</template>


<script>
import SidebarContainer from "./SidebarContainer.vue"

export default {
    name: 'patientSidebar',
    components: { SidebarContainer },
    mounted() {
        document.getElementById('app').style.setProperty("--sidebar-max-width", '150px')
        document.getElementById('app').style.setProperty("--sidebar-min-width", '120px')
    }
}
</script>

<style scoped>

#sidebarWrapper {
    margin-top: 30px;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    gap: 30px;
    align-items: center;
}

.sidebar-button {
    cursor: pointer;
    background-color: var(--accent-color);
    width: 80px;
    height: 80px;
    border-radius: 10px;
    outline: none;
    border: none;
    
}
.sidebar-button:hover {
    background: var(--highlight-color);
}
.sidebar-button:active {
    background: rgba(110, 110, 150, 0.8);
}
.icon {
    width: 60px;
    height: 60px;
}
#graphIcon {
    background: url('@/assets/Graph.svg') no-repeat center;
}
#mealIcon {
    background: url('@/assets/Food.svg') no-repeat center;
}

</style>