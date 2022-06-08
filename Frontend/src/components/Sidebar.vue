<template >
    <div class="container">
        <button class="sidebarButton" id="overviewButton" @click="goToPage('overview')"> 
            <svg class="icon" id="overviewIcon"></svg>
        </button>
        <button class="sidebarButton" id="foodButton" @click="goToPage('food')">
            <svg class="icon" id="foodIcon"></svg>
        </button>
        <button class="sidebarButton" id="doctorButton" @click="goToPage('doctor')">
            <svg class="icon" id="doctorIcon"></svg>
        </button>
        <button class="sidebarButton" id="emergencyButton" @click="goToPage('emergency')">
            <svg class="icon" id="emergencyIcon"></svg>
        </button>        
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
.container {
    position: fixed;
    top: 50px;
    bottom: 0;
    left: 0;
    width: 20%;
    min-width: 120px;
    max-width: 150px;
    background-color: var(--secondary-color);
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
}

.sidebarButton {
    cursor: pointer;
    background-color: var(--accent-color);
    width: 100px;
    height: 100px;
    border-radius: 10px;
    outline: none;
    border: none;
    
}
.sidebarButton:hover {
    background: rgba(255, 255, 255, 0.60);
}
.sidebarButton:active {
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
#emergencyIcon {
    background: url('@/assets/Emergency.svg') no-repeat center;
}

</style>