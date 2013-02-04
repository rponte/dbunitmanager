package br.com.triadworks.dbunit.vendors.mssqlserver;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.mssql.MsSqlConnection;

import br.com.triadworks.dbunit.connection.DefaultDbUnitConnectionCreator;

public class MsSqlServerDbUnitConnectionCreator extends DefaultDbUnitConnectionCreator {

	public MsSqlServerDbUnitConnectionCreator(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public IDatabaseConnection create() {
		try {
			IDatabaseConnection dbUnitConn = new MsSqlConnection(this.getConnection());
			return dbUnitConn;
		} catch (DatabaseUnitException e) {
			throw new IllegalStateException(
					"It's not possible to create a MsSqlServer DbUnit connection: "
							+ e.getMessage(), e);
		}
	}
	
}
