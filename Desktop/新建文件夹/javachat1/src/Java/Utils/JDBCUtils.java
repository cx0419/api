package Java.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static String driver="";
    private static String url="";
    private static String user="";
    private static String password="";
    /*
     * 文件的读取，只需要读取一次即可获取这些值，因此使用静态代码块
     */
    static {

        try {
            //读取资源文件，获取值
            //1.Properties集合类
            Properties properties=new Properties();
            //2.加载文件
            //获取src路径下的文件的方式--->ClassLoader类加载器
            ClassLoader classLoader= JDBCUtils.class.getClassLoader();
            String path=classLoader.getResource("jdbc.properties").getFile();
            path=java.net.URLDecoder.decode(path,"utf-8");
            properties.load(new FileReader(path));
            //3.获取数据，赋值
            url=properties.getProperty("url");
            user=properties.getProperty("user");
            password=properties.getProperty("password");
            driver=properties.getProperty("driver");
            //4.注册驱动
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getconnection() throws SQLException {//获取数据库连接对象 Connection
        return DriverManager.getConnection(url,user,password);
    }
    /**
     *重载方式实现close方法：
     *1.当执行SQL的增、删、改语句时，使用void close(Statement ,Connection)来关闭资源
     *2.当执行SQL的查询语句时，使用void close(ResultSet,Statement ,Connection)来关闭资源
     */
    public static void close(Statement statement,Connection connection){//释放资源
        if (statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(ResultSet resultSet,Statement statement,Connection connection){//释放资源
        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
