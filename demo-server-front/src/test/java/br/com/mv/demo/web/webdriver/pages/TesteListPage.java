package br.com.mv.demo.web.webdriver.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.modulo.webdriver.pages.AbstractPage;

public class TesteListPage extends AbstractPage {
	
	@FindBy(css = "input[type=text]")
	@CacheLookup
	private WebElement descTeste;
	
	@CacheLookup
    private WebElement btnPesquisar;
    
	@CacheLookup
    @FindBy(className="btn-editar")
    private WebElement btnEditar;
	
	@CacheLookup
	@FindBy(className="confirmation-callback")
	private WebElement btnExclusao;
	
	@CacheLookup
	@FindBy(className="btn-success")
	private WebElement btnExclusaoSim;
	
	
	public TesteListPage(WebDriver driver) {
		super(driver);
		get("/teste/list");
		PageFactory.initElements(driver, this);
	}
	
	
	public void toEditTeste(TesteFormPage testeForm) {
		this.btnEditar.click();
		PageFactory.initElements(driver, testeForm);
	}
	
	public void listTeste(String descTeste) {
		this.descTeste.sendKeys(descTeste);
		this.btnPesquisar.click();
	}
	
	public void toEditTeste() {
		this.btnEditar.click();
	}
	
	public void excludeTeste() {
		this.btnExclusao.click();
	}
	
	public void confirmExcludeTeste() {
		this.btnExclusaoSim.click();
	}
}