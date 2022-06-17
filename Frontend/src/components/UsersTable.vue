<template>
    <div class="table-page">
        <h1 class="table-page-header">My Patients</h1>
        <div class="search-bar">
            <form class>
                <input type="text"/>
            </form>
        </div>
        <table class="table">
            <thead>
               <tr>
                <th v-for="(name,key) in columnNames" :key="key">{{name}}</th>
               </tr>
            </thead>
            <tbody>
                <tr v-for="(element,key) in rowElements" :key="key">
                    <td v-for="(entry,key) in element" :key="key">{{entry}}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                sortBy: "id",
                sortOrder: 1,
                rowElements: [
                    {id: 1, firstName: "Gauss", lastName: "Jordan", dateOfBirth: "1998-12-07"},
                    {id: 2, firstName: "Jørgen", lastName: "Villadsen", dateOfBirth: "1979-10-10"},
                    {id: 3, firstName: "Michael", lastName: "Pedersen", dateOfBirth: "1969-06-09"}
                ],

                columnNames: ['Id','First Name', 'Last Name', 'Date of Birth']
            }
        }
    }
</script>

<style>
    .table-page {
        position:absolute;
        margin:40px;
        font-family:sans-serif;
    }
    .search-bar {
        text-align: left;
        margin-bottom: 15px;
    }
    .search-bar form,
    .search-bar input {    
        /* box-shadow: 0 0 20px rgba(0,0,0,0.15); */
        width:200px;
        
    }
    /* table styling inspired by https://dev.to/dcodeyt/creating-beautiful-html-tables-with-css-428l */
    .table {
        position:absolute;
        border-collapse: collapse;
        table-layout: fixed;
        width:800px;
        box-shadow: 0 0 20px rgba(0,0,0,0.15);
        font-size:0.9em;

    }
    .table thead tr {
        background-color:lightgray;
        text-align: left;
    }
    .table th,
    .table td {
        padding: 10px 12px;
    }
    .table th {
        cursor:pointer;
    }
    .table tbody tr {
        border-bottom: 1px solid lightgray;
        text-align: left;
    }
    .table tbody tr:hover {background-color: white}
    
</style>