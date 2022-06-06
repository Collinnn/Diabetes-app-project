<template>
    <div id="modalMask" @click="$emit('close')">
        <div id="modal" @click.stop>
            <div id="modalHead">
                <slot name="heading">
                    <h1> ❗❗❗WARNING❗❗❗ </h1>
                </slot>
            </div>
            <div id="modalBody">
                <slot name="body">
                    Pls eat something... 😰
                </slot>
            </div>
            <div id="modalFooter">
                <button id="modalButton" @click="$emit('close')"> Ok </button>
            </div>
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
    background-color: rgba(0, 0, 0, 0.6);
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
}
#modal {
    margin: 60px auto;
    width: 380px;
    height: auto;
    background-color: rgb(200, 200, 220);
    border-radius: 3px;
    padding: 5px 10px;
    box-shadow: 0 0 10px 4px;
}
#modalHead {
    height: 80px;
    overflow: hidden;
}
#modalBody {
    height: 80px;
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