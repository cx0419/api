package Java.Dao;

import Java.Domain.pojo.User;

import java.sql.*;
import static Java.Utils.JDBCUtils.close;

public class Login {
    public static boolean Check_AccountNumberAndAccountPassword(String AccountNumber, String AccountPassword) throws Exception {
//        普通获取连接方法
//        Class.forName("com.mysql.jdbc.Driver");
//        String url = "jdbc:mysql://localhost:3306/javachat";//uerslist为数据库名
//        String user = "root";
//        String password = "xxxx";
//        Connection con = DriverManager.getConnection(url, user, password);
        //连接池技术
        Connection con = Java.Utils.JDBCUtilsByDruid.getConnection();
        System.out.println("连接成功");
        String sql = "select AccountPassword, AccountNumber from user where AccountNumber=? and AccountPassword=?"; //userlist为表名字
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setObject(1, AccountNumber); // 注意：索引从1开始
        ps.setObject(2, AccountPassword);
        String AccountNumber_ = null;
        String AccountPassword_ = null;
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                AccountNumber_ = rs.getString("AccountNumber");
                AccountPassword_ = rs.getString("AccountPassword");
            }
        }
        ps.close();
        con.close();
        System.out.println("关闭成功");
        if(AccountPassword.equals(AccountPassword_)){
            return true;
        }else{
            return false;
        }
    }
}
