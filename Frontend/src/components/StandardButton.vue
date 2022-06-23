<!-- Responsible author: Collin
 * Contributors: 
 -->
<template>
<div class="StandardButton">{{name}}</div>
</template>

<script>
export default{
    name:'StandardButton',
    props:{
        name: String
    }

}


</script>


<style scoped>
.StandardButton{
    padding:10px 20px;
    width: 30px;
    color:var(--text-color);
    border-color: var(--variant-color);
    border: 1px solid var(--text-color);
    font-size: 14px;
    cursor: pointer;
    background-color:var(--accent-color);

}
.StandardButton:hover{
    background: var(--highlight-color);
}

</style>