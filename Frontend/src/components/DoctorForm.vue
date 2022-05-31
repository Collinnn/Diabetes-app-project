<template>
    <div id="container">
        <form @submit.prevent="submitForm">
            <ul>
                <p id="doctorFormRequirements"> </p>
                <label>
                    First name: <input type="text" v-required:doctorFormRequirements.notEmpty.noNum.noSpec="nameRequirements" v-model="form.firstName" />
                </label>
            </ul>
            <ul>
                <label>
                    Last name: <input type="text" v-required:doctorFormRequirements.notEmpty.noNum.noSpec="nameRequirements" v-model="form.lastName" />
                </label>
            </ul>
            <ul>
                <label>
                    Password: <input type="password" v-required:doctorFormRequirements.notEmpty.password="passwordRequirements" v-model="form.password" />
                </label>
            </ul>
            <button type="submit"> Submit </button>
        </form>
    </div>
</template>

<script>

export default {
    name: "DoctorForm",
    data() {
        return {
            form: {
                firstName: "",
                lastName: "",
                password: ""
            },
            nameRequirements: {
                notEmpty: "*All fields must be filled out.",
                noNum: "*First name an last name must not contain any numbers.",
                noSpec: "*First name and last name must not contain any special characters (letter accents excluded)."
            },
            passwordRequirements: {
                notEmpty: "*All fields must be filled out.",
                password: "*Password must be at least 6 characters long and contain at least 1 uppercase letter and 1 special character or number." 
            }
        }
    },
    methods: {
        submitForm() {
            if (this.isValidForm) {
                this.axios.post(this.$backend.getUrlPostDoctor(), this.form)
                .then(() => {
                    this.form.firstName = ""
                    this.form.lastName = ""
                    this.form.password = ""
                    console.log("Doctor submitted")
                })
                .catch(() => console.log("Invalid post request"))
            }
            else {
                console.log("Submission not accepted due to unhandled requirements")
            }
        }
    },
    computed: {
        isValidForm() {
            return document.getElementById("doctorFormRequirements").innerText.length == 0
        }
    }
}

</script>

<style>
    #container {
        position: absolute;
        left: calc(50% - 200px);
        width: 400px;
        border-radius: 20px;
        background-color: rgb(192, 192, 192);
        padding: 10px;
    }

</style>