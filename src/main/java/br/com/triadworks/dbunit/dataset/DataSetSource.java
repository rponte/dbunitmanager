package br.com.triadworks.dbunit.dataset;

import java.io.IOException;
import java.io.InputStream;

public interface DataSetSource {

	/**
	 * Returns an input stream of a dataset.
	 * 
	 * @throws FileNotFoundException
	 *             if unable to find the dataset
	 */
	public InputStream getInputStream() throws IOException;

}
