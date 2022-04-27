package Java.Dao;

import Java.Domain.pojo.User;
import Java.Utils.DatabaseModel;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Register {

    public static boolean Inquire_Email(User user) throws Exception{
        Connection con = Java.Utils.JDBCUtilsByDruid.getConnection();
        System.out.println("连接成功");
        String sql = "select Email from user where Email=?"; //userlist为表名字
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setObject(1, user.getEmail()); // 注意：索引从1开始
        String email = null;
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                email = rs.getString("Email");
            }
        }
        ps.close();
        con.close();
        System.out.println("关闭成功");
        if(email==null){
            return true;
        }else{
            return false;
        }
    }

    public  static boolean Creat_NewUser(User user) throws Exception {

        Connection con = Java.Utils.JDBCUtilsByDruid.getConnection();
        System.out.println("连接成功");
        String sql = "INSERT INTO user(AccountNumber,AccountPassword,Email,Name,Age,Gender)value (?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setObject(1, user.getAccountNumber());
        ps.setObject(2, user.getAccountPassword());
        ps.setObject(3, user.getEmail());
        ps.setObject(4, user.getName());
        ps.setObject(5, user.getAge());
        ps.setObject(6, user.getGender());
        ps.execute();
        if (ps != null)
            ps.close();
        if (con != null)
            con.close();
        System.out.println("关闭成功");
        return true;
    }

    public static void main(String[] args) throws Exception{
        User user = new User();
        user.setEmail("1234@qq.com");
        Creat_NewUser(user);
    }
}
