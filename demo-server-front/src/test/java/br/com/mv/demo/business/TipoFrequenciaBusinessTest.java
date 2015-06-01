package br.com.mv.demo.business;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.mv.demo.DemoTestsConfig;
import br.com.mv.demo.model.TipoFrequencia;

public class TipoFrequenciaBusinessTest extends DemoTestsConfig {
	
	@Autowired
	private TipoFrequenciaBusiness tipoFrequenciaBusiness;
	
	private TipoFrequencia tipoFrequencia;
	
	
	@Test
	@Transactional
	public void adddHorariosMedicacaoTest() throws Exception {
	    createTipoFrequenciaTest();
	    tipoFrequencia = tipoFrequenciaBusiness.adicionarHorariosMedicacao(tipoFrequencia);
	    assertThat(tipoFrequencia.getDetalhes(), not(empty()));
		assertThat(tipoFrequencia.getDetalhes(), hasSize(5));
	}
	
	
	private void createTipoFrequenciaTest() {
		tipoFrequencia = new TipoFrequencia();
		tipoFrequencia.setId(1L);
		tipoFrequencia.setDescricaoFrequencia("TESTE AUTOMATIZADO");
		tipoFrequencia.setImpressaoReceita("TESTE IMPRESSAO RECEITA");
		tipoFrequencia.setHorarioInicial(new Date());
		tipoFrequencia.setPeriodicidade(5);
	}

}
