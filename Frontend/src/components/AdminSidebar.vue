<template>
    <Sidebar style="max-width: 200px; min-width: 150px;">
        <div class="sidebar-wrapper">
            <DropdownMenu id="patientsMenu" title="Patients" :items="[
                {itemId: 'viewPatients', title: 'View patients', onClick: () => $router.push('food')},
                {itemId: 'addPatient', title: 'Add patient', onClick: () => $router.push('overview')}
                ]"
                @selected="(itemId) => this.selectedMenuItemId=itemId"
            />
            <DropdownMenu id="doctorsMenu" title="Doctors" :items="[
                {itemId: 'viewDoctors', title: 'View doctors', onClick: () => $router.push('overview')},
                {itemId: 'addDoctor', title: 'Add doctor', onClick: () => $router.push('food')}
                ]"
                @selected="(itemId) => this.selectedMenuItemId=itemId"
            />
        </div>
    </Sidebar>
</template>

<script>
import Sidebar from "./Sidebar.vue"
import DropdownMenu from "./DropdownMenu"

export default {
    name: "adminSidebar",
    data() {
        return {
            selectedMenuItemId: null
        }
    },
    components: { 
        Sidebar,
        DropdownMenu
    },
    watch: {
        selectedMenuItemId(value, oldValue) {
            if (oldValue != null) {
                document.getElementById(oldValue).style.backgroundColor = null
            }
            document.getElementById(value).style.backgroundColor = "blue"
        }
    }
}
</script>

<style>
.sidebar-wrapper {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    margin-top: 30px;
}

.button-mask {
    position: relative;
    z-index: 3;
    
}

.button {
    height: 40px;
    width: 100%;
    cursor: pointer;
    background-color: transparent;
    border: none;
    color: rgb(233, 233, 233);
    font: 20px Arial bold, sans-serif;
}
.button:hover {
    background-color: blue;
}

</style>