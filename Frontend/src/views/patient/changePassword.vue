<template>
    <title>Change user password page</title>
    <div class="content">
        <div id="container">
        <h3>Change password</h3>
            <form @submit.prevent="submitForm">
                <ul>
                    <p v-requirement v-for="{requirement} in unhandledRequirements" :key="requirement"> *{{ requirement }} </p>
                    <label>
                        New password: <input type="text" v-model="form.password1" />
                    </label>
                </ul>
                <ul>
                    <label>
                        Repeat password: <input type="text" v-model="form.password2" />
                    </label>
                </ul>
                <button type="submit"> Submit </button>
            </form>
        </div>
    </div>
</template>

<script>

let uppercase = /[A-Z]/
let specialChars = /[ `´§½!@#$%¤€£^¨&*()_+=[\]{};':"\\|,.<>/?~-]/
let numbers = /\d/

export default {
    name: "changePasswordPage",
    emits: ['logIn','logInasDoctor','logInasAdmin'],
    props:{
        User:{
            id:Number,
            Name:String,
            Doctor:String,
        }
    },
    data(){
        return{
            form:{
                password1:"",
                password2:""
            },
            requirements:[
                {id: "password", requirement:"Password must be at least 6 characters long and contain at least 1 uppercase letter and 1 special character or number."},
                {id: "match", requirement:"Both passwords must be identical"}
            ]
        }
    },
    methods: {
        submitForm() {
            if (this.unhandledRequirements.length == 0) {
            console.log("Submit new password")
            } else {
            console.log("Submission not accepted due to unhandled requirements")   
            }
        }
    },
    computed: {
        unhandledRequirements() {
            let updatedRequirements = this.requirements
            if ((numbers.test(this.form.password1) || specialChars.test(this.form.password1)) && uppercase.test(this.form.password1) && this.form.password1.length >= 6) {
                updatedRequirements = updatedRequirements.filter((req) => req.id != "password")
            }
            if (this.form.password1 == this.form.password2) {
                updatedRequirements = updatedRequirements.filter((req) => req.id != "match")
            }

            return updatedRequirements
            
        }
    }
}

</script>

<style>
.content {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}
#container {
    width: 400px;
    border-radius: 20px;
    background-color: rgb(192, 192, 192);
    padding: 10px;
}
</style>