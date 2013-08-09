package br.com.triadworks.dbunit.dataset;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class FileSystemDataSetSourceTest {

	private static final File DATASET = new File("src/test/resources/rootDataSet.xml");

	@Test
	public void shouldFindDataSetInTheFileSystem() throws IOException {
		FileSystemDataSetSource dataSet = new FileSystemDataSetSource(DATASET); 
		assertNotNull("dataSet not found", dataSet.getInputStream());
	}
	
	@Test
	public void shouldFindDataSetInTheFileSystemWhenUsingStringAsResourcePath() throws IOException {
		FileSystemDataSetSource dataSet = new FileSystemDataSetSource(DATASET.getAbsolutePath()); 
		assertNotNull("dataSet not found", dataSet.getInputStream());
	}
	
	@Test(expected=FileNotFoundException.class)
	public void shouldThrowAnExceptionWhenUnableToFindDataSet() throws IOException {
		new FileSystemDataSetSource(new File("invalid_file.xml")).getInputStream();
	}

}
