'use strict';

const { request } = require('http');
const port = 8080;
const devices = 10;

let sum = 0.0;
let actual_devices = devices;

// recursive function in order to guarantee that the last request is the one that prints the final mean
// a loop would have parallelized the process, without that guarantee
function myrequest(id){
    const req = request({port:port, path:`/?id=${id}`}, res => { // req=request, res=response
        const chunks = [];
        res.setEncoding('utf8');
        res.on('data', chunk => chunks.push(chunk));

        res.on('end', () => {
            let temp = JSON.parse(chunks.join(''))['value'];
            if (temp == null || temp == undefined || isNaN(temp)){
                temp = 0;
                actual_devices--;
            }
            sum += temp;
            // console.log("temp sum: ", sum);
            if (id === devices-1) {console.log("final mean: ", sum/actual_devices);}
            else {myrequest(id+1);}
        });
    });

    req.on('error', e => {
        console.error(`problem with request: ${e.message}`);
        });
    
    req.end(); 
}

myrequest(0);