package br.com.triadworks.dbunit.vendors.postgresql;

import javax.sql.DataSource;

import br.com.triadworks.dbunit.DefaultDbUnitManagerImpl;
import br.com.triadworks.dbunit.connection.CachedDbUnitConnectionCreator;
import br.com.triadworks.dbunit.connection.DbUnitConnectionCreator;
import br.com.triadworks.dbunit.resolver.DbUnitDataSetResolver;
import br.com.triadworks.dbunit.resolver.DefaultDbUnitDataSetResolver;

public class PostgreSqlDbUnitManagerImpl extends DefaultDbUnitManagerImpl {

	public PostgreSqlDbUnitManagerImpl(DataSource dataSource) {
		this(new CachedDbUnitConnectionCreator(
				new PostgreSqlDbUnitConnectionCreator(dataSource)),
				new DefaultDbUnitDataSetResolver());
	}
	
	public PostgreSqlDbUnitManagerImpl(DbUnitConnectionCreator connectionCreator, DbUnitDataSetResolver dataSetResolver) {
		super(connectionCreator, dataSetResolver);
	}

}
