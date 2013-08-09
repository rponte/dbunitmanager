package br.com.triadworks.dbunit.connection;

import org.dbunit.database.IDatabaseConnection;

public class CachedDbUnitConnectionCreator implements DbUnitConnectionCreator {

	private final DbUnitConnectionCreator delegate;
	private IDatabaseConnection cachedDbUnitConnection;

	public CachedDbUnitConnectionCreator(DbUnitConnectionCreator delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public IDatabaseConnection create() {
		
		if (cachedDbUnitConnection == null)
			cachedDbUnitConnection = delegate.create();
		
		return cachedDbUnitConnection;
	}

	@Override
	public void close(IDatabaseConnection dbUnitConnection) {
		// does not close the connection
	}

}
