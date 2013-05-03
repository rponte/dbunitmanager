package br.com.triadworks.dbunit.dataset;

import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class ClassEntryDataSetSourceTest {

	@Test
	public void shouldFindDataSetWithTheSameNameOfTheClassInTheClaspath() throws IOException {
		ClassEntryDataSetSource dataSet = new ClassEntryDataSetSource(ClassEntryDataSetSourceTest.class);
		assertNotNull("dataSet not found", dataSet.getInputStream());
	}
	
	@Test(expected=FileNotFoundException.class)
	public void shouldThrowAnExceptionWhenUnableToFindDataSet() throws IOException {
		new ClassEntryDataSetSource(FileSystemDataSetSourceTest.class).getInputStream();
	}

}
