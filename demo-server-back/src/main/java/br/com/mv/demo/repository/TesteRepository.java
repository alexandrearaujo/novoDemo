package br.com.mv.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.mv.demo.model.Teste;
import br.com.mv.modulo.repository.GenericCrudRepository;

@Repository
public interface TesteRepository extends GenericCrudRepository<Teste> {
	
	public Page<Teste> findByDescricaoLikeIgnoreCase(String descricao, Pageable pageable);
	public Page<Teste> findAll(Pageable pageable);

}
