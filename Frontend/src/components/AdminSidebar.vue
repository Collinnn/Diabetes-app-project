<template>
    <SidebarContainer>
        <div class="sidebar-wrapper">
            <DropdownMenu id="patientsMenu" title="Patients" :items="[
                {itemId: 'viewPatients', title: 'View patients', onClick: () => $router.push('patients')},
                {itemId: 'addPatient', title: 'Add patient', onClick: () => $router.push('addPatient')}
                ]"
                @selected="(itemId) => selectedMenuItemId=itemId"
            />
            <DropdownMenu id="doctorsMenu" title="Doctors" :items="[
                {itemId: 'viewDoctors', title: 'View doctors', onClick: () => $router.push('doctors')},
                {itemId: 'addDoctor', title: 'Add doctor', onClick: () => $router.push('addDoctor')}
                ]"
                @selected="(itemId) => selectedMenuItemId=itemId"
            />
        </div>
    </SidebarContainer>
</template>

<script>
import SidebarContainer from "./SidebarContainer.vue"
import DropdownMenu from "./DropdownMenu.vue"

export default {
    name: "adminSidebar",
    data() {
        return {
            selectedMenuItemId: null
        }
    },
    components: { 
        SidebarContainer,
        DropdownMenu
    },
    mounted() {
        document.getElementById('app').style.setProperty("--sidebar-max-width", '200px')
        document.getElementById('app').style.setProperty("--sidebar-min-width", '150px')
    },
    watch: {
        selectedMenuItemId(value, oldValue) {
            if (oldValue != null && document.getElementById(oldValue) != null) {
                document.getElementById(oldValue).style.backgroundColor = ""
            }
            document.getElementById(value).style.backgroundColor = "var(--primary-color)"
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