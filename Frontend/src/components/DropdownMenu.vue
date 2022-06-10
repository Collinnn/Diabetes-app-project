<template>
    <button :id="id" class="menu-head" @click="isMenuVisible = !isMenuVisible"> {{title}} </button>
    <div class="menu-wrapper" v-if="isMenuVisible">
        <button :id="id" class="menu-item" v-for="{id, title, onClick} in items" :key="id" @click="onClick()"> 
            {{title}} 
        </button>
     </div>
</template>

<script>
export default {
    name: "dropdownMenu",
    props: {
        id: String, 
        title: String,
        // Default: Array of {id: 'foo', title: 'bar', onClick: baz()} 
        items: [Array, Object],
        default: []
    },
    data() {
        return {
            isMenuVisible: false
        }
    },
    mounted() {
        //this.items.forEach((id, title) => )
    },
    watch: {
        isMenuVisible() {
            if (this.isMenuVisible) {
                document.getElementById(this.id).style.backgroundColor = "rgb(0, 0, 230)"
            } else {
                document.getElementById(this.id).style.backgroundColor = ""
            }
        },
        items: {
            handler(val) {
                console.log(val)    
            },
            deep: true
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