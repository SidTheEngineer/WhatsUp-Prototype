'use strict';
var express = require('express');

module.exports = (function() {

    var api = express.Router();
    var MongoClient = require('mongodb').MongoClient;
    var config = require('../../config.json');

    // Connect to the database.
    MongoClient.connect(config.dbUrl, function(error, database) {

        if(error) return console.log(error);
        
        // Database object we can use methods from.
        var db = database;

        // API route to send POST with JSON data of campus events.
        api.post('/events', function(req, res) {

            console.log("connection established");

            // Save POST data to events collection in the databse,
            // this should theoretically represent 1 days worth of
            // events.
            db.collection('events').save(req.body, function(error, result) {
                if(error) res.send(error);
                console.log(req.body);
                //res.send("Event saved to database");
            })

        });

    });

    return api;

})();