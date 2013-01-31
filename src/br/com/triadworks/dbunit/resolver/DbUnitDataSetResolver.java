package br.com.triadworks.dbunit.resolver;

import org.dbunit.dataset.IDataSet;

public interface DbUnitDataSetResolver {

	public IDataSet resolve(String dataSetXmlPath);

}
