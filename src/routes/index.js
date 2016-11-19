'use strict';

var express = require('express');

module.exports = (function() {

    var router = express.Router();

    router.get('/', function(req, res) {
        res.send('<h1>Campus Pins!!!</h1>');
    });

    return router;

})();