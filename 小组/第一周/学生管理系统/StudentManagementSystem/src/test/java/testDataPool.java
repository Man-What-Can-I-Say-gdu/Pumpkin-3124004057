<<<<<<< HEAD:小组/第一周/学生管理系统/StudentManagementSystem/src/test/java/testDataPool.java
import DataBasePool.ConnectionPool;
import DataBasePool.ConnectionPoolConfig;

import java.sql.Connection;

public class testDataPool {
    public static void main(String[] args) throws Exception {
    }
}
=======
import DataBasePool.ConnectionPool;
import DataBasePool.ConnectionPoolConfig;

import java.sql.Connection;

public class testDataPool {
    public static void main(String[] args) throws Exception {
        ConnectionPool.InitConnectionPool();
        Connection connection = ConnectionPool.GetConnection();
    }
}
>>>>>>> 2d83697314848c3aee51c5225472089c07daba39:黄主源-3124004057-计算机学院/小组/第一周/学生管理系统/StudentManagementSystem/src/test/java/testDataPool.java
