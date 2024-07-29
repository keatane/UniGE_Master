'use strict';

const { request } = require('http');
const { whilst } = require('async');
const port = 8080;
const mainPath = '/?id=';
const devices = 10;

let counter = 0;

function httpRequest(path, postData, cb) {
    const options = {
        hostname: 'localhost',
        port: port,
        path: path + postData,
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
                    (temp == null || temp == undefined || isNaN(temp)) ? cb(null, data.join('')) : console.log(data.join(''));
                }
            );
        });
    req.on('error', cb);
    req.write(postData);
    req.end();
}

whilst(
    cb => cb(null, (counter < devices)), // test, no error occurs
    cb => httpRequest(mainPath, counter.toString(), cb), // iteratee
    (err, res) => err ? console.error(err.message,res) : console.log(res) // final callback
);