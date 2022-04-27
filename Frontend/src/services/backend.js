export default class Backend {
    constructor() {
        this.urlAPI = "http://localhost:8080/api/v1"
        this.subpathDoctors = "/doctors"
        this.subpathPatients = "/patients"
    }

    ////// Doctor paths //////
    getUrlGetDoctors() {
        return this.urlAPI + this.subpathDoctors
    }

    getUrlPostDoctor() {
        return this.urlAPI + this.subpathDoctors
    }

    getUrlDeleteDoctorById(id) {
        return this.urlAPI + this.subpathDoctors + "/" + id
    }

    getUrlGetDoctorById(id) {
        return this.urlAPI + this.subpathDoctors + "/" + id
    }

    getUrlGetPatientsFromDoctorById(id) {
        return this.urlAPI + this.subpathDoctors + "/" + id + this.subpathPatients
    }

    getUrlPostPatientToDoctorById(id) {
        return this.urlAPI + this.subpathDoctors + "/" + id + this.subpathPatients
    }

    ////// Patient paths //////
    getUrlGetPatients() {
        return this.urlAPI + this.subpathPatients
    }

    getUrlPostPatient() {
        return this.urlAPI + this.subpathPatients
    }

    getUrlDeletePatientById(id) {
        return this.urlAPI + this.subpathPatients + "/" + id
    }

}