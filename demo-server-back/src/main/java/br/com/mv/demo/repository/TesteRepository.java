package br.com.mv.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.mv.demo.model.Teste;

@Repository
public interface TesteRepository extends JpaRepository<Teste, Long> {

	@Query("select t from Teste t left join fetch t.detalhes left join fetch t.sortedTestes left join fetch t.mappedTestes where t.id = :#{#id}")
	public Teste joinFetch(@Param("id") Long id);
	
	
}
