package br.com.triadworks.dbunit.vendors.mysql;

import java.sql.Connection;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.mysql.MySqlConnection;

import br.com.triadworks.dbunit.connection.DefaultDbUnitConnectionCreator;
import br.com.triadworks.dbunit.util.JdbcMetaDataExtractor;

public class MySqlDbUnitConnectionCreator extends DefaultDbUnitConnectionCreator {

	public MySqlDbUnitConnectionCreator(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public IDatabaseConnection create() {
		
		Connection jdbcConnection = this.getConnection();
		
		JdbcMetaDataExtractor extractor = new JdbcMetaDataExtractor(jdbcConnection);
		String userName = extractor.getUserName();
		
		try {
			IDatabaseConnection dbUnitConn = new MySqlConnection(jdbcConnection, userName);
			return dbUnitConn;
		} catch (DatabaseUnitException e) {
			throw new IllegalStateException(
					"It's not possible to create a MySql DbUnit connection: "
							+ e.getMessage(), e);
		}
	}
}
