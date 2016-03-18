package br.com.mv.demo.model.type;

import java.util.Arrays;

public enum EnumTipoTratamentoNome {
	
	SR("Sr(a)."),
	DR("Dr(a).");
	
	private String descricao;
	
	
	private EnumTipoTratamentoNome(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static EnumTipoTratamentoNome fromDescricao(String descricao) {
		return Arrays.asList(EnumTipoTratamentoNome.values())
				.stream()
				.filter(enumTipoTratamento -> enumTipoTratamento.getDescricao().equals(descricao))
				.findFirst()
				.get();
	}

}
