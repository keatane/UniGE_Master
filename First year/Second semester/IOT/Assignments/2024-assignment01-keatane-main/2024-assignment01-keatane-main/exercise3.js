'use strict';
const header = require('./header.json');
const data = require('./weather.json');

function toCSV(data){
    let csv = '';

    // Adding the name of the columns
    csv += header.join(',') + '\n';

    // Adding the data values
    let hash = {}
    data.forEach((row) => {
        let rowArray = header.map((key) => {
            // For each key in the header, we will try to find the corresponding value (even 'dirty' ones) in the row
            let tempk = Object.keys(row).filter(x => (x.replace(/\s/g, '').toLowerCase().includes(key.replace(/\s/g, '').toLowerCase()) || x.replace(/\s/g, '').toUpperCase().includes(key.replace(/\s/g, "").toUpperCase()) || x == key))[0];
            tempk ? hash[key] = row[tempk] : hash[key] = '';
            return hash[key];
        });
        csv += rowArray.join(',') + '\n';
    });
    console.log(csv);
    return csv;
}

toCSV(data);
