package Java.Dao;

import Java.Domain.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Forget {
    public static boolean Check_UserAccountNumberAndEmail(User user) throws Exception{
        //从连接池中拿到连接
        Connection con = Java.Utils.JDBCUtilsByDruid.getConnection();
        System.out.println("连接成功");
        String sql = "select AccountNumber, Email from user where AccountNumber=? and Email=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setObject(1, user.getAccountNumber()); // 注意：索引从1开始
        ps.setObject(2, user.getEmail());
        String AccountNumber_ = null;
        String Email_ = null;
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                AccountNumber_ = rs.getString("AccountNumber");
                Email_ = rs.getString("Email");
            }
        }
        ps.close();
        con.close();
        System.out.println("关闭成功");
        if(AccountNumber_==null && Email_==null){//数据库中并无此账号(与其绑定在一起的邮箱)
            return false;
        }else{
            return true;
        }
    }

    public static boolean Reset_AccountPassword(User user) throws Exception{
        Connection con = Java.Utils.JDBCUtilsByDruid.getConnection();
        System.out.println("连接成功");
        String sql = "UPDATE user SET AccountPassword = ? WHERE AccountNumber = ? && Email = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setObject(1,user.getAccountPassword());
        ps.setObject(2,user.getAccountNumber());
        ps.setObject(3,user.getEmail());
        if(ps.execute()){
            return false;
        }else{
            return true;
        }
    }

}
