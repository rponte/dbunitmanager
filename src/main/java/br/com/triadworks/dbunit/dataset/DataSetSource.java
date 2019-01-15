package br.com.triadworks.dbunit.dataset;

import java.io.IOException;
import java.io.InputStream;

public interface DataSetSource {

	/**
	 * Returns an input stream of a dataset.
	 * 
	 * @return returns an {@code InputStream} for this dataset
	 * @throws IOException
	 *             if unable to find the dataset
	 */
	public InputStream getInputStream() throws IOException;

}
