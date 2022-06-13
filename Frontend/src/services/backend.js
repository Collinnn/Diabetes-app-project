export default class Backend {
    constructor() {
        this.urlAPI = "http://localhost:8080/api/v1"
        this.subpathDoctors = "/doctors"
        this.subpathPatients = "/patients"
        this.subpathMeasurements = "/measurements"
    }

    ////// Doctor controller //////
    getUrlGetDoctors() {
        return this.urlAPI + this.subpathDoctors
    }

    getUrlPostDoctor() {
        console.log("Post doctor")
        return this.urlAPI + this.subpathDoctors
    }

    getUrlGetDoctorById(id) {
        return this.urlAPI + this.subpathDoctors + "/" + id
    }

    getUrlPutDoctorById(id) {
        return this.urlAPI + this.subpathDoctors + "/" + id
    }

    getUrlDeleteDoctorById(id) {
        return this.urlAPI + this.subpathDoctors + "/" + id
    }

    getUrlGetPatientsFromDoctorById(id) {
        return this.urlAPI + this.subpathDoctors + "/" + id + this.subpathPatients
    }

    
    ////// Patient controller //////
    getUrlGetPatients() {
        return this.urlAPI + this.subpathPatients
    }
    getUrlPostPatient() {
        return this.urlAPI + this.subpathPatients
    }

    getUrlGetPatientById(id) {
        return this.urlAPI + this.subpathPatients + "/" + id
    }

    getUrlPutPatientById(id) {
        return this.urlAPI + this.subpathPatients + "/" + id
    }

    getUrlDeletePatientById(id) {
        return this.urlAPI + this.subpathPatients + "/" + id
    }


    ////// Measurement controller //////
    getUrlGetMeasurementsFromPatientById(id) {
        return this.urlAPI + this.subpathPatients + "/" + id + this.subpathMeasurements
    }

    getUrlPostMeasurementToPatientById(id) {
        return this.urlAPI + this.subpathPatients + "/" + id + this.subpathMeasurements
    }
}