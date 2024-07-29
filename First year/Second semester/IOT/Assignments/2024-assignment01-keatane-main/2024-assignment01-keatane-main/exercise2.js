'use strict'; 

function stringify(value,p) {
    function replacer(k, v) {
        
        // Invalid condition
        if(!p) return;

        // If we got an array, we will 'explode' it in its elements (that are not another array and satisfy the property p) and so on
        if (Array.isArray(v))
            return v.map(obj => Object.fromEntries(Object.entries(obj).filter(([k, v]) => (!Array.isArray(v) && p(k)))));
        
        return v;
    }
    return JSON.stringify(value, replacer);
}

console.log(stringify([{ city: 'Milano', air_quality: 'red', temperature: 10 }, { air_quality: 'yellow', 'temperature': 20, 'sea_conditions': 3, city: 'Genova' }], k => k.match(/^[a-z]+$/)));
console.log(stringify([{ city: 'Milano', air_quality: 'red', temperature: 10 }, { air_quality: 'yellow', 'temperature': 20, 'sea_conditions': 3, city: 'Genova' }], k => k.length < 5));


