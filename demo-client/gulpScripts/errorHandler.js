module.exports = {
    onError: function (error) {
        console.log(error.toString());
        this.emit('end');
    }
};