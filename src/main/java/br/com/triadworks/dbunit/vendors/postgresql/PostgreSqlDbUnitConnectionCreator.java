package br.com.triadworks.dbunit.vendors.postgresql;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;

import br.com.triadworks.dbunit.connection.DefaultDbUnitConnectionCreator;

public class PostgreSqlDbUnitConnectionCreator extends DefaultDbUnitConnectionCreator {

	public PostgreSqlDbUnitConnectionCreator(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public IDatabaseConnection create() {
		try {
			IDatabaseConnection dbUnitConn = new DatabaseConnection(getConnection());
			dbUnitConn.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new PostgresqlDataTypeFactory());
			return dbUnitConn;
		} catch (DatabaseUnitException e) {
			throw new IllegalStateException(
					"It's not possible to create a PostgreSql DbUnit connection: "
							+ e.getMessage(), e);
		}
	}
}
