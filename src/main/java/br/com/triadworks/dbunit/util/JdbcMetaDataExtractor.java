package br.com.triadworks.dbunit.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class JdbcMetaDataExtractor {

	private final Connection connection;

	public JdbcMetaDataExtractor(Connection connection) {
		this.connection = connection;
	}

	public String getUserName() {
		try {
			DatabaseMetaData databaseMetaData = connection.getMetaData();
			String userName = databaseMetaData.getUserName();
			return userName;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalStateException(
					"It's not possible to get connection metadata: "
						+ e.getMessage(), e);
		}
	}

}
