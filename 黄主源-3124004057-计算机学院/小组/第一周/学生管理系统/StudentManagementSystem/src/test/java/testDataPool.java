import DataBasePool.ConnectionPool;
import DataBasePool.ConnectionPoolConfig;

import java.sql.Connection;

public class testDataPool {
    public static void main(String[] args) throws Exception {
        ConnectionPool.InitConnectionPool();
        Connection connection = ConnectionPool.GetConnection();
    }
}
