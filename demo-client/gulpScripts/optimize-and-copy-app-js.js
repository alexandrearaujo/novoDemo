var gulp = require('gulp');
var changed = require('gulp-changed');
var errorHandler = require('./errorHandler.js');
var sourcemaps = require('gulp-sourcemaps');
var paths = require('./paths.js');
var uglify = require('gulp-uglify');

gulp.task('optimize-and-copy-app-js', function() {
	return gulp.src(paths.src.app)
			   .pipe(changed(paths.dest.app))
			   .pipe(sourcemaps.init({loadMaps: true}))
			   .pipe(uglify().on('error', errorHandler.onError))
			   .pipe(sourcemaps.write())
			   .pipe(gulp.dest(paths.dest.app));
});