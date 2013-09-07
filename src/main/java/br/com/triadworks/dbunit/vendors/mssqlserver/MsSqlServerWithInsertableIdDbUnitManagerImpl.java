package br.com.triadworks.dbunit.vendors.mssqlserver;

import javax.sql.DataSource;

import org.dbunit.ext.mssql.InsertIdentityOperation;

import br.com.triadworks.dbunit.connection.CachedDbUnitConnectionCreator;
import br.com.triadworks.dbunit.connection.DbUnitConnectionCreator;
import br.com.triadworks.dbunit.dataset.DataSetSource;
import br.com.triadworks.dbunit.resolver.DbUnitDataSetResolver;
import br.com.triadworks.dbunit.resolver.DefaultDbUnitDataSetResolver;

public class MsSqlServerWithInsertableIdDbUnitManagerImpl extends MsSqlServerDbUnitManagerImpl {

	public MsSqlServerWithInsertableIdDbUnitManagerImpl(DataSource dataSource) {
		this(new CachedDbUnitConnectionCreator(
				new MsSqlServerDbUnitConnectionCreator(dataSource)),
				new DefaultDbUnitDataSetResolver());
	}
	
	public MsSqlServerWithInsertableIdDbUnitManagerImpl(DbUnitConnectionCreator connectionCreator, DbUnitDataSetResolver dataSetResolver) {
		super(connectionCreator, dataSetResolver);
	}

	@Override
	public void cleanAndInsert(DataSetSource dataSetSource) {
		execute(InsertIdentityOperation.CLEAN_INSERT, dataSetSource);
	}
	
	@Override
	public void insert(DataSetSource dataSetSource) {
		execute(InsertIdentityOperation.INSERT, dataSetSource);
	}
	
	@Override
	public void refresh(DataSetSource dataSetSource) {
		execute(InsertIdentityOperation.REFRESH, dataSetSource);
	}
	
}
