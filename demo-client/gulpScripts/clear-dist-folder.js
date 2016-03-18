var gulp = require('gulp');
var paths = require('./paths.js');
var del = require('del');

gulp.task('clear-dist-folder', function() {
	return del(paths.dest.root);
});