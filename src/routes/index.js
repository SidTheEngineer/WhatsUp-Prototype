'use strict';
var express = require('express');

module.exports = (function() {

    var router = express.Router();

    // Index/home route.
    router.get('/', function(req, res) {
        res.sendFile('campus-pins.html', { root: __dirname + '/../' });
    });

    return router;

})();