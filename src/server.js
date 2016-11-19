'use strict';

var express = require('express');
var router = require('./routes/index');
var api = require('./routes/api');
var app = express();
var PORT = 3000;

app.use('/', router);
app.use('/api', api);

app.listen(PORT, function() {
    console.log("Server started on port: " + PORT);
});