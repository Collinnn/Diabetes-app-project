<template >
    <div id="sidebarContainer">
        <div id="sidebarWrapper"> 
            <button title="Overview" class="sidebar-button" id="overviewButton" @click="goToPage('overview')"> 
                <svg class="icon" id="overviewIcon"></svg>
            </button>
            <button title="Meals" class="sidebar-button" id="foodButton" @click="goToPage('food')">
                <svg class="icon" id="foodIcon"></svg>
            </button>
            <button title="Doctor" class="sidebar-button" id="doctorButton" @click="goToPage('doctor')">
                <svg class="icon" id="doctorIcon"></svg>
            </button> 
        </div>      
    </div>
</template>

<script>
    import router from "@/router"
    
    export default {
        name: 'SidebarMenu',
        data() {
            return {
                title: 'Sidebar'
            }
        },
        methods: {
            goToPage(pageName) {
                router.push({ name: pageName})
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
    background-color: #0010bb;
}

#sidebarWrapper {
    position: relative;
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