package br.com.mv.demo.comparators;

import java.util.Comparator;

import br.com.mv.demo.model.SortedTeste;

public class SortedTesteReverseComparator implements Comparator<SortedTeste> {

	@Override
	public int compare(SortedTeste o1, SortedTeste o2) {
		return o2.compareTo(o1);
	}

	

}
