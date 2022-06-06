<template>
    <div id="modalMask" @click="$emit("close")">
        <div id="modal">
            <slot name="heading">
                <h2>❗❗❗WARNING❗❗❗ </h2>
            </slot>
            <slot name="body">
                Eat something or you're gonna feel bad, stupid... 
            </slot>
            <button @click="$emit("close")"> Ok </button>
        </div>
    </div>

</template>


<script>
    export default {
        name: "ModalBox"
    }
</script>


<style>
#modalMask {
    background-color: rgba(0, 0, 0, 0.4);
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
}

</style>