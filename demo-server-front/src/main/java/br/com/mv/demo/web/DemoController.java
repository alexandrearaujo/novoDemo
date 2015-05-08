package br.com.mv.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.com.mv.demo.business.NovoTesteBusiness;
import br.com.mv.demo.model.NovoTeste;
import br.com.mv.modulo.exception.GenericMessages;
import br.com.mv.modulo.web.GenericCrudController;

@Controller
@RequestMapping("/novoTeste")
@SessionAttributes(types = NovoTeste.class)
public class DemoController extends GenericCrudController<NovoTeste> {

	@Autowired
	public DemoController(GenericMessages genericMessages, NovoTesteBusiness novoTesteBusiness) {
		super(genericMessages, novoTesteBusiness);
	}

}
