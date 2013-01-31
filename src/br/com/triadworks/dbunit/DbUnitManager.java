package br.com.triadworks.dbunit;

public interface DbUnitManager {

	/**
	 * Refreshes dataset contents into the target database. This means that data
	 * of existing rows are updated and non-existing row get inserted. Any rows
	 * which exist in the database but not in dataset stay unaffected. This
	 * approach is more appropriate for tests that assume other data may exist
	 * in the database. if they are correctly written, tests using this strategy
	 * can even be performed on a populated database like a copy of a production
	 * database.
	 */
	public void refresh(String dbUnitXmlPath);

	/**
	 * Performs a DELETE_ALL operation followed by an INSERT operation. This is
	 * the safest approach to ensure that the database is in a known state. This
	 * is appropriate for tests that require the database to only contain a
	 * specific set of data.
	 */
	public void cleanAndInsert(String dbUnitXmlPath);
	
	/**
	 * Inserts the dataset contents into the database. This operation assumes
	 * that table data does not exist in the target database and fails if this
	 * is not the case. To prevent problems with foreign keys, tables must be
	 * sequenced appropriately in the dataset.
	 */
	public void insert(String dbUnitXmlPath);
	
	/**
	 * Updates the database from the dataset contents. This operation assumes
	 * that table data already exists in the target database and fails if this
	 * is not the case.
	 */
	public void update(String dbUnitXmlPath);

	/**
	 * Deletes only the dataset contents from the database. This operation does
	 * not delete the entire table contents but only data that are present in
	 * the dataset.
	 */
	public void delete(String dbUnitXmlPath);

	/**
	 * Deletes all rows of tables present in the specified dataset. If the
	 * dataset does not contains a particular table, but that table exists in
	 * the database, the database table is not affected. Table are truncated in
	 * reverse sequence.
	 */
	public void deleteAll(String dbUnitXmlPath);
	
	/**
	 * Truncates tables present in the specified dataset. If the dataset does not
	 * contains a particular table, but that table exists in the database, the
	 * database table is not affected. Table are truncated in reverse sequence.
	 */
	public void truncate(String dbUnitXmlPath);

}