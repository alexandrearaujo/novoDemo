package br.com.mv.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TESTE_FILHO_1")
@Getter @Setter
@Polymorphism(type = PolymorphismType.EXPLICIT)
public class TesteFilho1 extends Teste {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "FILHO_1_DESCRICAO")
	private String filhoTesteDescricao;

}
