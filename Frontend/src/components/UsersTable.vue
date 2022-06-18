<template>
<div>
    <div class="table-wrapper">
        <table class="table">
            <thead>
               <tr>
                <th v-for="(names,key) in columnNames" :key="key">{{names}}</th>
               </tr>
            </thead>
            <tbody>
                <tr v-for="(element,key) in rowElements" :key="key">
                    <td v-for="(entry,key) in element" :key="key">{{entry}}</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
</template>

<script>
    export default {
        data() {
            return {
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
    .container {
        display:flex;
        justify-content: center;
    }
    .table-wrapper {
        width:810px;
        max-height: 380px;
        overflow-y: scroll;
    }
    .table-wrapper thead,
    .table-wrapper th {
        position: sticky;
        top:0;
    }
    /* table styling inspired by https://dev.to/dcodeyt/creating-beautiful-html-tables-with-css-428l */
    .table {
        border-collapse: collapse;
        table-layout: fixed;
        width:800px;
        box-shadow: 0 0 20px rgba(0,0,0,0.15);
        font-size:0.9em;
        font-family: sans-serif;
        height: 200px;
        overflow-y: scroll;
    }
    .table thead tr {
        background-color:var(--accent-color);
        text-align: left;
    }
    .table th,
    .table td {
        padding: 10px 12px;
        cursor:pointer;
    }
    .table tbody tr {
        border-bottom: 1px solid lightgray;
        text-align: left;
    }
    .table tbody tr:hover {background-color: white}
    
</style>