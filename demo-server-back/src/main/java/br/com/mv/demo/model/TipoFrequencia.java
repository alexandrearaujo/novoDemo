package br.com.mv.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TIPO_FREQUENCIA")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class TipoFrequencia implements Serializable
{
	/**
	 * Generated by IDE
	 */
	private static final long serialVersionUID = 7820222083399929059L;

	@Id
    @SequenceGenerator(name = "SEQ_TIPO_FREQUENCIA", sequenceName = "SEQ_TIPO_FREQUENCIA", allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="SEQ_TIPO_FREQUENCIA" )
    @Column(name = "CD_TIPO_FREQUENCIA", length = 8, nullable = false)
    private Long id;
    
    @Column(name = "DS_TIPO_FREQUENCIA")
    @NotNull
    @Size(max = 100)
    private String descricaoFrequencia;
    
    @Column(name = "DS_IMPRESSAO_RECEITA")
    @Size(max = 150)
    private String impressaoReceita;
    
    @Column(name = "NR_PERIODICIDADE")
    @NotNull
    @Min(1)
    @Max(24)
    private Integer periodicidade;
    
    @Column(name = "HR_INICIAL")
    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    @Temporal(TemporalType.TIME)
    private Date horarioInicial;
    
    @OneToMany(mappedBy = "tipoFrequencia", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Size(min = 1)
    private List<DetalheTipoFrequencia> detalhes;

    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoFrequencia other = (TipoFrequencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
