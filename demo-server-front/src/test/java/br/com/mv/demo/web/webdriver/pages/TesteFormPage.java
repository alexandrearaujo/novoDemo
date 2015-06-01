package br.com.mv.demo.web.webdriver.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.modulo.webdriver.pages.AbstractPage;

public class TesteFormPage extends AbstractPage {

	@FindBy(css = "input[type=text]")
	@CacheLookup
	private WebElement descTeste;
	
	@CacheLookup
    private WebElement btnSalvar;

	
	public TesteFormPage(WebDriver driver, String context, int port) {
		super(driver, context, port);
		get("teste/new");
		PageFactory.initElements(driver, this);
	}
	
	
	public void createTeste(TesteListPage testeList, String descTeste) {
		this.descTeste.sendKeys(descTeste);
		this.btnSalvar.click();
		PageFactory.initElements(driver, testeList);
	}
	
	public void editTeste(TesteListPage testeList, String descTeste) {
		this.descTeste.sendKeys(descTeste);
		this.btnSalvar.click();
		PageFactory.initElements(driver, testeList);
	}
}
