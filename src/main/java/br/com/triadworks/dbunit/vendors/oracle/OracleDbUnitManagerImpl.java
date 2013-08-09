package br.com.triadworks.dbunit.vendors.oracle;

import javax.sql.DataSource;

import br.com.triadworks.dbunit.DefaultDbUnitManagerImpl;
import br.com.triadworks.dbunit.connection.CachedDbUnitConnectionCreator;
import br.com.triadworks.dbunit.connection.DbUnitConnectionCreator;
import br.com.triadworks.dbunit.resolver.DbUnitDataSetResolver;
import br.com.triadworks.dbunit.resolver.DefaultDbUnitDataSetResolver;

public class OracleDbUnitManagerImpl extends DefaultDbUnitManagerImpl {

	public OracleDbUnitManagerImpl(DataSource dataSource) {
		this(new CachedDbUnitConnectionCreator(
				new OracleDbUnitConnectionCreator(dataSource)),
				new DefaultDbUnitDataSetResolver());
	}
	
	public OracleDbUnitManagerImpl(DbUnitConnectionCreator connectionCreator, DbUnitDataSetResolver dataSetResolver) {
		super(connectionCreator, dataSetResolver);
	}

}
