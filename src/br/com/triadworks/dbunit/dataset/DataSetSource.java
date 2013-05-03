package br.com.triadworks.dbunit.dataset;

import java.io.IOException;
import java.io.InputStream;

public interface DataSetSource {

	public InputStream getInputStream() throws IOException;
	
}
