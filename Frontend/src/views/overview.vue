<template>
    <div>
        <h1>Overview Page</h1>
        <ul>
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
                Password: <input type="text" v-model="doctorForm.password" />
            </label>
        </ul>
            <button @click="submitDoctorForm"> Submit </button>
       

        
    </div>    
</template>


<script>
export default {
    name: "overviewPage",
    data() {
        return {
            doctorForm: {
                firstName: "",
                lastName: "",
                password: ""
            }
        }
    },
    methods: {
        submitDoctorForm() {
            this.axios.post(this.$backend.getUrlPostDoctor(), this.doctorForm)
                .then(() => {
                    this.doctorForm.firstName = ""
                    this.doctorForm.lastName = ""
                    this.doctorForm.password = ""
                    console.log("Doctor added")
                })
        }
    }
}

</script>