package br.com.mv.demo.business;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mv.demo.model.NovoTeste;
import br.com.mv.demo.repository.NovoTesteRepository;
import br.com.mv.modulo.business.GenericCrudBusiness;

@Service
@Transactional(readOnly=true)
public class NovoTesteBusiness extends GenericCrudBusiness<NovoTeste> {
	
	private final NovoTesteRepository novoTesteRepository;

	@Autowired
	public NovoTesteBusiness(NovoTesteRepository novoTesteRepository) {
		super(novoTesteRepository);
		this.novoTesteRepository = novoTesteRepository;
	}
	
	@Override
	public Page<NovoTeste> listModel(NovoTeste t, Pageable pageable) {
		String descricaoLiked = null;
		
		if (StringUtils.isNotBlank(t.getDescricao())) {
			descricaoLiked = "%" + t.getDescricao() + "%";
		}
		
		if (StringUtils.isNotBlank(t.getDescricao())) {
			return novoTesteRepository.findByDescricaoLikeIgnoreCase(descricaoLiked, pageable);
		} else {
			return novoTesteRepository.findAll(pageable);
		}
	}

}
