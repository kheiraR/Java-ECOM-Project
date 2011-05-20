/*
 *  The MIT License
 * 
 *  Copyright 2011 Michaël Schwartz <m.schwartz@epokmedia.fr>.
 * 
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 * 
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 * 
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package miage.ecom.test;

import java.sql.DriverManager;
import java.sql.SQLException;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.dialect.MySQL5Dialect;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;

/**
 *
 * @author Schwartz Michaël <m.schwartz@epokmedia.fr>
 */
public abstract class EcomBaseTest {

	private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/ecom?user=root&password=";

	
	public static JavaArchive getTestArchive(Class testClass) {

		return ShrinkWrap.create(JavaArchive.class)
				.addPackages(true,"miage/ecom")
				.addAsResource(testClass.getPackage(), testClass.getSimpleName() + ".xml")
				.addPackage("org/dbunit")
				.addPackages(true,"us/monoid")
				.addPackages(false, "org/dbunit","org/dbunit/ant","org/dbunit/assertion","org/dbunit/operation","org/dbunit/ext/mysql")
				.addPackages(true,"org/dbunit/util", "org/dbunit/dataset","org/dbunit/database")
				.addAsManifestResource("persistence.xml", "persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));

	}

	@Before
	public void setUp() throws Exception {
		final IDatabaseConnection conn = getConnection();
		final IDataSet data = getDataSet();
		try {
			System.out.println("Cleaning database...");
			DatabaseOperation.CLEAN_INSERT.execute(conn, data);
		} finally {
			conn.close();
		}
	}

	private IDatabaseConnection getConnection()
			throws ClassNotFoundException, SQLException, DatabaseUnitException {

		DatabaseConnection connection = new DatabaseConnection(DriverManager.getConnection(CONNECTION_STRING));
		connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());

		return connection;
	}

	private IDataSet getDataSet() throws Exception {
		
		return new FlatXmlDataSetBuilder().setColumnSensing(true).build(getClass().getResourceAsStream(
				getClass().getSimpleName() + ".xml"));
	}


}
