package br.com.mv.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.com.mv.demo.business.TesteBusiness;
import br.com.mv.demo.model.Teste;
import br.com.mv.modulo.exception.GenericMessages;
import br.com.mv.modulo.web.GenericCrudController;

@Controller
@RequestMapping("/teste")
@SessionAttributes(types = Teste.class)
public class TesteController extends GenericCrudController<Teste> {

	@Autowired
	public TesteController(GenericMessages genericMessages,	TesteBusiness testeBusiness) {
		super(genericMessages, testeBusiness);
	}
}