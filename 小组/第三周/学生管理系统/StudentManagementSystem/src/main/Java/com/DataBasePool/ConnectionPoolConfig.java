package com.DataBasePool;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;
/*
*
*
*
*
*
*
*
*
 */
public class ConnectionPoolConfig {
    //需要配置的信息
    private String driver;
    private String url;
    private String username;
    private String password;
    private String maxSize;
    private String initSize;
    private String health;
    private String delay;

    private String period;

    private String timeout;

    private String waittime;


    //使用单例实现获取一个配置信息的对象
    private static ConnectionPoolConfig PoolConfig;


    //三种模式：通过sychro...修饰符上锁，使用双重检查上锁机制，枚举内部类获取枚举常量
    //私有空参构造，防止外界new对象并进行修改
    private ConnectionPoolConfig() throws Exception {
        //利用反射读取配置文件信息
        //获取properties对象
        Properties prop = new Properties();
        //加载配置文件
        ClassLoader classLoader = ConnectionPoolConfig.class.getClassLoader();
        //定义输入流(maven中只有这种模式（？）)
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
        prop.load(inputStream);
        //获取配置文件中定义的数据源
        for (Object o : prop.keySet()) {
            //剪切字符串得到变量名，此时配置文件属性名与配置类变量名还有首字母的大小写区别，因此要编写一个方法能够将首字母转为大写
            //NoSuchField
            String fieldName = o.toString().split("\\.")[1];
            //获取Field对象
            Field field = this.getClass().getDeclaredField(fieldName);
            //获取field对象的构造器
            Method ConstructorMethod = this.getClass().getMethod(UpVariesName(fieldName),field.getType());
            //调用构造器
            //argument type mismatch
            ConstructorMethod.invoke(this,prop.get(o));
        }
    }
    //获取配置类对象，使用单例模式，防止更改配置导致连接池出现异常
    public static ConnectionPoolConfig GetConfig() throws Exception {
        //判断是否当前配置类对象是否为空，非空则返回单例，为空则调用构造函数
        if(PoolConfig == null){
            PoolConfig = new ConnectionPoolConfig();
        }
        return PoolConfig;
    }
    /*
     *利用反射读取配置文件信息
    */
    private String UpVariesName(String FieldName) {
        char[] chars = FieldName.toCharArray();
        //将首字符中存放的ascll码值减32获得大写字符
        chars[0] -= 32;
        //获得Set方法的名称
        return "set" + new String(chars);
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(String maxSize) {
        this.maxSize = maxSize;
    }

    public String getInitSize() {
        return initSize;
    }

    public void setInitSize(String initSize) {
        this.initSize = initSize;
    }

    public String isHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getWaittime() {
        return waittime;
    }

    public void setWaittime(String waittime) {
        this.waittime = waittime;
    }

    public static ConnectionPoolConfig getPoolConfig() {
        return PoolConfig;
    }

    public static void setPoolConfig(ConnectionPoolConfig poolConfig) {
        PoolConfig = poolConfig;
    }

    @Override
    public String toString() {
        return "ConnectionPoolConfig{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", maxSize=" + maxSize +
                ", initSize=" + initSize +
                ", dealth=" + health +
                ", delay=" + delay +
                ", period=" + period +
                ", timeout=" + timeout +
                ", waittime=" + waittime +
                '}';
    }
}

