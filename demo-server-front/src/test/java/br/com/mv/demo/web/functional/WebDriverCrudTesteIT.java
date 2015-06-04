package br.com.mv.demo.web.functional;

import java.text.ParseException;

import org.junit.Test;

import br.com.mv.demo.DemoWebDriverTestsConfig;
import br.com.mv.demo.web.webdriver.pages.TesteFormPage;
import br.com.mv.demo.web.webdriver.pages.TesteListPage;

public class WebDriverCrudTesteIT extends DemoWebDriverTestsConfig {
	
	private TesteListPage testeList;
	private TesteFormPage testeForm;
	
	@Test
	public void successfullyCrudTeste() throws ParseException {
		String expectedTeste = "WEBDRIVER TESTE";
		String expectedEditedTeste = "WEBDRIVER TESTE ALTERADO";
		
		testeList = new TesteListPage(webDriver, contextPath, port);
		testeForm = new TesteFormPage(webDriver, contextPath, port);
		
		testeForm.createTeste(testeList, expectedTeste);
		testeList.listTeste(expectedTeste);
		testeList.toEditTeste(testeForm);
		testeForm.editTeste(testeList, expectedEditedTeste);
		testeList.listTeste(expectedEditedTeste);
		testeList.excludeTeste();
		testeList.confirmExcludeTeste();
	}

}
