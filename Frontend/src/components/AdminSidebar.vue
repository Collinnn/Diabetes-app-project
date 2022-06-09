<template>
    <sidebar>
        <div class="sidebar-wrapper">
            <div class="button-wrapper">
                <label class="sidebar-label"> Patients </label>
            </div>
            <div class="button-wrapper">
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
.button-wrapper {
    display: flex;
    justify-content: end;
    align-items: center;
    height: 40px;
    width: 100%;
}
.button-wrapper:hover {
    background-color: blue;

}
.sidebar-label {
    position: relative;
    float: right;
    width: 70%;
}

</style>