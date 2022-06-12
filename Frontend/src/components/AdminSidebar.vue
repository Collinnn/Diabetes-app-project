<template>
    <Sidebar style="max-width: 200px; min-width: 150px;">
        <div class="sidebar-wrapper">
            <DropdownMenu id="patientsMenu" title="Patients" :items="[
                {itemId: 'viewPatients', title: 'View patients', onClick: () => $router.push('patients')},
                {itemId: 'addPatient', title: 'Add patient', onClick: () => $router.push('add-patient')}
                ]"
                @selected="(itemId) => this.selectedMenuItemId=itemId"
            />
            <DropdownMenu id="doctorsMenu" title="Doctors" :items="[
                {itemId: 'viewDoctors', title: 'View doctors', onClick: () => $router.push('doctors')},
                {itemId: 'addDoctor', title: 'Add doctor', onClick: () => $router.push('add-doctor')}
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

</style>