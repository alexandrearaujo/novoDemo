package br.com.mv.demo.model.type;

import java.util.Arrays;

public enum EnumTipoTeste {
	
	LIBERADO(1L),
	ATUALIZANDO(2L),
	BLOQUEADO(3L);
	
	private Long situacaoTeste;
	
	private EnumTipoTeste(Long situacaoTeste) {
		this.situacaoTeste = situacaoTeste;
	}
	
	public static EnumTipoTeste fromSituacaoTeste(Long situacaoEnumTeste) {
		return Arrays.asList(EnumTipoTeste.values())
				.stream()
				.filter(enumTipoTeste -> enumTipoTeste.getSituacaoTeste().equals(situacaoEnumTeste))
				.findFirst()
				.get();
	}
	
	public Long getSituacaoTeste() {
		return situacaoTeste;
	}

}
