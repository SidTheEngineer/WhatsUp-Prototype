'use strict';
var express = require('express');

module.exports = (function() {

    var api = express.Router();

    // API route to send POST with JSON data of campus events.
    api.post('/events', function(req, res) {
        res.send('Post request made to API router');
    });

    return api;

})();