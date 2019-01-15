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
import br.com.triadworks.dbunit.dataset.DataSetSource;
import br.com.triadworks.dbunit.resolver.DbUnitDataSetResolver;

public class DefaultDbUnitManagerImpl implements DbUnitManager {

	private final DbUnitConnectionCreator connectionCreator;
	private final DbUnitDataSetResolver dataSetResolver;
	
	public DefaultDbUnitManagerImpl(DbUnitConnectionCreator connectionCreator, DbUnitDataSetResolver dataSetResolver) {
		this.connectionCreator = connectionCreator;
		this.dataSetResolver = dataSetResolver;
	}
	
	@Override
	public void refresh(DataSetSource dataSetSource) {
		execute(REFRESH, dataSetSource);
	}

	@Override
	public void cleanAndInsert(DataSetSource dataSetSource) {
		execute(CLEAN_INSERT, dataSetSource);
	}

	@Override
	public void insert(DataSetSource dataSetSource) {
		execute(INSERT, dataSetSource);
	}

	@Override
	public void update(DataSetSource dataSetSource) {
		execute(UPDATE, dataSetSource);
	}

	@Override
	public void delete(DataSetSource dataSetSource) {
		execute(DELETE, dataSetSource);
	}
	
	@Override
	public void deleteAll(DataSetSource dataSetSource) {
		execute(DELETE_ALL, dataSetSource);
	}
	
	@Override
	public void truncate(DataSetSource dataSetSource) {
		execute(TRUNCATE_TABLE, dataSetSource);
	}
	
	/**
	 * Executes DbUnit <code>operation</code> on dataset
	 * <code>dataSetSource</code>.
	 *
	 * @param operation
	 *            the DBUnit operation
	 * @param dataSetSource
	 *            the dataset to be used by this operation
	 */
	protected void execute(DatabaseOperation operation, DataSetSource dataSetSource) {
		IDatabaseConnection dbconn = null;
		try {
			dbconn = connectionCreator.create();
			operation.execute(dbconn, findDataSet(dataSetSource));
		} catch (Exception e) {
			throw new IllegalStateException(
					"It's not possible to execute DbUnit operation: " 
							+ e.getMessage(), e);
		} finally {
			connectionCreator.close(dbconn);
		}
	}
	
	protected IDataSet findDataSet(DataSetSource dataSetSource) {
		return dataSetResolver.resolve(dataSetSource);
	}

}
