package br.com.mv.demo.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mv.demo.model.FilhoTeste;
import br.com.mv.demo.repository.FilhoTesteRepository;

@Service
@Transactional(readOnly = true)
public class FilhoTesteBusiness {
	
	@Autowired
	private FilhoTesteRepository filhoTesteRepository;
	
	
	@Transactional
	public FilhoTeste salvar(FilhoTeste filhoTeste) {
		return filhoTesteRepository.save(filhoTeste);
	}

}
