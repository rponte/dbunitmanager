package br.com.triadworks.dbunit.connection;

import org.dbunit.database.IDatabaseConnection;

public interface DbUnitConnectionCreator {

	public IDatabaseConnection create();
	
	public void close(IDatabaseConnection dbUnitConnection);
	
}
