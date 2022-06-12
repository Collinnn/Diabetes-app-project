<template >
    <Sidebar>
        <div id="sidebarWrapper"> 
            <button title="Overview" class="sidebar-button" id="overviewButton" @click="$router.push('overview')"> 
                <svg class="icon" id="overviewIcon"></svg>
            </button>
            <button title="Meals" class="sidebar-button" id="foodButton" @click="$router.push('food')">
                <svg class="icon" id="foodIcon"></svg>
            </button>
        </div>      
    </Sidebar>
</template>


<script>
import Sidebar from "./Sidebar.vue"

export default {
        name: 'patientSidebar',
        components: { Sidebar },
        data() {
            return {
                title: 'PatientSidebar'
            }
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
    background-color: #5b6be4;
    width: 80px;
    height: 80px;
    border-radius: 10px;
    outline: none;
    border: none;
    
}
.sidebar-button:hover {
    background: rgba(255, 255, 255, 0.60);
}
.sidebar-button:active {
    background: rgba(100, 100, 255, 0.60);
}
.icon {
    width: 60px;
    height: 60px;
}
#overviewIcon {
    background: url('@/assets/Graph.svg') no-repeat center;
}
#foodIcon {
    background: url('@/assets/Food.svg') no-repeat center;
}
#doctorIcon {
    background: url('@/assets/Doctor.svg') no-repeat center;
}


</style>