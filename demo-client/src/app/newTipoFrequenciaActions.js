/**
 * Begin Actions for New/Edit
 */

ViewModelTipoFrequenciaNewEdit.prototype.formatHour = function(stringHours){
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

ViewModelTipoFrequenciaNewEdit.prototype.toJSON = function(){
	console.log('toJSON prototype');
    var tipoFrequencia = ko.toJS(this);
    tipoFrequencia.horarioInicial = this.formatHour(this.horarioInicial);
    console.log(tipoFrequencia.horarioInicial);
    return tipoFrequencia; 
};


ViewModelTipoFrequenciaNewEdit.prototype.clearDetalheTipoFrequencia = function(){
	
	var data = ko.toJSON(this);
	var $tbody = $('tbody');
	var lines = $tbody.find('tr');
	var value = this.horarioInicial();
	var previousValue = $('#horarioInicial').attr('data-previous-value').split(':')[0]+':'+$('#horarioInicial').attr('data-previous-value').split(':')[1];

	if(previousValue != this.horarioInicial() && (lines != null && (lines.length == 1 && lines.text() != "Sem Registro " || lines.length > 1) )){
		 $.post(
				 '/estratificacao/tipoFrequencia/clearDetalhes', data, 
				 function(data) {
					console.log('back do clearDetalhes!');
					console.log(data);
			  		$('#detalheTipoFrequenciaTable').html(data);
			  		$('#bootstrapTable').bootstrapTable();
			  		$('#horarioInicial').attr('data-previous-value',value);
			})
			.error( function (jqXHR, status, error) {
				console.log(jqXHR.responseText);
				console.log(status),
				console.log(error);
			 });
	}
}



window.viewModelTipoFrequenciaNewEdit = new ViewModelTipoFrequenciaNewEdit(
																			$('#id').val(),
																			$('#descFrequencia').val(), 
																			$('#periodicidade').val(), 
																			$('#descImpressaoReceita').val(), 
																			$('#horarioInicial').val()
																		);

ko.applyBindings(window.viewModelTipoFrequenciaNewEdit);