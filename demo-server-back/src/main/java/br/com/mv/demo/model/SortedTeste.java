package br.com.mv.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SORTED_TESTE")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(of = "id")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SortedTeste implements Comparable<SortedTeste> {
	
	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "SEQ_SORTED_TESTE", sequenceName = "SEQ_SORTED_TESTE", allocationSize = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SORTED_TESTE")
	private Long id;
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "CD_TESTE")
//	@JsonManagedReference(value = "teste-sorted")
	private Teste teste;

	@Override
	public int compareTo(SortedTeste o) {
		return descricao.compareToIgnoreCase(o.getDescricao());
	}

}
