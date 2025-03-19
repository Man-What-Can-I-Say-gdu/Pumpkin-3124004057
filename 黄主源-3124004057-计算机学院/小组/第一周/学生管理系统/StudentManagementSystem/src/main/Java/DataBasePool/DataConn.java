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
