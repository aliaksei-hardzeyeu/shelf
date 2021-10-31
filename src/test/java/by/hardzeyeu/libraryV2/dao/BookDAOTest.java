package by.hardzeyeu.libraryV2.dao;

import org.dbunit.DBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.sql.SQLException;

class BookDAOTest extends DBTestCase {
    private static BookDAO bookDAO;

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream("dependents.xml"));
    }

    @BeforeAll
    static void init() throws SQLException {
        //set autocommit off
        bookDAO = new BookDAO();

    }

    @Test
    void getBook() {
    }

    @Test
    void getListOfBooks() {
    }

    @Test
    void addBook() {
    }

    @Test
    void updateBook() {
    }

    @Test
    void removeBook() {
    }


}