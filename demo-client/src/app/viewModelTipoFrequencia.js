/**
 * exmeplo: knockout.JS
 */

/* For List */
function ListViewModelTipoFrequencia(descricao, periodicidade, horaInicial) {
	var self = this;

    self.descricaoFrequencia = ko.observable(descricao);
    self.periodicidade = ko.observable(periodicidade);
    self.horarioInicial = ko.observable( self.formatHour(horaInicial) );
    
};

console.log('loaded list view modal tipoFrequencia');

/* For new/edit */
function ViewModelTipoFrequenciaNewEdit(id, descricao, periodicidade, impReceita, horaInicial) {
	var self = this;
	
	self.id = ko.observable(id);
	self.descricaoFrequencia = ko.observable(descricao);
	self.impressaoReceita = ko.observable(impReceita);
	self.periodicidade = ko.observable(periodicidade);
	self.horarioInicial = ko.observable( horaInicial );
};