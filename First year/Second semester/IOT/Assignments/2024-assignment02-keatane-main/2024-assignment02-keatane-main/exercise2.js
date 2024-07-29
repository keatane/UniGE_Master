'use strict';

const { request } = require('http');
const port = 8080;
const devices = 10;

// recursive function in order to guarantee that the last request is the one that prints the final mean
// a loop would have parallelized the process, without that guarantee
function myrequest(id){
    const req = request({port:port, path:`/?id=${id}`}, res => { // req=request, res=response
        const chunks = [];
        res.setEncoding('utf8');
        res.on('data', chunk => chunks.push(chunk));

        res.on('end', () => {
            let temp = JSON.parse(chunks.join(''))['value'];
            if (!(temp == null || temp == undefined || isNaN(temp))){
                console.log(chunks.join(''));
                return;
            }
            if (id === devices-1) return;
            myrequest(id+1);
        });
    });

    req.on('error', e => {
        console.error(`problem with request: ${e.message}`);
        });
    
    req.end(); 
}

myrequest(0);