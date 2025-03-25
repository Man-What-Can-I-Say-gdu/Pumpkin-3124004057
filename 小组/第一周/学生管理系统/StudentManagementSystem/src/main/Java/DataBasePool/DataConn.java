<<<<<<< HEAD:小组/第一周/学生管理系统/StudentManagementSystem/src/main/Java/DataBasePool/DataConn.java
package DataBasePool;

import java.sql.Connection;
import java.sql.Time;

public class DataConn {
    /*
    *@author 用于封装连接池的对象和开始使用的时间，只能由ConnectionPool调用
    */
    public Connection conn;
    //受保护的方法，防止使用的时间被更改导致长时间占用连接
    protected Long StartTime;

    public DataConn(Connection conn, Long startTime) {
        this.conn = conn;
        StartTime = startTime;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Long getStartTime() {
        return StartTime;
    }

    public void setStartTime(Long startTime) {
        StartTime = startTime;
    }


}
=======
package DataBasePool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;

public class DataConn {
    /*
    *@author 用于封装连接池的对象和开始使用的时间，只能由ConnectionPool调用
    */
    public Connection conn;
    //受保护的方法，防止使用的时间被更改导致长时间占用连接
    protected Time StartTime;

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Time getStartTime() {
        return StartTime;
    }

    public void setStartTime(Time startTime) {
        StartTime = startTime;
    }


}
>>>>>>> 2d83697314848c3aee51c5225472089c07daba39:黄主源-3124004057-计算机学院/小组/第一周/学生管理系统/StudentManagementSystem/src/main/Java/DataBasePool/DataConn.java
