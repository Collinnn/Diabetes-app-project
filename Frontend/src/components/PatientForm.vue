<template>
    <div id="container">
        <h3> Patient Submission Form </h3>
        <form @submit.prevent="submitForm">
            <ul>
                <p v-requirement v-for="{requirement} in unhandledRequirements" :key="requirement"> *{{ requirement }} </p>
                <label>
                    First name: <input type="text" v-model="form.firstName" />
                </label>
            </ul>
            <ul>
                <label>
                    Last name: <input type="text" v-model="form.lastName" />
                </label>
            </ul>
            <ul>
                <label>
                    Password: <input type="password" v-model="form.password" />
                </label>
            </ul>
            <ul>
                <label>
                    Date of birth: <input type="date" min="1900-01-01" :max="this.today" v-model="form.dateOfBirth" />
                </label>
            </ul>
            <ul> 
                <label>
                    Doctor: <select>
                                <option v-for="{id, firstName, lastName} in doctors" :key="id"> {{firstName}} {{lastName}} </option>
                            </select>
                </label>
            </ul>
            <button type="submit"> Submit </button>
        </form>
    </div>
</template>

<script>

let date = new Date(); 
let today = date.toISOString().split('T')[0]

let uppercase = /[A-Z]/
let specialChars = /[ `´§½!@#$%¤€£^¨&*()_+=[\]{};':"\\|,.<>/?~-]/
let numbers = /\d/

export default {
    name: "PatientForm",
    data() {
        return {
            today: today,
            form: {
                id: "",
                firstName: "",
                lastName: "",
                password: "",
                dateOfBirth: today,
                doctor: this.doctorForm
            },
            doctorForm: {
                id: "",
                firstName: "",
                lastName: "",
                password: "",
            },
            requirements: [
                { id: "empty", requirement: "All fields must be filled out." },
                { id: "numbers", requirement: "First name and last must not contain any numbers." },
                { id: "specialchars", requirement: "First name and last name must not contain any special characters (Letter accents excluded)." },
                { id: "password", requirement: "Password must be at least 6 characters long and contain at least 1 uppercase letter and 1 special character or number." }
            ],
            doctors: this.axios.get(this.$backend.getUrlGetDoctors(), this.doctorForm)
                        .then(() => {
                            console.log(this.doctors)
                        })
        }
    },
    methods: {
        submitForm() {
            if (this.unhandledRequirements.length == 0) {
                this.axios.post(this.$backend.getUrlPostPatientToDoctorById(1), this.form)
                .then(() => {
                    console.log(this.form)
                    this.form.firstName = ""
                    this.form.lastName = ""
                    this.form.password = ""
                    this.form.dateOfBirth = today
                    console.log("Patient submitted")
                })
                .catch(() => console.log("Invalid request", this.form))
            }
            else {
                console.log("Submission not accepted due to unhandled requirements")
            }
        }
    },
    computed: {
        unhandledRequirements() {
            let updatedRequirements = this.requirements

            if ((this.form.firstName.length * this.form.lastName.length * this.form.password.length) !== 0) {
                updatedRequirements = updatedRequirements.filter((req) => req.id != "empty")
            }

            if (!specialChars.test(this.form.firstName + this.form.lastName)) {
                updatedRequirements = updatedRequirements.filter((req) => req.id != "specialchars")
            }

            if (!numbers.test(this.form.firstName + this.form.lastName)) {
                updatedRequirements = updatedRequirements.filter((req) => req.id != "numbers")
            }

            if ((numbers.test(this.form.password) || specialChars.test(this.form.password)) && uppercase.test(this.form.password) && this.form.password.length >= 6) {
                updatedRequirements = updatedRequirements.filter((req) => req.id != "password")
            }
            return updatedRequirements
        }
    }
}

</script>


<style>
    #container {
        width: 400px;
        border-radius: 20px;
        background-color: rgb(192, 192, 192);
        padding: 10px;
    }

</style>