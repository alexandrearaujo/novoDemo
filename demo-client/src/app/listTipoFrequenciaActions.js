/**
 * Begin Actions for List
 */
ListViewModelTipoFrequencia.prototype.formatHour = function(stringHours){
	var date;
	if( stringHours != '' && stringHours != null){
		date = new Date();
		var hoursAndMinusArray = stringHours.split(':');
		date.setHours(hoursAndMinusArray[0]);
		date.setMinutes(hoursAndMinusArray[1]);
		date.setSeconds(00);
		console.log(date);
	}else
		date = '';
	
	return date;
}

ListViewModelTipoFrequencia.prototype.toJSON = function() {
	console.log('toJSON prototype');
    var tipoFrequencia = ko.toJS(this);
    tipoFrequencia.horarioInicial = this.formatHour(this.horarioInicial);
    console.log(tipoFrequencia.horarioInicial);
    return tipoFrequencia; 
};

ListViewModelTipoFrequencia.prototype.filter = function() {
	var data = ko.toJSON(this);
	console.log('execute ajax');
	jQuery.post( "/estratificacao/tipoFrequencia/list", data)
	  .done(function( data ) {
		ko.utils.setHtml($('#results'), data);
	  })
	  .error( function (jqXHR, status, error) {
		console.log(jqXHR.responseText);
		console.log(status),
		console.log(error);
	 })
}; 
/**
 * End Actions for List
 */

console.log('-> apply binds');

window.viewTipoFrequencia = new ListViewModelTipoFrequencia(null, null, null);

ko.applyBindings(window.viewTipoFrequencia);