package br.com.triadworks.dbunit;

import br.com.triadworks.dbunit.dataset.DataSetSource;

public interface DbUnitManager {

	/**
	 * Refreshes dataset contents into the target database. This means that data
	 * of existing rows are updated and non-existing row get inserted. Any rows
	 * which exist in the database but not in dataset stay unaffected. This
	 * approach is more appropriate for tests that assume other data may exist
	 * in the database. if they are correctly written, tests using this strategy
	 * can even be performed on a populated database like a copy of a production
	 * database.
	 * 
	 * @param dataSetSource
	 *            the dataset to be used by this operation
	 */
	public void refresh(DataSetSource dataSetSource);

	/**
	 * Performs a DELETE_ALL operation followed by an INSERT operation. This is
	 * the safest approach to ensure that the database is in a known state. This
	 * is appropriate for tests that require the database to only contain a
	 * specific set of data.
	 * 
	 * @param dataSetSource
	 *            the dataset to be used by this operation
	 */
	public void cleanAndInsert(DataSetSource dataSetSource);
	
	/**
	 * Inserts the dataset contents into the database. This operation assumes
	 * that table data does not exist in the target database and fails if this
	 * is not the case. To prevent problems with foreign keys, tables must be
	 * sequenced appropriately in the dataset.
	 * 
	 * @param dataSetSource
	 *            the dataset to be used by this operation
	 */
	public void insert(DataSetSource dataSetSource);
	
	/**
	 * Updates the database from the dataset contents. This operation assumes
	 * that table data already exists in the target database and fails if this
	 * is not the case.
	 * 
	 * @param dataSetSource
	 *            the dataset to be used by this operation
	 */
	public void update(DataSetSource dataSetSource);

	/**
	 * Deletes only the dataset contents from the database. This operation does
	 * not delete the entire table contents but only data that are present in
	 * the dataset.
	 * 
	 * @param dataSetSource
	 *            the dataset to be used by this operation
	 */
	public void delete(DataSetSource dataSetSource);

	/**
	 * Deletes all rows of tables present in the specified dataset. If the
	 * dataset does not contains a particular table, but that table exists in
	 * the database, the database table is not affected. Table are truncated in
	 * reverse sequence.
	 * 
	 * @param dataSetSource
	 *            the dataset to be used by this operation
	 */
	public void deleteAll(DataSetSource dataSetSource);
	
	/**
	 * Truncates tables present in the specified dataset. If the dataset does not
	 * contains a particular table, but that table exists in the database, the
	 * database table is not affected. Table are truncated in reverse sequence.
	 * 
	 * @param dataSetSource
	 *            the dataset to be used by this operation
	 */
	public void truncate(DataSetSource dataSetSource);

}