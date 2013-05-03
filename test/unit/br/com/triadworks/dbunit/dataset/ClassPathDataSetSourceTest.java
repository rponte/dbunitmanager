package br.com.triadworks.dbunit.dataset;

import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class ClassPathDataSetSourceTest {

	@Test
	public void shouldFindDataSetInTheRootOfClasspath() throws IOException {
		ClassPathDataSetSource dataSet = new ClassPathDataSetSource("rootDataSet.xml");
		assertNotNull("dataSet not found", dataSet.getInputStream());
	}
	
	@Test
	public void shouldFindDataSetInsideOfPackages() throws IOException {
		ClassPathDataSetSource dataSet = new ClassPathDataSetSource("dbunit/dataset/xml/anotherDataSet.xml");
		assertNotNull("dataSet not found", dataSet.getInputStream());
	}
	
	@Test(expected=FileNotFoundException.class)
	public void shouldThrowAnExceptionWhenUnableToFindDataSet() throws IOException {
		new ClassPathDataSetSource("invalid_package/invalida_dataSet.xml").getInputStream();
	}

}
