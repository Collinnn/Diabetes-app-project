<template>
    <div>
        <form id="doctorForm" @submit.prevent="submitDoctorForm" >
            <ul>
                <p class="required" v-for="{requirement} in updatedFirstNameRequirements" :key="requirement"> *{{ requirement }} </p>
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
            firstNameRequirements: [
                { id: "numbers", requirement: "First name must not contain any numbers." },
                { id: "specialchars", requirement: "First name must not contain any special characters (Letter accents excluded)." }
            ]
        }
    },
    methods: {
        submitDoctorForm() {
            console.log("poopy " + this.doctorForm.firstName)
            this.axios.post(this.$backend.getUrlPostDoctor(), this.doctorForm)
                .then(() => {
                    this.doctorForm.firstName = ""
                    this.doctorForm.lastName = ""
                    this.doctorForm.password = ""
                    console.log("Doctor submitted")
                })
                .catch(() => console.log("Invalid request"))
        }
    },
    computed: {
        updatedFirstNameRequirements() {
            if (this.doctorForm.firstName != "") {
                let updatedRequirements = this.firstNameRequirements

                if (!specialChars.test(this.doctorForm.firstName)) {
                    updatedRequirements = updatedRequirements.filter((req) => req.id != "specialchars")
                }

                if (!numbers.test(this.doctorForm.firstName)) {
                    updatedRequirements = updatedRequirements.filter((req) => req.id != "numbers")
                }
                return updatedRequirements
            }
            
            return []
        }
    }
}

</script>


<style>
 .required {
     color: red;
     font-size: 10px;
     font-family: Verdana, Tahoma;
 }

</style>