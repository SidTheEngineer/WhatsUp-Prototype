'use strict';
var express = require('express');
var bodyParser = require('body-parser');
var router = require('./routes/index');
var api = require('./routes/api');
var app = express();
var PORT = 3000;

// Allows us to grab the JSON POST data in req.body.
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));

// Static files.
app.use('/css', express.static(__dirname + '/css'));
app.use('/js', express.static(__dirname + '/js'));
app.use('/images', express.static(__dirname + '/images'));
 
// Routes.
app.use('/', router);
app.use('/api', api);

app.listen(PORT, function() {
    console.log("Server started on port: " + PORT);
});