package br.com.mv.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TESTE_FILHO_2")
@Getter @Setter
public class TesteFilho2 extends Teste {
	
private static final long serialVersionUID = 1L;
	
	@Column(name = "FILHO_2_CODIGO")
	private Long filhoTesteCodigo;

}
