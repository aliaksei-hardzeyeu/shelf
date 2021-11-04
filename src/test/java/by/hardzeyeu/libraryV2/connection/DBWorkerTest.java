package by.hardzeyeu.libraryV2.connection;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;


class DBWorkerTest {

    @Test
    public void getConnectionTest_ReturnConnectionIfInnerCredentialsTrue_ConnectionNotNull() {
        DBWorker dbWorker = new DBWorker();
        Connection connection = dbWorker.getConnection();
        Assertions.assertNotNull(connection);
    }
}