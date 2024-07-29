'use strict';

const { request } = require('http');
const port = 8080;
const mainPath = '/?id=';
const devices = 10;

let actual_devices = devices;
let string_median = '';
let counter = 0;
let list = [];

function getMedian() {
    if (list.length === 0 || actual_devices === 0) {
        console.log('Median: NaN');
        return;
    }

    list.sort((a, b) => JSON.parse(a)['value'] - JSON.parse(b)['value']);
    counter = Math.ceil(actual_devices / 2) - 1;
    string_median =
        actual_devices % 2 !== 0 ? JSON.parse(list[counter])['value'] : (JSON.parse(list[counter])['value'] + JSON.parse(list[counter + 1])['value']) / 2;
    console.log('Median: ', string_median);
}

async function httpRequest(path, postData) {
    return new Promise((resolve, reject) => {
        const options = {
            hostname: 'localhost',
            port: port,
            path: path + postData,
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Content-Length': Buffer.byteLength(postData),
            },
        };
        const req = request(options, res => {
            const data = [];
            res.on('error', reject);
            res.on('data', chunk => data.push(chunk));
            res.on('end', () => {
                counter++;
                const value = JSON.parse(data.join(''))['value'];
                if (value == null || value == undefined || isNaN(value)) {
                    actual_devices--;
                    resolve(data.join(''));
                } else {
                    list.push(data.join(''));
                }
                resolve()
            });
        });
        req.on('error', reject);
        req.write(postData);
        req.end();
    });
}

async function main() {
    const sensors_requests = Array.from({ length: devices }, (_, i) => httpRequest(mainPath, i.toString()));

    try {
        await Promise.all(sensors_requests); // wait for all promises to resolve
        getMedian(); // then get the median
    } catch (error) {
        console.error(error);
    }
}

main();
