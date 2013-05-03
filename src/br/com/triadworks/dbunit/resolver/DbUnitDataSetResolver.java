package br.com.triadworks.dbunit.resolver;

import org.dbunit.dataset.IDataSet;

import br.com.triadworks.dbunit.dataset.DataSetSource;

public interface DbUnitDataSetResolver {

	public IDataSet resolve(DataSetSource source);

}
