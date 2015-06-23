module.exports = {
    src: {
        app: './src/app/**',
        images: "./src/img*/**",
        less: './src/less/*.less'
    },
    dest: {
        root: './dist/',
        app: this.root + 'app',
        css: this.root + 'css',
        images: this.root + 'img'
    }
};