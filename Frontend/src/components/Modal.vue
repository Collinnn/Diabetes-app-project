<!--
 * Responsible author: Jacob Martens
 * Contributors:
 -->
<template>
    <div id="modalMask" @click="this.$emit('close')">
        <div id="modal" @click.stop>
            <div id="modalHead">
                <slot name="heading">
                    <h1> ❗❗❗WARNING❗❗❗ </h1>
                </slot>
            </div>
            <div id="modalBody">
                <slot name="body">
                    Your blood sugar is running low... 😰
                </slot>
            </div>
            <div id="modalFooter">
                <button id="modalButton" @click="this.$emit('close')"> Ok </button>
            </div>
        </div>
    </div>

</template>


<script>
    export default {
        name: "ModalBox",
        emits: ['close']
    }
</script>


<style>
#modalMask {
    background-color: rgba(0, 0, 0, 0.6);
    position: fixed;
    z-index: 100;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
}
#modal {
    margin: 60px auto 0;
    width: 30%;
    max-height: 50%;
    background-color: var(--primary-color);
    border-radius: 4px;
    padding: 5px 10px;
    box-shadow: 0 0 10px 2px;
}
#modalHead {
    display: flex;
    justify-content: center;
    height: fit-content;
    width: 100%;
    color: var(--text-color);
    overflow: hidden;
}
#modalBody {
    display: flex;
    justify-content: center;
    max-height: 300px;
    width: 100%;
    color: var(--text-color);
    overflow: hidden;
}
#modalFooter {
    position: relative;
    height: 30px;
}
#modalButton {
    position: absolute;
    bottom: 5px;
    right: 5px;
    cursor: pointer;
}

</style>