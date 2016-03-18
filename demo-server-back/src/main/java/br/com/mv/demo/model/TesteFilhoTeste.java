package br.com.mv.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TESTE_FILHO_TESTE")
@Getter @Setter @EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
public class TesteFilhoTeste implements Serializable {
	
	/**
	 * Generated by IDE
	 */
	private static final long serialVersionUID = -1363324737150234557L;

	@Id
	@ManyToOne
	@JoinColumn(name = "CD_TESTE")
//	@JsonManagedReference(value = "teste-filho")
	private Teste teste;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "CD_FILHO_TESTE")
	private FilhoTeste filhoTeste;
}