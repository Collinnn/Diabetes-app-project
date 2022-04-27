<template>
    <div>
        <h1>Overview Page</h1>
        <ul>
            <label>
                First name: <input type="text" />
            </label>
        </ul>
        <ul>
            <label>
                Last name: <input type="text" />
            </label>
        </ul>
        <ul>
            <label>
                Password: <input type="text" />
            </label>
        </ul>
            <button v-on:submit.prevent="submitPatientsForm"> Submit </button>
       

        
    </div>    
</template>


<script>
export default {
    name: "overviewPage",
    data() {
        return {
            doctorForm: {
                firstName: "John",
                lastName: "Doe",
                password: "Secure123"
            }
        }
    },
    methods: {
        submitPatientsForm() {
            this.axios.post(this.$backend.getUrlPostDoctor(), this.doctorForm)
                .then(() => {
                    this.doctorForm.firstName = "John"
                    this.doctorForm.lastName = "Doe"
                    this.doctorForm.password = "Secure123"
                    console.log("Doctor added")
                })
        }
    }
}

</script>