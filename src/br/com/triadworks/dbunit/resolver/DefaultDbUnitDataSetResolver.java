package br.com.triadworks.dbunit.resolver;

import java.io.FileInputStream;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

public class DefaultDbUnitDataSetResolver implements DbUnitDataSetResolver {

	@Override
	public IDataSet resolve(String dataSetXmlPath) {
		FlatXmlDataSet dataSet = buildDataSet(dataSetXmlPath);
		ReplacementDataSet replacement = configureReplacement(dataSet);
		return replacement;
	}

	protected ReplacementDataSet configureReplacement(FlatXmlDataSet dataSet) {
		ReplacementDataSet replacement = new ReplacementDataSet(dataSet);
		replacement.addReplacementObject("[NULL]", null);
		return replacement;
	}

	private FlatXmlDataSet buildDataSet(String dataSetXmlPath){
		try {
			return new FlatXmlDataSetBuilder().build(new FileInputStream(dataSetXmlPath));
		} catch (Exception e) {
			throw new IllegalStateException(
					"It's not possible to create DbUnit dataset: " 
						+ e.getMessage(), e);
		}
	}

}