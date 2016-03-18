package br.com.mv.demo.model.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.mv.demo.model.type.EnumTipoTeste;

@Converter
public class EnumTipoTesteConverter implements AttributeConverter<EnumTipoTeste, Long> {

	@Override
	public Long convertToDatabaseColumn(EnumTipoTeste attribute) {
		if (attribute == null) {
            return null;
        }
		
		return attribute.getSituacaoTeste();
	}

	@Override
	public EnumTipoTeste convertToEntityAttribute(Long dbData) {
		if (dbData == null) {
            return null;
        }
		
		return EnumTipoTeste.fromSituacaoTeste(dbData);
	}

}
