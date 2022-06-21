<template>
    <div id="container">
        <h3> Patient Submission Form </h3>
        <form @submit.prevent="submitForm">
            <ul>
                <p class="requirements-text" v-requirement v-for="{requirement} in unhandledRequirements" :key="requirement"> *{{ requirement }} </p>
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
                    Date of birth: <input type="date" min="1900-01-01" :max="today" v-model="form.dateOfBirth" />
                </label>
            </ul>
            <ul> 
                <label for="doctors"> Doctor: </label>
                <select name="doctors" v-model="selectedDoctorId" :disabled="doctors.length == 0">
                    <option :value="null" selected disabled hidden> {{ doctorSelectDefault }} </option>
                    <option v-for="{id, firstName, lastName} in doctors" :key="id" :value="id"> {{firstName}} {{lastName}} </option>
                </select>
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
            doctorSelectDefault: "--Select a doctor--",
            form: {
                id: "",
                firstName: "",
                lastName: "",
                password: "",
                dateOfBirth: today,
                doctor: null
            },
            selectedDoctorId: null,
            requirements: [
                { id: "empty", requirement: "All fields must be filled out." },
                { id: "numbers", requirement: "First name and last must not contain any numbers." },
                { id: "specialchars", requirement: "First name and last name must not contain any special characters (Letter accents excluded)." },
                { id: "password", requirement: "Password must be at least 6 characters long and contain at least 1 uppercase letter and 1 special character or number." }
            ],
            doctors: []
        }
    },
    async mounted() {
        await this.axios.get(this.$backend.getUrlGetDoctors()).then((response) => this.doctors = response.data)
        this.doctorSelectDefault = this.doctors.length == 0 ? "--No available doctors--" : "--Select a doctor--"
    },
    methods: {
        submitForm() {
            if (this.unhandledRequirements.length == 0) {
                this.axios.post(this.$backend.getUrlPostPatient(), this.form)
                .then(() => {
                    this.form.firstName = ""
                    this.form.lastName = ""
                    this.form.password = ""
                    this.form.dateOfBirth = today
                    console.log("Patient submitted succesfully")
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
    },
    watch: {
        selectedDoctorId(value) {
            this.form.doctor = {id: value}
        }
    }
}

</script>


<style>
    #container {
        width: 400px;
        border-radius: 20px;
        background-color: var(--accent-color);
        padding: 10px;
    }
    .requirements-text {
        color: var(--strong-text-color);
    }

</style>