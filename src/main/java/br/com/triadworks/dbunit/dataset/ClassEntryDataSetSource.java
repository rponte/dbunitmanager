package br.com.triadworks.dbunit.dataset;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassEntryDataSetSource implements DataSetSource {

	private final Class<?> clazz;
	
	public ClassEntryDataSetSource(Class<?> clazz) {
		this.clazz = clazz;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		String xmlName = clazz.getSimpleName() + ".xml";
		InputStream input = clazz.getResourceAsStream(xmlName);
		if (input == null)
			throw new FileNotFoundException("DataSet cannot be opened because it does not exist.");
		
		return input;
	}

}
