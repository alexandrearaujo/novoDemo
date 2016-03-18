package br.com.mv.demo.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mv.demo.model.MappedTeste;
import br.com.mv.demo.repository.MappedTesteRepository;

@Service
@Transactional(readOnly = true)
public class MappedTesteBusiness {

	@Autowired
	private MappedTesteRepository mappedTesteRepository;
	
	@Transactional
	public MappedTeste salvar(MappedTeste mappedTeste) {
		return mappedTesteRepository.save(mappedTeste); 
	}
}
