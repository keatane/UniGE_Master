'use strict';

const { connect } = require('mqtt');
const { createReadStream } = require('fs');
const { pipeline, Transform } = require('stream');
const { createGunzip } = require('zlib');

const server = 'mqtt://212.78.1.205';
const topic = 'unige/dibris/iot23/assignment05/keatane';
const port = 1883;
const username = 'studenti';
const password = 'studentiDRUIDLAB_1';
const path = process.argv[2] || './room328.txt.gz'; // path to the data 
const reader = createReadStream(path);
const interval = 1000;

const client = connect(`${server}:${port}`, { username, password });

client.on('connect', function () {
  console.log('Connected to MQTT broker');
  publishData();
});

client.on('error', function (error) {
  console.error('MQTT error:', error);
});

// Read data from gzipped file, parse JSON, and publish to MQTT
const publishData = () => {
  const gunzip = createGunzip();

  reader.on('error', (error) => {
    console.error('Error reading file:', error);
  });

  pipeline(
    reader,
    gunzip,
    new Transform({
      construct(callback) { this.data = ''; callback(); },
      transform(chunk, encoding, callback) {
        this.data += chunk; 
        callback();
      },
      flush(callback) {
        try {
          for (const line of this.data.split('\n')) {
            if (line.length === 0) continue;
            this.push(line);
          }
          callback();
        } catch (err) { 
          callback(err); 
        }
      }
    }),
    new Transform({
      transform(chunk, encoding, callback) {
        if (chunk.toString().length != 1) {
          console.log('Publishing message:', chunk.toString());
          setTimeout(() =>client.publish(topic, chunk.toString(), { qos: 2 }, (err) => {
            if (err) {
              console.error('Failed to publish message:', err);
            }
            callback();
          }), interval)
        };
      }
    }),
    (err) => {
      if (err) {
        console.error('Pipeline error:', err);
      }
    }
  );
};
  
// Handle network interruption
client.on('offline', () => {
  console.log('MQTT client offline. Retrying connection...');
});

// Close the MQTT connection
process.on('SIGINT', () => {
  client.end();
  console.log('MQTT client disconnected.');
  process.exit();
});
