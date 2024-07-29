'use strict';

const { request } = require('http');
const { parallel } = require('async');
const port = 8080;
const mainPath = '/?id=';
const devices = 10;

let actual_devices = devices
let counter = 0;
let list = [];

function httpRequest(path, postData, cb) {
    const options = {
        hostname: 'localhost',
        port: port,
        path: path+postData,
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Content-Length': Buffer.byteLength(postData)
        }
    }
    
    const req = request(options, res => {
        const data = [];
        res.on('error', cb);
        res.on('data', chunk => data.push(chunk));
        res.on('end', () => {
                counter++;
                let temp = JSON.parse(data.join(''))['value'];
                if ((temp == null || temp == undefined || isNaN(temp))){
                    actual_devices--;
                    cb(null, data.join(''));
                }
                else{
                    list.push(data.join(''));
                }
                if (counter == devices) getMedian();
            });
        });
    req.on('error', cb);
    req.write(postData);
    req.end();
}

// Request initialization
let sensors = [];
for (let i = 0; i < devices; i++){
    sensors[i] = cb => httpRequest(mainPath, i.toString(), cb);
}

parallel(sensors, (err, res) => err ? console.error(err.message,res) : console.log(res));