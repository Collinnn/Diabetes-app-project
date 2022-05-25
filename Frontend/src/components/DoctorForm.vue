<template>
    <div id="container">
        <form id="doctorForm" @submit.prevent="submitDoctorForm" >
            <ul>
                <p class="required" v-requirement v-for="{requirement} in unhandledRequirements" :key="requirement"> *{{ requirement }} </p>
                <label>
                    First name: <input type="text" v-model="doctorForm.firstName" />
                </label>
            </ul>
            <ul>
                <label>
                    Last name: <input type="text" v-model="doctorForm.lastName" />
                </label>
            </ul>
            <ul>
                <label>
                    Password: <input type="password" v-model="doctorForm.password" />
                </label>
            </ul>
            <button type="submit"> Submit </button>
        </form>
        
    </div>
</template>

<script>

let uppercase = /[A-Z]/
let specialChars = /[ `´§½!@#$%¤€£^¨&*()_+\-=[\]{};':"\\|,.<>/?~]/
let numbers = /\d/

export default {
    name: "DoctorForm",
    data() {
        return {
            doctorForm: {
                firstName: "",
                lastName: "",
                password: ""
            },
            requirements: [
                { id: "empty", requirement: "All fields must be filled out." },
                { id: "numbers", requirement: "First name and last must not contain any numbers." },
                { id: "specialchars", requirement: "First name and last name must not contain any special characters (Letter accents excluded)." },
                { id: "password", requirement: "Password must be at least 6 characters long and contain at least 1 uppercase letter and 1 special character or number." }
            ]
        }
    },
    methods: {
        submitDoctorForm() {
            if (this.unhandledRequirements.length == 0) {
                this.axios.post(this.$backend.getUrlPostDoctor(), this.doctorForm)
                .then(() => {
                    this.doctorForm.firstName = ""
                    this.doctorForm.lastName = ""
                    this.doctorForm.password = ""
                    console.log("Doctor submitted")
                })
                .catch(() => console.log("Invalid request"))
            }
            else {
                console.log("Submission not accepted due to unhandled requirements")
            }
        }
    },
    computed: {
        unhandledRequirements() {
            let updatedRequirements = this.requirements

            if ((this.doctorForm.firstName.length * this.doctorForm.lastName.length * this.doctorForm.password.length) !== 0) {
                updatedRequirements = updatedRequirements.filter((req) => req.id != "empty")
            }

            if (!specialChars.test(this.doctorForm.firstName + this.doctorForm.lastName)) {
                updatedRequirements = updatedRequirements.filter((req) => req.id != "specialchars")
            }

            if (!numbers.test(this.doctorForm.firstName + this.doctorForm.lastName)) {
                updatedRequirements = updatedRequirements.filter((req) => req.id != "numbers")
            }

            if ((numbers.test(this.doctorForm.password) || specialChars.test(this.doctorForm.password)) && uppercase.test(this.doctorForm.password) && this.doctorForm.password.length >= 6) {
                updatedRequirements = updatedRequirements.filter((req) => req.id != "password")
            }

            return updatedRequirements
            
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