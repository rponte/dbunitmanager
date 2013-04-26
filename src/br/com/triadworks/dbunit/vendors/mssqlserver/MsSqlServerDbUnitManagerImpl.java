package br.com.triadworks.dbunit.vendors.mssqlserver;

import javax.sql.DataSource;

import org.dbunit.ext.mssql.InsertIdentityOperation;

import br.com.triadworks.dbunit.DefaultDbUnitManagerImpl;
import br.com.triadworks.dbunit.connection.CachedDbUnitConnectionCreator;
import br.com.triadworks.dbunit.connection.DbUnitConnectionCreator;
import br.com.triadworks.dbunit.resolver.DbUnitDataSetResolver;
import br.com.triadworks.dbunit.resolver.DefaultDbUnitDataSetResolver;

public class MsSqlServerDbUnitManagerImpl extends DefaultDbUnitManagerImpl {

	public MsSqlServerDbUnitManagerImpl(DataSource dataSource) {
		this(new CachedDbUnitConnectionCreator(
				new MsSqlServerDbUnitConnectionCreator(dataSource)),
				new DefaultDbUnitDataSetResolver());
	}
	
	public MsSqlServerDbUnitManagerImpl(DbUnitConnectionCreator connectionCreator, DbUnitDataSetResolver dataSetResolver) {
		super(connectionCreator, dataSetResolver);
	}

	@Override
	public void cleanAndInsert(String dbUnitXmlPath) {
		execute(InsertIdentityOperation.CLEAN_INSERT, dbUnitXmlPath);
	}
	
	@Override
	public void insert(String dbUnitXmlPath) {
		execute(InsertIdentityOperation.INSERT, dbUnitXmlPath);
	}
	
	@Override
	public void refresh(String dbUnitXmlPath) {
		execute(InsertIdentityOperation.REFRESH, dbUnitXmlPath);
	}
	
}
