package br.com.mv.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.mv.demo.business.FilhoTesteBusiness;
import br.com.mv.demo.business.MappedTesteBusiness;
import br.com.mv.demo.business.TesteBusiness;
import br.com.mv.demo.model.ComplementoDetalhe;
import br.com.mv.demo.model.DetalheTeste;
import br.com.mv.demo.model.FilhoTeste;
import br.com.mv.demo.model.MappedTeste;
import br.com.mv.demo.model.SortedTeste;
import br.com.mv.demo.model.Teste;
import br.com.mv.demo.model.type.EnumTipoTeste;
import br.com.mv.demo.model.type.EnumTipoTratamentoNome;

@Controller
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	private TesteBusiness testeBusiness;
	
	@Autowired
	private FilhoTesteBusiness filhoTesteBusiness;
	
	@Autowired
	private MappedTesteBusiness mappedTesteBusiness;
	
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public @ResponseBody Teste list(Model model) {
		Teste teste = new Teste();
		teste.setDescricao("TESTE");
		teste.setSituacaoTeste(EnumTipoTeste.ATUALIZANDO);
		teste.setCodigo(2); 
		
		teste.getNome().setApelido("ROBERTIM"); 
		teste.getNome().setPrimeiroNome("ROBERTO");
		teste.getNome().setTratamento(EnumTipoTratamentoNome.SR);
		teste.getNome().setUltimoNome("CARLOS");
		teste.getNome().setNomeDoMeio("P.");
		
		FilhoTeste filho = new FilhoTeste();
		filho.setNome("ROBERTO FILHO");
		filhoTesteBusiness.salvar(filho);
		
		SortedTeste sortedTeste = new SortedTeste();
		sortedTeste.setDescricao("TESTE 1");
		
		SortedTeste sortedTeste2 = new SortedTeste();
		sortedTeste2.setDescricao("NOVO TESTE");
		
		SortedTeste sortedTeste3 = new SortedTeste();
		sortedTeste3.setDescricao("TESTE 2");
		
		MappedTeste mappedTeste = new MappedTeste();
		mappedTeste.setDescricao("NOVO TESTE");
		mappedTeste.setSituacaoTeste(EnumTipoTeste.LIBERADO);
		mappedTesteBusiness.salvar(mappedTeste);
		
		MappedTeste mappedTeste2 = new MappedTeste();
		mappedTeste2.setDescricao("TESTE");
		mappedTeste2.setSituacaoTeste(EnumTipoTeste.ATUALIZANDO);
		mappedTesteBusiness.salvar(mappedTeste2);
		
		MappedTeste mappedTeste3 = new MappedTeste();
		mappedTeste3.setDescricao("TESTE 2");
		mappedTeste3.setSituacaoTeste(EnumTipoTeste.ATUALIZANDO);
		mappedTesteBusiness.salvar(mappedTeste3);
		
		DetalheTeste detalheTeste = new DetalheTeste();
		detalheTeste.setDescricao("TESTE");
		
		ComplementoDetalhe complemento = new ComplementoDetalhe();
		complemento.setComplemento("TESTE"); 
		
		detalheTeste.addComplemento(complemento);
		
		teste.getPhones().add("123");
		teste.getPhones().add("456");
		
		teste.setCpf("11111111111");
		
		teste.addSortedTeste(sortedTeste);
		teste.addSortedTeste(sortedTeste2);
		teste.addSortedTeste(sortedTeste3);
		teste.addMappedTeste(mappedTeste);
		teste.addMappedTeste(mappedTeste2);
		teste.addMappedTeste(mappedTeste3);
		teste.addDetalhe(detalheTeste);
		teste.addFilho(filho); 
		
//		teste.getSortedTestes().add(sortedTeste);
//		teste.getSortedTestes().add(sortedTeste2);
//		teste.getSortedTestes().add(sortedTeste3);
//		teste.getMappedTestes().put(mappedTeste.getSituacaoTeste(), mappedTeste);
//		teste.getMappedTestes().put(mappedTeste2.getSituacaoTeste(), mappedTeste2);
//		teste.getMappedTestes().put(mappedTeste3.getSituacaoTeste(), mappedTeste3);
//		teste.getDetalhes().add(detalheTeste);
//		TesteFilhoTeste filhoTeste = new TesteFilhoTeste();
//		filhoTeste.setFilhoTeste(filho);
//		filhoTeste.setTeste(teste);
//		filho.getPais().add(filhoTeste);
//		teste.getFilhos().add(filhoTeste);
		
		testeBusiness.salvar(teste);
		
		testeBusiness.refresh(teste);
		
		return teste;
	}
	
	@RequestMapping(value = { "/atualizar" }, method = RequestMethod.GET)
	public @ResponseBody Teste atualizar(@RequestParam("id") Teste teste, @RequestParam("idDetalhe") Long idDetalhe) {
		teste.setDescricao("TESTE ATUAL");
		teste.setSituacaoTeste(EnumTipoTeste.LIBERADO);
		teste.setCodigo(3);
		
		DetalheTeste detalheTeste = teste.getDetalhes().stream().findFirst().get(); 
//		DetalheTeste detalheTeste = testeBusiness.getDetalheTesteWithoutData(idDetalhe); 
		
		teste.removeDetalhe(detalheTeste);
//		teste.getDetalhes().remove(detalheTeste);
		
		FilhoTeste filho = teste.getFilhos().stream().findFirst().get().getFilhoTeste();
		teste.removeFilho(filho);
		
//		TesteFilhoTeste filhoTeste = new TesteFilhoTeste(teste, filho);
//		teste.getFilhos().remove(filhoTeste);
		
		testeBusiness.salvar(teste);
		return teste;
	}
	
	@RequestMapping(value = { "/limpar" }, method = RequestMethod.GET)
	public @ResponseBody String deletar() {
		testeBusiness.deletarTodos();
		return "OK";
	}
	
	@RequestMapping(value = { "/mappedTeste" }, method = RequestMethod.GET)
	public @ResponseBody MappedTeste mappedTeste() {
		MappedTeste mappedTeste = new MappedTeste();
		mappedTeste.setDescricao("TESTE ALONE");
		mappedTeste.setSituacaoTeste(EnumTipoTeste.ATUALIZANDO);
		return mappedTesteBusiness.salvar(mappedTeste);
	}
	
	@RequestMapping(value = { "/naturalId" }, method = RequestMethod.GET)
	public @ResponseBody Teste getTesteByNatural() {
		return testeBusiness.getTesteByNaturalId(); 
	}
	
	@RequestMapping(value = { "/findId" }, method = RequestMethod.GET)
	public @ResponseBody Teste findById(@RequestParam Long id) {
		return testeBusiness.findById(id);
	}
	
	@RequestMapping(value = { "/joinFetch" }, method = RequestMethod.GET)
	public @ResponseBody Teste joinFetch(@RequestParam Long id) {
		return testeBusiness.joinFetch(id);
	}
	
	@RequestMapping(value = { "/joinFetchCriteria" }, method = RequestMethod.GET)
	public @ResponseBody Teste joinFetchCriteria(@RequestParam Long id) {
		return testeBusiness.joinFetchCriteria(id);
	}
	
	@RequestMapping(value = { "/entityGraph" }, method = RequestMethod.GET)
	public @ResponseBody Teste fetchEntityGraph(@RequestParam Long id) {
		return testeBusiness.fetchEntityGraph(id);
	}
	
	@RequestMapping(value = { "/entityGraphWithQuery" }, method = RequestMethod.GET)
	public @ResponseBody Teste fetchEntityGraphWithQuery(@RequestParam Long id) {
		return testeBusiness.fetchEntityGraphWithQuery(id);
	}
	
	@RequestMapping(value = { "/insert" }, method = RequestMethod.GET)
	public String insert() {
		testeBusiness.insertTestes();
		return "OK";
	}
	
	@RequestMapping(value = { "/update" }, method = RequestMethod.GET)
	public @ResponseBody String update() {
		testeBusiness.updateTestes();
		return "OK";
	}

}
