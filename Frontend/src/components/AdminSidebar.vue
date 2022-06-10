<template>
    <Sidebar style="max-width: 200px; min-width: 150px;">
        <div class="sidebar-wrapper">
            <DropdownMenu id="patientsMenu" title="Patients" :items="[
                {id: 'viewPatients', title: 'View patients', onClick: dop()},
                {id: 'addPatient', title: 'Add patient', onClick: $router.push('overview')}
                ]" 
            />
            <DropdownMenu id="doctorsMenu" title="Doctors" :items="[
                {id: 'viewDoctors', title: 'View doctors', onClick: $router.push('overview')},
                {id: 'addDoctor', title: 'Add doctor', onClick: $router.push('food')}
                ]" 
            />
        </div>
    </Sidebar>
</template>

<script>
import Sidebar from "./Sidebar.vue"
import DropdownMenu from "./DropdownMenu"

export default {
    name: "adminSidebar",
    components: { 
        Sidebar,
        DropdownMenu
    },
    methods: {
        dop() {
            console.log("bop")
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