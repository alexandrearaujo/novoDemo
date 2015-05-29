package br.com.mv.demo.web.functional;

import java.text.ParseException;

import org.junit.Test;

import br.com.mv.demo.web.webdriver.pages.TesteFormPage;
import br.com.mv.demo.web.webdriver.pages.TesteListPage;
import br.com.mv.modulo.WebDriverTestsConfig;

public class WebDriverCrudTesteIT extends WebDriverTestsConfig {
	
	private TesteListPage testeList;
	private TesteFormPage testeForm;
	
	@Test
	public void successfullyCrudTeste() throws ParseException {
		String expectedTeste = "WEBDRIVER TESTE";
		String expectedEditedTeste = "WEBDRIVER TESTE ALTERADO";
		
		testeList = new TesteListPage(webDriver);
		testeForm = new TesteFormPage(webDriver);
		
		testeForm.createTeste(testeList, expectedTeste);
		testeList.listTeste(expectedTeste);
		testeList.toEditTeste(testeForm);
		testeForm.editTeste(testeList, expectedEditedTeste);
		testeList.listTeste(expectedEditedTeste);
		testeList.excludeTeste();
		testeList.confirmExcludeTeste();
	}

}
