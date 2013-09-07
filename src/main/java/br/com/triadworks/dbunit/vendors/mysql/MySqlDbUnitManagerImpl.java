package br.com.triadworks.dbunit.vendors.mysql;

import javax.sql.DataSource;

import br.com.triadworks.dbunit.DefaultDbUnitManagerImpl;
import br.com.triadworks.dbunit.connection.CachedDbUnitConnectionCreator;
import br.com.triadworks.dbunit.connection.DbUnitConnectionCreator;
import br.com.triadworks.dbunit.resolver.DbUnitDataSetResolver;
import br.com.triadworks.dbunit.resolver.DefaultDbUnitDataSetResolver;

public class MySqlDbUnitManagerImpl extends DefaultDbUnitManagerImpl {

	public MySqlDbUnitManagerImpl(DataSource dataSource) {
		this(new CachedDbUnitConnectionCreator(
				new MySqlDbUnitConnectionCreator(dataSource)),
				new DefaultDbUnitDataSetResolver());
	}
	
	public MySqlDbUnitManagerImpl(DbUnitConnectionCreator connectionCreator, DbUnitDataSetResolver dataSetResolver) {
		super(connectionCreator, dataSetResolver);
	}

}
