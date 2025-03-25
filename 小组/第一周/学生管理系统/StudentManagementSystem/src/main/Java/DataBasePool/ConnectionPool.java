package DataBasePool;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;


public class ConnectionPool {
    //正在使用的Connection对象池，使用Vector集合保证线程安全（起初用过ArrayList数组，但编写到一般查资料才发现Arraylist的没有同步机制
    //使用DataConn得到使用的开始时间，通过当前时间与开始时间相减能得到运行时间，用于判断是否使用超时
    private final static Vector<DataConn> UsingConnPool = new Vector<>();
    //空闲的Connection对象池
    private final static Vector<Connection> FreeConnPool = new Vector<>();

    //获取配置信息
    protected static final ConnectionPoolConfig config;
    static {
        try {
            config = ConnectionPoolConfig.GetConfig();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //初始化连接:返回一个包含着初始化数量个数的连接池数组
    public static void InitConnectionPool() throws Exception {
        //通过配置类获取配置配置信息
        //获取URL用于连接驱动
        Class.forName(config.getDriver());
        //循环获取Connection对象，数量为initSize个,并封装到ArrayList中
        for (int i = 0; i < Integer.parseInt(config.getInitSize()); i++) {
            Connection conn = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
            //进行封装,得到空闲池
            FreeConnPool.add(conn);
            TimeCheck();
        }
    }
    //从连接池中获取Connection对象
    //判断连接池中是否还有空闲对象，如果没有就再判断是否可以新建
    public static synchronized  Connection GetConnection() throws Exception {
        //判断连接池中是否存在空闲的Connection对象
        if(FreeConnPool.isEmpty()){
            //空闲池没有可以使用的对象
            //判断连接的数量是否达到上限
            if(UsingConnPool.size() < Integer.parseInt(config.getMaxSize())){
                //未达到上限：创建新的连接
                NewConn();
                //获取Free池中的新连接并返回
                return MoveConnToUsing();
            }else{
                //达到上限，等待释放
                System.out.println("获取失败！正在等待重新获取");
                //从配置类中获取等待时间并进行等待
                try {
                    Object WaitCarrige = new Object();
                    synchronized (WaitCarrige){
                        WaitCarrige.wait(Long.parseLong(config.getWaittime()));
                    }
                    //递归实现重复获取
                    GetConnection();
                }catch (InterruptedException | IllegalStateException e){
                    e.printStackTrace();
                }
            }
        }else{
            //存在空闲的连接
            //利用栈的存储方式进行存储，从后往前取保证索引的正确
            //获取Connection元素
            return MoveConnToUsing();
        }
        System.out.println("获取失败！");
        return null;
    }
    //实现创建新的连接
    private static void NewConn() throws Exception {
        Connection conn = DriverManager.getConnection(config.getUrl(),config.getUsername(),config.getPassword());
        //加载到Free池中
        FreeConnPool.add(conn);
    }
    //实现连接对象从Free池转到Using池
    private static Connection MoveConnToUsing() {
        //获得末尾的连接池对象
        Connection conn = FreeConnPool.get(FreeConnPool.size() - 1);
        //将得到的conn转移到UsingConnPool
        FreeConnPool.remove(FreeConnPool.size() - 1);
        UsingConnPool.add(new DataConn(conn, System.currentTimeMillis()));
        System.out.println("获取成功");
        return conn;
    }
    //实现连接对象从Using池转到Free池
    public static void RecycleConnection(Connection conn) throws Exception {
        Connection TempConn;
        //判断Conn是否已经关闭
        if(!conn.isClosed()){
            //将得到的conn转移到UsingConnPool
            for (int i = 0; i < UsingConnPool.size(); i++) {
                //对比conn中的connection对象找到索引
                TempConn = UsingConnPool.get(i).getConn();
                if (TempConn.equals(conn)){
                    //遇到相同则将将对应Connection对象进行remove并添加到Free池中
                    UsingConnPool.remove(i);
                    FreeConnPool.add(TempConn);
                }
            }
            System.out.println("回收失败！");
        }
    }
    //定时检查器，用来判断数据库运行是否超时,如果超时则回收连接
    private static void TimeCheck() {
        //定义一个Timer，与其他线程异步进行等待
        //先判断是否开启监视
        if(Boolean.parseBoolean(config.isHealth())){
            //判断为真，开启检查
            //设置定时器
            System.out.println("定时器启用中");
            Timer timer = new Timer();
            //开启定时器任务，每个WaitTime检查一次连接是否释放，启动时机应该是获得连接以后
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    //遍历Using池查看是否存在线程占用
                    for (int i = 0; i < UsingConnPool.size(); i++) {
                        //获取开始时间和当前时间并做差判断是否超时
                        if(System.currentTimeMillis() - UsingConnPool.get(i).getStartTime() >= Long.parseLong(config.getTimeout())){
                            //强制释放该连接
                            try {
                                release(i);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                }
            }, Long.parseLong(ConnectionPoolConfig.getPoolConfig().getPeriod()));
        }
    }
    //release强制回收连接
    private static void release(int i) throws SQLException {
        Connection conn = UsingConnPool.get(i).getConn();
        UsingConnPool.remove(i);
        conn.close();
    }
}

