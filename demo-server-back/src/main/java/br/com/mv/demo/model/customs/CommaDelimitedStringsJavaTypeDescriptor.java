package br.com.mv.demo.model.customs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.MutableMutabilityPlan;

public class CommaDelimitedStringsJavaTypeDescriptor extends AbstractTypeDescriptor<List> {

	private static final long serialVersionUID = -1676291800251166209L;
	
	public static final String DELIMITER = ",";

    public CommaDelimitedStringsJavaTypeDescriptor() {
        super(
            List.class,
            new MutableMutabilityPlan<List>() {
                @Override
                protected List deepCopyNotNull(List value) {
                    return new ArrayList( value );
                }
            }
        );
    }

    @Override
    public String toString(List value) {
        return ( (List<String>) value ).stream().collect( Collectors.joining( DELIMITER ) );
    }

    @Override
    public List<String> fromString(String string) {
        List<String> values = new ArrayList<>();
        if (string != null) {
        	Collections.addAll( values, string.split( DELIMITER ) );
        }
        return values;
    }

    @Override
    public <X> X unwrap(List value, Class<X> type, WrapperOptions options) {
        return (X) toString( value );
    }

    @Override
    public <X> List wrap(X value, WrapperOptions options) {
        return fromString( (String) value );
    }
}
