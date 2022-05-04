<template>
    <div>
        <form id="doctorForm" @submit.prevent="submitDoctorForm" >
            <ul>
                <p class="required"> {{ updatedFirstNameRequirements }} </p>
                <label>
                    {{ doctorForm[0].label }}: <input :type="doctorForm[0].type" v-model="doctorForm[0].content" />
                </label>
            </ul>
            <button type="submit"> Submit </button>
        </form>
        
    </div>
</template>

<script>

let formId = 0
let firstNameRequirements = 
    "First name must not contain any numbers." +
    "\nFirst name must not contain any special characters (Letter accents excluded)." +
    "\nFirst name must not be empty."
    
let lastNameRequirements =
    "Last name must not contain any numbers." +
    "\nLast name must not contain any special characters (Letter accents excluded)." +
    "\nLast name must not be empty."

export default {
    name: "DoctorForm",
    data() {
        return {
            doctorForm: [
                { id: formId++, label: "First name", type: "text", content: "", required: firstNameRequirements },
                { id: formId++, label: "Last name", type: "text", content: "", required: lastNameRequirements},
                { id: formId++, label: "Password", type: "password", content: ""}
            ]
        }
    },
    methods: {
        validateForm() {
            if (this.doctorForm[0].content === "") {
                console.log("Form validated. Result false")
                return false
            }
            else {
                console.log("Form validated. Result true")
                return true
            }
        },
        submitDoctorForm() {
            this.axios.post(this.$backend.getUrlPostDoctor(), this.doctorForm)
                .then(() => {
                    this.doctorForm.firstName = ""
                    this.doctorForm.lastName = ""
                    this.doctorForm.password = ""
                    console.log("Doctor added")
                })
                .catch(() => console.log("Invalid request"))
        }
    },
    computed: {
        updatedFirstNameRequirements() {
            return firstNameRequirements
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