<template>
    <button :id="id" class="menu-head" @click="isMenuVisible = !isMenuVisible"> {{title}} </button>
    <div class="menu-wrapper" v-if="isMenuVisible">
        <button :id="itemId" class="menu-item" v-for="{itemId, title, onClick} in items" :key="itemId" @click="onClick(); $emit('selected', itemId)"> 
            {{title}} 
        </button>
     </div>
</template>

<script>
export default {
    name: "dropdownMenu",
    emits: ['selected'],
    props: {
        id: String, 
        title: String,
        // Array of objects {id: String, title: String, onClick: func}
        items: Array
    },
    data() {
        return {
            isMenuVisible: false
        }
    },
    watch: {
        isMenuVisible() {
            if (this.isMenuVisible) {
                document.getElementById(this.id).style.backgroundColor = "rgb(0, 0, 230)"
            } else {
                document.getElementById(this.id).style.backgroundColor = ""
            }
        }
    }
}
</script>

<style scoped>
button:hover {
    background-color: blue;
}
.menu-head, .menu-item {
    height: 40px;
    width: 100%;
    cursor: pointer;
    background-color: transparent;
    border: none;
    color: rgb(233, 233, 233);
    text-align: left;
}
.menu-head {
    padding-left: 20px;
    font: 20px Arial bold, sans-serif;
}
.menu-item {
    padding-left: 40px;
    font: 14px Arial bold, sans-serif;
}


</style>