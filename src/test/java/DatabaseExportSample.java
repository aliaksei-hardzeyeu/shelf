import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.database.search.TablesDependencyHelper;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseExportSample {
    public static void main(String[] args) throws Exception {
        // database connection -> doesn`t work with project`s c3p0 data source
        Class driverClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Connection jdbcConnection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library_v2", "root", "root");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);


        // partial database export
        QueryDataSet partialDataSet = new QueryDataSet(connection);
        partialDataSet.addTable("books", "SELECT * FROM books");
        partialDataSet.addTable("borrows", "SELECT * FROM borrows");
        FlatXmlDataSet.write(partialDataSet, new FileOutputStream("partial.xml"));

        // full database export
//        IDataSet fullDataSet = connection.createDataSet();
//        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("full.xml"));

        // dependent tables database export: export table X and all tables that
        // have a PK which is a FK on X, in the right order for insertion
        String[] depTableNames = TablesDependencyHelper.getAllDependentTables(connection, "books");
        IDataSet depDataSet = connection.createDataSet(depTableNames);
        FlatXmlDataSet.write(depDataSet, new FileOutputStream("dependents.xml"));
    }
}
