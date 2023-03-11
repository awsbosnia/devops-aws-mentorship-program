var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  var imageNumber = Math.floor(Math.random()*(9));
  res.render('index', { title: 'DevOps Mentorship Program', image: imageNumber });
});

module.exports = router;
