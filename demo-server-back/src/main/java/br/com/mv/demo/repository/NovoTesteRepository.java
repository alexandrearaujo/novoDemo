package br.com.mv.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.mv.demo.model.NovoTeste;
import br.com.mv.modulo.repository.GenericCrudRepository;

@Repository
public interface NovoTesteRepository extends GenericCrudRepository<NovoTeste> {
	
	public Page<NovoTeste> findByDescricaoContainingIgnoreCase(String descricao, Pageable pageable);
	public Page<NovoTeste> findAll(Pageable pageable);

}