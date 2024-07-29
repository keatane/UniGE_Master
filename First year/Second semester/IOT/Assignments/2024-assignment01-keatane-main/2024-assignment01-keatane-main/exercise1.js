'use strict';

function stringify(value) {
    function replacer(key, val) {

        // If the value is null or an array and all its keys are integers, we return it as it is
        if (!val || Array.isArray(val) && Object.keys(val).every(key => Number.isInteger(parseInt(key)))) {
            return val;
        }
        
        // If the value is an object, we return a fresh copy of it, less 'dirty
        if (typeof val === 'object') {
            return Object.assign({}, val);
        }

        // If the value is a string or other, we return it as it is
        return val;
    }
    return JSON.stringify(value, replacer);
}

let a1 = [{ city: 'Milano', air_quality: 'red', temperature: 10 }, { air_quality: 'yellow', 'temperature': 20, 'sea_conditions': 3, city: 'Genova'}];
let a2 = '2,3,5,7,9,11,13'.match(/\d+/);
let a3 = '2,3,5,7,9,11,13'.match(/\d+/g);

console.log(stringify({a1, a2, a3}));
