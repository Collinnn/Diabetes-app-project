<template>
<div>
    <div class = search-bar>
        <input type="text" placeholder="Search users" @input="search">
    </div> 
    <div class="table-wrapper">
        <table class="table">
            <thead>
               <tr>
                <th v-for="(names,index) in columnNames" :key="index" @click="sortColumn(index)">{{names}}<span style="float:right;">{{arrow}}</span></th>
               </tr>
            </thead>
            <tbody>
                <tr v-for="(patient,index) in visibleRows" :key="index" @click="this.$emit('rowClick',patient)">
                    <td v-for="(entry,index) in patient" :key="index">{{entry}}</td>
                </tr>
            </tbody>
        </table>
    </div>
    <div id="tutorialContainer">
        <span id="tutorial-tip" style="font-size: '24px'; font-weight: bold;"> #Tip </span>
        <p id="tutorial">Click on a column header to sort in ascending order. Click again for descending order.
        <br>Click on a row to be redirected to that user's page.
        </p>
    </div>
    
</div>
</template>

<script>

    const rowToString = function (row) {
        return Object.values(row).join(" ")
    }

    export default {
        data() {
            return {
                visibleRows: [],
                order: 'asc',
                arrow: '▴',
                index: null
            }
        },
        props: {
            rows: Array,
            columnNames: Array
        },
        emits: ['rowClick'],
        methods: {
            search(e) {
                const term = e.target.value.toLowerCase();
                this.visibleRows = this.rows.filter(row => rowToString(row).toLowerCase().includes(term))
            },
            sortColumn(index) {
                //this ensures that every time a new column is clicked, the default sorting order is ascending
                if(!this.index == index) {
                    this.order = 'asc'
                }
                this.index = index
                if(this.order=='asc') {
                    this.arrow = '▴'
                    switch(index) {
                        case 0: this.visibleRows.sort((a,b) => (a.id < b.id) ? -1: (a.id > b.id) ? 1: 0)
                        this.order='desc'
                        break
                        case 1: this.visibleRows.sort((a,b) => (a.firstName < b.firstName) ? -1: (a.firstName > b.firstName) ? 1: 0)
                        this.order='desc'
                        break
                        case 2: this.visibleRows.sort((a,b) => (a.lastName < b.lastName) ? -1: (a.lastName > b.lastName) ? 1: 0)
                        this.order='desc'
                        break
                        case 3: this.visibleRows.sort((a,b) => (a.dateOfBirth < b.dateOfBirth) ? -1: (a.dateOfBirth > b.dateOfBirth) ? 1: 0)
                        this.order='desc'
                        break
                    }
                } else {
                    this.arrow = '▾'
                    switch(index) {
                        case 0: this.visibleRows.sort((a,b) => (a.id > b.id) ? -1: (a.id < b.id) ? 1: 0)
                        this.order='asc'
                        break
                        case 1: this.visibleRows.sort((a,b) => (a.firstName > b.firstName) ? -1: (a.firstName < b.firstName) ? 1: 0)
                        this.order='asc'
                        break
                        case 2: this.visibleRows.sort((a,b) => (a.lastName > b.lastName) ? -1: (a.lastName < b.lastName) ? 1: 0)
                        this.order='asc'
                        break
                        case 3: this.visibleRows.sort((a,b) => (a.dateOfBirth > b.dateOfBirth) ? -1: (a.dateOfBirth < b.dateOfBirth) ? 1: 0)
                        this.order='asc'
                        break
                    }
                }
            }
        },
        mounted() {
            this.visibleRows = this.rows
        },
        watch: {
            rows(){
                this.visibleRows = this.rows
            }
        }
    }
</script>

<style>
    .search-bar {
        text-align: left;
        margin-bottom: 20px;
        font-family: sans-serif;
    }
    .search-bar input {
        min-width: 200px;
        font-size: 0.9em;
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
    .table tbody tr:hover {
        background-color: white
    }
    #tutorialContainer {
        width: fit-content;
        height: fit-content;
        padding: 10px;
        margin-top: 10px;
        border: solid;
        border-radius: 5px;
        border-width: 1px;
        border-color: black;
        text-align: left;
    }
    #tutorialTip {
        margin-top: 10px;
    }
    #tutorial {
        color:var(--text-color);
    }
    
</style>