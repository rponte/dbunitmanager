package br.com.triadworks.dbunit;

public interface DbUnitManager {

	/**
	 * Atualiza o banco com os dados do arquivo xml, porém não altera os
	 * registros anteriormente inseridos no banco e que não existem no arquivo
	 * xml.
	 */
	public void refresh(String dbUnitXmlPath);

	/**
	 * Deleta todos os dados de cada tabela e em seguida insere os registros encontrados no arquivo xml.
	 */
	public void cleanAndInsert(String dbUnitXmlPath);

	/**
	 * Insere os dados encontrados no arquivo xml.
	 */
	public void insert(String dbUnitXmlPath);

	/**
	 * Atualiza os registros encontrados no arquivo xml.
	 */
	public void update(String dbUnitXmlPath);

	/**
	 * Deleta os registros encontrados no arquivo xml.
	 */
	public void delete(String dbUnitXmlPath);

	/**
	 * Deleta todos os dados de cada tabela encontrada no arquivo xml.
	 */
	public void deleteAll(String dbUnitXmlPath);
	
	/**
	 * Trunca todas as tabelas encontradas no arquivo xml.
	 */
	public void truncate(String dbUnitXmlPath);

}