package br.com.triadworks.dbunit.connection;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;

public class DefaultDbUnitConnectionCreator implements DbUnitConnectionCreator {

	private final DataSource dataSource;

	public DefaultDbUnitConnectionCreator(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public IDatabaseConnection create() {
		try {
			IDatabaseConnection dbUnitConn = new DatabaseConnection(this.getConnection());
			return dbUnitConn;
		} catch (DatabaseUnitException e) {
			throw new IllegalStateException(
					"It's not possible to create a DbUnit connection: "
							+ e.getMessage(), e);
		}
	}

	@Override
	public void close(IDatabaseConnection dbUnitConnection) {
		try {
			dbUnitConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected Connection getConnection() {
		Connection conn;
		try {
			conn = dataSource.getConnection();
		} catch (Exception e) {
			throw new IllegalStateException(
					"It's not possible to get a Jdbc connection: "
							+ e.getMessage(), e);
		}
		return conn;
	}

}
