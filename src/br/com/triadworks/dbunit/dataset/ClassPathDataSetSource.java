package br.com.triadworks.dbunit.dataset;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassPathDataSetSource implements DataSetSource {

	private final String path;

	public ClassPathDataSetSource(String path) {
		this.path = path;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		InputStream	is = getClassLoader().getResourceAsStream(this.path);
		if (is == null) {
			throw new FileNotFoundException("Class path dataset source cannot be opened because it does not exist.");
		}
		return is;
	}

	private ClassLoader getClassLoader() {
		ClassLoader cl = null;
		try {
			cl = Thread.currentThread().getContextClassLoader();
		} catch (Throwable ex) {
			// Cannot access thread context ClassLoader - falling back to system class loader...
		}
		if (cl == null) {
			// No thread context class loader -> use class loader of this class.
			cl = ClassPathDataSetSource.class.getClassLoader();
		}
		return cl;
	}

}
