<template>
    <button :id="id" class="menu-head" @click="isMenuVisible = !isMenuVisible"> {{title}} <span style="float: right; padding-right: 10px;">{{dropdownArrow}}</span> </button>
    <div class="menu-wrapper" v-if="isMenuVisible">
        <button :id="itemId" class="menu-item" v-for="{itemId, title, onClick} in items" :key="itemId" @click="onClick(); this.$emit('selected', itemId)"> 
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
            isMenuVisible: false,
            dropdownArrow: "▾"
        }
    },
    watch: {
        isMenuVisible() {
            if (this.isMenuVisible) {
                document.getElementById(this.id).style.backgroundColor = "var(--accent-color)"
                this.dropdownArrow = "▴"
            } else {
                document.getElementById(this.id).style.backgroundColor = ""
                this.dropdownArrow = "▾"
            }
        }
    }
}
</script>

<style scoped>
button:hover {
    background-color: var(--primary-color);
}
.menu-head, .menu-item {
    height: 40px;
    width: 100%;
    cursor: pointer;
    background-color: inherit;
    border: none;
    color: var(--text-color);
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