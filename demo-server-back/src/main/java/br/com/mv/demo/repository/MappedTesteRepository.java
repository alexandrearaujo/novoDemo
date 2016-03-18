package br.com.mv.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mv.demo.model.MappedTeste;

@Repository
public interface MappedTesteRepository extends JpaRepository<MappedTeste, Long> {

}
