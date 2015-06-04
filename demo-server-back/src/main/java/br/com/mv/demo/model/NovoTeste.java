package br.com.mv.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="NOVO_TESTE")
@Getter @Setter @AllArgsConstructor @RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
public class NovoTeste implements Serializable {
	
	private static final long serialVersionUID = 1151038177470505468L;

	@Id
    @SequenceGenerator(name = "SEQ_NOVO_TESTE", sequenceName = "SEQ_NOVO_TESTE", allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="SEQ_NOVO_TESTE" )
    @Column(name = "CD_NOVO_TESTE", length = 8, nullable = false)
    private Long id;
	
	@Column(name = "DS_NOVO_TESTE")
    @NotNull
    @Size(max = 100)
    private String descricao;
}