DbUnitManager
=============

It's a simple wrapper for [DbUnit](http://www.dbunit.org/) to make developer's life easier when writing integration tests.

Using it
--------

1. Configure your pom.xml file to find DbUnitManager:
```xml
<repositories>
	<repository>
    	<id>sonatype-oss-public</id>
        <url>https://oss.sonatype.org/content/groups/public/</url>
    	<releases>
    		<enabled>true</enabled>
    	</releases>
    	<snapshots>
    		<enabled>true</enabled>
		</snapshots>
	</repository>		
</repositories>  

<dependency>
	<groupId>br.com.triadworks</groupId>
  	<artifactId>dbunitmanager</artifactId>
  	<version>1.0-SNAPSHOT</version>
</dependency>
```

2. Use it in your integration testcase java class
```java
public class ProductsDaoTest {

	private DbUnitManager dbUnitManager;
	private DataSource dataSource = // creates your DataSource here!
	
	@Before
	public void setup() {
		dbUnitManager = new MySqlDbUnitManagerImpl(dataSource);
		dbUnitManager.cleanAndInsert(new ClassEntryDataSetSource(ProductsDaoTest.class));
	}
	
	@Test
	public void findingAllProducts() {
		
		ProductsDao dao = new ProductsDaoImpl(dataSource);
		
		List<Product> products = dao.all();
		assertEquals("total of products", 7, products.size());
	}
	
}
```

DataSetSource strategies
------------------------

DbUnit uses a dataset (basically a XML file) to know what are the tables it should work with. DbUnitManager folllows the same idea, however it uses a simple abstraction called **DataSetSource**. DataSetSource is an interface which will help developers to find datasets in theirs projects, may be them located in classpath, file system or elsewhere they want to.

So far DbUnitManager comes with three DataSetSource implementations:

* ClassPathDataSetSource
	- The ClassPathDataSetSource represents a dataset which should be obtained from the classpath.
```java
dbUnitManager.cleanAndInsert(new ClassPathDataSetSource("br/com/triadworks/xmls/Products.xml"));
```

* FileSystemDataSetSource
	- The FileSystemDataSetSource represents a dataset which should be obtained from the file system.
```java
dbUnitManager.cleanAndInsert(new FileSystemDataSetSource("/home/rponte/Products.xml"));
```

* ClassEntryDataSetSource
	- The ClassEntryDataSetSource represents a dataset which should be obtained from the classpath but offering you a simple Convention-Over-Configuration (CoC). The convention simply says the dataset must be in the same package of the class argument passed for the ClassEntryDataSetSource.
```java
dbUnitManager.cleanAndInsert(new ClassEntryDataSetSource(ProductsDaoTest.class));
```

In my humble opinion, the best and organized way to work with DbUnit (and DbUnitManager as well) is creating a single dataset for each testcase. This way it's possible to keep each testcase independent and isolated from each other. So using ClassEntryDataSetSource will give you and your team a way to work following a simple convention.





