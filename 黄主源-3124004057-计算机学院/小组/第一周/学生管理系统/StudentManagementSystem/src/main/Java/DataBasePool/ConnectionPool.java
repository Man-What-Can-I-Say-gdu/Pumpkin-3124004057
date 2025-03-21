package DataBasePool;

import jdk.internal.foreign.abi.Binding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;


public class ConnectionPool {
    //正在使用的Connection对象池，使用Vector集合保证线程安全（起初用过ArrayList数组，但编写到一般查资料才发现Arraylist的没有同步机制，
    private static Vector<Connection> UsingConnPool = new Vector<>();
    //空闲的Connection对象池
    private static Vector<Connection> FreeConnPool = new Vector<>();
    
    
    
    //初始化连接:返回一个包含着初始化数量个数的连接池数组
    public static void InitConnectionPool() throws Exception {
        //通过配置类获取配置配置信息
        ConnectionPoolConfig config = ConnectionPoolConfig.GetConfig();
        //获取URL用于连接驱动
        Class.forName(config.getDriver());
        //循环获取Connection对象，数量为initSize个,并封装到ArrayList中
        for (int i = 0; i < Integer.parseInt(config.getInitSize()); i++) {
            Connection conn = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
            //进行封装,得到空闲池
            FreeConnPool.add(conn);
        }
    }
    //从连接池中获取Connection对象
    //判断连接池中是否还有空闲对象，如果没有就再判断是否可以新建
    public static Connection GetConnection() throws Exception {
        //判断连接池中是否存在空闲的Connection对象
        if(FreeConnPool.isEmpty()){
            //空闲池没有可以使用的对象
            //判断连接的数量是否达到上限
            if(FreeConnPool.size() + UsingConnPool.size() < Integer.parseInt(ConnectionPoolConfig.getPoolConfig().getMaxSize())){
                //未达到上限：创建新的连接
                NewConn();
                //获取Free池中的新连接并返回
                return MoveConnToUsing();
            }else{
                return MoveConnToUsing();
            }
        }else{
            //存在空闲的连接
            //利用栈的存储方式进行存储，从后往前取保证索引的正确
            //获取Connection元素
            return MoveConnToUsing();
        }
    }
    //实现获取新的连接
    private static void NewConn() throws Exception {
        Class.forName(ConnectionPoolConfig.getPoolConfig().getDriver());
        Connection conn = DriverManager.getConnection(ConnectionPoolConfig.getPoolConfig().getUrl());
        //加载到Free池中
        FreeConnPool.add(conn);
    }
    //实现连接对象从Free池转到Using池
    private static Connection MoveConnToUsing() {
        //获得末尾的连接池对象
        Connection conn = FreeConnPool.get(FreeConnPool.size() - 1);
        //将得到的conn转移到UsingConnPool
        FreeConnPool.remove(FreeConnPool.size() - 1);
        UsingConnPool.add((Connection) conn);
        System.out.println("获取成功");
        return conn;
    }
    //实现连接对象从Using池转到Free池
    private static Connection MoveConnToFree() {
        Connection conn = UsingConnPool.get(FreeConnPool.size() - 1);
        //将得到的conn转移到UsingConnPool
        UsingConnPool.remove((Connection) conn);
        FreeConnPool.add((Connection) conn);
        System.out.println("释放成功");
        return conn;
    }
    //定时检查器，用来判断数据库运行是否超时,如果超时则回收连接
    private static void TimeCheck() throws Exception {
        //定义一个Timer，与其他线程异步进行等待
        //先判断是否开启监视
        if(Boolean.valueOf(ConnectionPoolConfig.getPoolConfig().isHealth())){
            //判断为真，开启检查
            Timer timer = new Timer();
            
        }
    }
}
