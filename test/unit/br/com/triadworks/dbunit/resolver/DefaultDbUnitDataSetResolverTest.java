package br.com.triadworks.dbunit.resolver;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.junit.Before;
import org.junit.Test;

import br.com.triadworks.dbunit.dataset.ClassEntryDataSetSource;
import br.com.triadworks.dbunit.dataset.DataSetSource;

public class DefaultDbUnitDataSetResolverTest {

	private static final String[] EXPECTED_TABLE_NAMES = new String[]{"BLOG", "POST", "AUTHOR"};
	private static final DataSetSource VALID_DATASET_SOURCE = new ClassEntryDataSetSource(DefaultDbUnitDataSetResolverTest.class);
	
	DbUnitDataSetResolver resolver;
	
	@Before
	public void setup() {
		resolver = new DefaultDbUnitDataSetResolver();
	}
	
	@Test
	public void shouldResolveDataSet() throws DataSetException {
		IDataSet dataSet = resolver.resolve(VALID_DATASET_SOURCE);
		
		assertArrayEquals("table names", EXPECTED_TABLE_NAMES, dataSet.getTableNames());
	}
	
	@Test
	public void shouldDecorateDataSetWithReplacementDataSet() {
		IDataSet dataSet = resolver.resolve(VALID_DATASET_SOURCE);
		
		assertTrue("replacement dataset", dataSet instanceof ReplacementDataSet);
	}
	
	@Test(expected=IllegalStateException.class)
	public void shouldThrowAnExceptionWhenUnableToResolveDataSetSource() {
		resolver.resolve(null);
	}

}
