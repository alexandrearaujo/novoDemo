'use strict';

var uglify = require('gulp-uglify'),
    sourcemaps = require('gulp-sourcemaps'),
    cssMinify = require('gulp-minify-css'),
    imagemin = require('gulp-imagemin'),
    pngcrush = require('imagemin-pngcrush'),
    less = require('gulp-less'),
    gulp = require('gulp'),
    gutil = require('gulp-util'),
    changed = require('gulp-changed'),
    path = require('path');

var paths = {
    baseUrl: 'file:' + process.cwd() + '/src/',
    less: ['src/less/*.less'],
    images: ["src/img*/**"],
    app: ['src/app/**'],
    destination: './dist/'
};


gulp.task('copy-app-js', function() {
	return gulp.src(paths.app)
			   .pipe(changed(paths.destination + 'app'))
			   .pipe(sourcemaps.init())
			   .pipe(uglify().on('error', function(e) { console.log('\x07',e.message); return this.end(); }))
			   .pipe(sourcemaps.write("./"))
			   .pipe(gulp.dest(paths.destination + 'app'));
});

gulp.task('copy-images', function() {
    return gulp.src(paths.images)
    	       .pipe(changed(paths.destination))
	           .pipe(imagemin({
	               progressive: true,
	               svgoPlugins: [{removeViewBox: false}],
	               use: [pngcrush()]
	           }))
	           .pipe(gulp.dest(paths.destination));
});

gulp.task('less', function () {
    return gulp.src(paths.less)
    		   .pipe(changed(paths.destination + '/css'))
	    	   .pipe(sourcemaps.init())
	    	   .pipe(less())
	    	   .pipe(cssMinify({noRebase: true}))
	    	   .pipe(sourcemaps.write())
	    	   .pipe(gulp.dest(paths.destination + '/css'))
	    	   .on('error', gutil.log);
});