package br.com.triadworks.dbunit.dataset;

import java.io.IOException;
import java.io.InputStream;

public class ClassEntryDataSetSource implements DataSetSource {

	private final Class<?> clazz;
	
	public ClassEntryDataSetSource(Class<?> clazz) {
		this.clazz = clazz;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		
		String packagePath = classPackageAsResourcePath();
		String xmlName = String.format("%s/%s.xml", packagePath, clazz.getSimpleName());
		
		return clazz.getResourceAsStream(xmlName);
	}
	
	private String classPackageAsResourcePath() {
		String className = clazz.getName();
		int packageEndIndex = className.lastIndexOf('.');
		if (packageEndIndex == -1) {
			return "";
		}
		String packageName = className.substring(0, packageEndIndex);
		return packageName.replace('.', '/');
	}

}
