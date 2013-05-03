package br.com.triadworks.dbunit.dataset;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileSystemDataSetSource implements DataSetSource {

	private final File file;
	
	public FileSystemDataSetSource(String path) {
		this.file = new File(path);
	}
	
	public FileSystemDataSetSource(File file) {
		this.file = file;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new FileInputStream(this.file);
	}

}
