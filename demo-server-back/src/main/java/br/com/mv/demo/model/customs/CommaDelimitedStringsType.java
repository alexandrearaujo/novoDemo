package br.com.mv.demo.model.customs;

import java.util.List;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

public class CommaDelimitedStringsType extends AbstractSingleColumnStandardBasicType<List> {

	private static final long serialVersionUID = 7982768288176906399L;

	public CommaDelimitedStringsType() {
        super(
            VarcharTypeDescriptor.INSTANCE,
            new CommaDelimitedStringsJavaTypeDescriptor()
        );
    }

    @Override
    public String getName() {
        return "comma_delimited_strings";
    }
}
