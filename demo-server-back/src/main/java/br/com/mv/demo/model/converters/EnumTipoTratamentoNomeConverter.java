package br.com.mv.demo.model.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.mv.demo.model.type.EnumTipoTratamentoNome;

@Converter
public class EnumTipoTratamentoNomeConverter implements AttributeConverter<EnumTipoTratamentoNome, String> {

	@Override
	public String convertToDatabaseColumn(EnumTipoTratamentoNome attribute) {
		if (attribute == null) {
            return null;
        }
		
		return attribute.getDescricao();
	}

	@Override
	public EnumTipoTratamentoNome convertToEntityAttribute(String dbData) {
		if (dbData == null) {
            return null;
        }
		
		return EnumTipoTratamentoNome.fromDescricao(dbData);
	}

}
