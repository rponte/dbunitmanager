package br.com.triadworks.dbunit;

import static org.dbunit.operation.DatabaseOperation.CLEAN_INSERT;
import static org.dbunit.operation.DatabaseOperation.DELETE;
import static org.dbunit.operation.DatabaseOperation.DELETE_ALL;
import static org.dbunit.operation.DatabaseOperation.INSERT;
import static org.dbunit.operation.DatabaseOperation.REFRESH;
import static org.dbunit.operation.DatabaseOperation.TRUNCATE_TABLE;
import static org.dbunit.operation.DatabaseOperation.UPDATE;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import br.com.triadworks.dbunit.connection.DbUnitConnectionCreator;
import br.com.triadworks.dbunit.resolver.DbUnitDataSetResolver;

public class DefaultDbUnitManagerImpl implements DbUnitManager {

	private final DbUnitConnectionCreator connectionCreator;
	private final DbUnitDataSetResolver dataSetResolver;
	
	public DefaultDbUnitManagerImpl(DbUnitConnectionCreator connectionCreator, DbUnitDataSetResolver dataSetResolver) {
		this.connectionCreator = connectionCreator;
		this.dataSetResolver = dataSetResolver;
	}
	
	@Override
	public void refresh(String dbUnitXmlPath) {
		execute(REFRESH, dbUnitXmlPath);
	}

	@Override
	public void cleanAndInsert(String dbUnitXmlPath) {
		execute(CLEAN_INSERT, dbUnitXmlPath);
	}

	@Override
	public void insert(String dbUnitXmlPath) {
		execute(INSERT, dbUnitXmlPath);
	}

	@Override
	public void update(String dbUnitXmlPath) {
		execute(UPDATE, dbUnitXmlPath);
	}

	@Override
	public void delete(String dbUnitXmlPath) {
		execute(DELETE, dbUnitXmlPath);
	}
	
	@Override
	public void deleteAll(String dbUnitXmlPath) {
		execute(DELETE_ALL, dbUnitXmlPath);
	}
	
	@Override
	public void truncate(String dbUnitXmlPath) {
		execute(TRUNCATE_TABLE, dbUnitXmlPath);
	}
	
	/**
	 * Executes DbUnit <code>operation</code> on dataset
	 * <code>dbUnitXmlPath</code>.
	 */
	protected void execute(DatabaseOperation operation, String dbUnitXmlPath) {
		IDatabaseConnection dbconn = connectionCreator.create();
		try {
			operation.execute(dbconn, findDataSet(dbUnitXmlPath));
		} catch (Exception e) {
			throw new IllegalStateException(
					"It's not possible to execute DbUnit operation: " 
							+ e.getMessage(), e);
		} finally {
			connectionCreator.close(dbconn);
		}
	}
	
	protected IDataSet findDataSet(String dbUnitXmlPath) {
		return dataSetResolver.resolve(dbUnitXmlPath);
	}

}
