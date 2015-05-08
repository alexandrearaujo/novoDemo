'use strict';

var uglify = require('gulp-uglify'),
    sourcemaps = require('gulp-sourcemaps'),
    cssMinify = require('gulp-minify-css'),
    imagemin = require('gulp-imagemin'),
    pngcrush = require('imagemin-pngcrush'),
    gulp = require('gulp'),
    path = require('path');

var paths = {
    baseUrl: 'file:' + process.cwd() + '/src/',
    css: {
        files: ['src/css/*.css'],
        root: 'src/css'
    },
    images: ["src/img*/**"],
    app: ['src/app/**'],
    destination: './dist/'
};

// Optimize application CSS files and copy to "dist" folder
gulp.task('optimize-and-copy-css', function() {
    return gulp.src(paths.css.files)
    	.pipe(sourcemaps.init())
        .pipe(cssMinify({root : paths.css.root}))
        .pipe(sourcemaps.write())
        .pipe(gulp.dest(paths.destination + 'css'));
});

gulp.task('copy-css-to-target', function() {
    return gulp.src('dist/css/**')
	        .pipe(gulp.dest('./target/classes/static/css'));
});

gulp.task('copy-app-js', function() {
	return gulp.src(paths.app)
	.pipe(sourcemaps.init())
	.pipe(uglify().on('error', function(e) { console.log('\x07',e.message); return this.end(); }))
	.pipe(sourcemaps.write("./"))
	.pipe(gulp.dest(paths.destination + 'app'));
});

gulp.task('copy-app-js-to-target', function() {
    return gulp.src('dist/app/**')
	        .pipe(gulp.dest('./target/classes/static/app'));
});

gulp.task('copy-images', function() {
    return gulp.src(paths.images)
	        .pipe(imagemin({
	            progressive: true,
	            svgoPlugins: [{removeViewBox: false}],
	            use: [pngcrush()]
	        }))
	        .pipe(gulp.dest(paths.destination));
});