package br.com.triadworks.dbunit.resolver;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import br.com.triadworks.dbunit.dataset.DataSetSource;

public class DefaultDbUnitDataSetResolver implements DbUnitDataSetResolver {

	@Override
	public IDataSet resolve(DataSetSource source) {
		FlatXmlDataSet dataSet = buildDataSet(source);
		ReplacementDataSet replacement = configureReplacement(dataSet);
		return replacement;
	}

	protected ReplacementDataSet configureReplacement(FlatXmlDataSet dataSet) {
		ReplacementDataSet replacement = new ReplacementDataSet(dataSet);
		replacement.addReplacementObject("[NULL]", null);
		return replacement;
	}

	private FlatXmlDataSet buildDataSet(DataSetSource source) {
		try {
			return new FlatXmlDataSetBuilder().build(source.getInputStream());
		} catch (Exception e) {
			throw new IllegalStateException(
					"It's not possible to create DbUnit dataset: " 
						+ e.getMessage(), e);
		}
	}

}