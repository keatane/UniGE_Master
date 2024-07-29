'use strict';

const { rejects } = require('assert');
const { request } = require('http');
const port = 8080;
const mainPath = '/?id=';
const devices = 10;

let found = false;

function httpRequest(path, postData) {
    return new Promise((resolve, reject) => {
        const options = {
            hostname: 'localhost',
            port: port,
            path: path + postData,
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Content-Length': Buffer.byteLength(postData)
            }
        };
        const req = request(options, res => {
            const data = [];
            res.on('error', reject);
            res.on('data', chunk => data.push(chunk));
            res.on('end', () => {
                resolve(data.join(''));
            });
        });
        req.on('error', reject);
        req.write(postData);
        req.end();
    });
}

async function main() {
    let found = false;
    const sensors_requests = Array.from({ length: devices }, (_, i) => httpRequest(mainPath, i.toString()));

    try {
        const results = await Promise.all(sensors_requests);
        for (let i = 0; i < results.length; i++) {
            const value = JSON.parse(results[i])['value'];
            if (value != null && value != undefined && !isNaN(value)) {
                console.log(results[i]);
                found = true;
                break;
            }
        }
        if (!found) console.log("No device found");
    } catch (error) {
        console.error(error);
    }
}

main();