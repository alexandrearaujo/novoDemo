var gulp = require('gulp');
var changed = require('gulp-changed');
var paths = require('./paths.js');

gulp.task('copy-app-js', function() {
	return gulp.src(paths.src.app)
			   .pipe(changed(paths.dest.app))
			   .pipe(gulp.dest(paths.dest.app));
});