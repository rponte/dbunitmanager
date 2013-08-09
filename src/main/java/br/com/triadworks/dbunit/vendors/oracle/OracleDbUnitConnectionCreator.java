package br.com.triadworks.dbunit.vendors.oracle;

import java.sql.Connection;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.oracle.Oracle10DataTypeFactory;
import org.dbunit.ext.oracle.OracleConnection;
import br.com.triadworks.dbunit.connection.DefaultDbUnitConnectionCreator;
import br.com.triadworks.dbunit.util.JdbcMetaDataExtractor;

public class OracleDbUnitConnectionCreator extends DefaultDbUnitConnectionCreator {

	public OracleDbUnitConnectionCreator(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public IDatabaseConnection create() {
		
		Connection jdbcConnection = this.getConnection();
		
		JdbcMetaDataExtractor extractor = new JdbcMetaDataExtractor(jdbcConnection);
		String userName = extractor.getUserName();
		
		try {
			IDatabaseConnection dbUnitConn = new OracleConnection(jdbcConnection, userName);
			DatabaseConfig config = dbUnitConn.getConfig();
			// oracle 10g
			config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new Oracle10DataTypeFactory());
			// receycle bin (skip oracle 10g recycle bin system tables if enabled)
			config.setProperty(DatabaseConfig.FEATURE_SKIP_ORACLE_RECYCLEBIN_TABLES, Boolean.TRUE);
	
			return dbUnitConn;
		} catch (DatabaseUnitException e) {
			throw new IllegalStateException(
					"It's not possible to create a Oracle DbUnit connection: "
							+ e.getMessage(), e);
		}
	}
	
}
