<template >
    <div class="container">
        <button class="sidebarButton" id="overviewButton" @click="goToPage('overview')"> 
            <span class="icon" id="overviewIcon"></span>
        </button>
        <button class="sidebarButton" id="foodButton" @click="goToPage('food')">
            <span class="icon" id="foodIcon"></span>
        </button>
        <button class="sidebarButton" id="doctorButton" @click="goToPage('doctor')">
            <span class="icon" id="doctorIcon"></span>
        </button>
        <button class="sidebarButton" id="emergencyButton" @click="goToPage('emergency')">
            <span class="icon" id="emergencyIcon"></span>
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
    background-color: #0010bb;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
}

.sidebarButton {
    cursor: pointer;
    background-color: #5b6be4;
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
    padding: 30px;
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