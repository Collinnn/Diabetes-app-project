<template>
    <div id="modalMask" @click="$emit('close')">
        <div id="modal">
            <div id="modalHead">
                <slot name="heading">
                    <h1>❗❗❗WARNING❗❗❗ </h1>
                </slot>
            </div>
            <div id="modalBody">
                <slot name="body">
                    Eat something or you're gonna feel bad, stupid... 
                </slot>
            </div>
            <button id="acceptButton" @click="$emit('close')"> Ok </button>
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
    width: 350px;
    height: auto;
    background-color: rgb(200, 200, 200);
    border-radius: 3px;
    padding: 5px 10px;
}
#modalHead {
    min-height: 40px;
}
#modalBody {
    min-height: 60px;
}
#acceptButton {
    position: relative;
    bottom: 20px;
    float: right;

}

</style>