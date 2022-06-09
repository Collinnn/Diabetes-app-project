<template>
    <sidebar style="max-width: 200px; min-width: 150px;">
        <div class="sidebar-wrapper">
            <div class="button-container" @click="$router.push('admin-patients')">
                <label class="sidebar-label"> Patients </label>
            </div>
            <div class="button-container" @click="$router.push('admin-doctors')">
                <label class="sidebar-label"> Doctors </label>
            </div>
        </div>
    </sidebar>
</template>

<script>
import Sidebar from "./Sidebar.vue"

export default {
    name: "adminSidebar",
    components: { 
        Sidebar
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

.button-container {
    height: 40px;
    width: 100%;
    display: flex;
    justify-content: end;
    align-items: center;
    background-color: transparent;
    cursor: pointer;
}
.button-container:hover {
    background-color: blue;
}

.sidebar-label {
    cursor: pointer;
    width: 70%;
    margin-left: 60px;
    color: rgb(233, 233, 233);
    font-family: Arial, Helvetica, sans-serif;
}
.sidebar-label:hover .button-container {
    background-color: blue;
}

</style>