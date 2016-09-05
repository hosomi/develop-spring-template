package jp.template.basic.data;

import java.io.File;
import java.io.FileOutputStream;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.rules.ExternalResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SprintSecurity 用のテストデータ投入。
 * 
 * @author hosomi.
 */
@Component
public class TestDataResource extends ExternalResource {

	@Autowired
	private DataSource dataSource;

	private File backupFile;

	@Override
	protected void before() throws Throwable {

		IDatabaseConnection conn = null;
		DatabaseConfig config = null;
		try {
			conn = new DatabaseConnection(dataSource.getConnection());
			config = conn.getConfig();
			config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
			
			// バックアップを取得する(H2 のインメモリなので不要かも)
			QueryDataSet partialDataSet = new QueryDataSet(conn);
			partialDataSet.addTable("user");
			backupFile = File.createTempFile("world_backup", "xml");
			FlatXmlDataSet.write(partialDataSet, new FileOutputStream(backupFile));

			System.out.println(backupFile.getAbsolutePath());

			// テストデータを入れる
			IDataSet dataset = new CsvDataSet(new File("src/test/resources/data"));
			DatabaseOperation.CLEAN_INSERT.execute(conn, dataset);

		} finally {
			config = null;
			
			if (conn != null) {
				try {
					conn.close();
				} finally {
					conn = null;
				}
			}
		}
	}

	@Override
	protected void after() {
		
		try {
			IDatabaseConnection conn = null;
			DatabaseConfig config = null;
			try {
				conn = new DatabaseConnection(dataSource.getConnection());
				config = conn.getConfig();
				config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
				
				// バックアップからリストアする
				if (backupFile != null) {
					IDataSet dataSet = new FlatXmlDataSetBuilder().build(backupFile);
					DatabaseOperation.CLEAN_INSERT.execute(conn, dataSet);
					backupFile.delete();
					backupFile = null;
				}
			} finally {
				config = null;
				
				if (conn != null) {
					try {
						conn.close();
					} finally {
						conn = null;
					}
				}
			}
		} catch (Exception ignored) {
			
		}
	}

}
