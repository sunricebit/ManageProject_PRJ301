/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import context.DBContext;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author ADMIN
 */
public class UserDAO {

    PreparedStatement stmt;
    ResultSet rs;
    Statement stm;

    public UserDAO() {
        connectDB();
    }
    Connection cnt;

    public void connectDB() {
        try {
            cnt = (new DBContext()).getConnection();
            System.out.println("Connect successful!");
        } catch (Exception e) {
            System.out.println("Connect failed! Error: " + e.getMessage());
        }
    }

    public boolean login(String username, String pass) {
        try {
            String sql = "select * from employee where username = ?";
            stmt = cnt.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while (rs.next()) {
                boolean checkPass = BCrypt.checkpw(pass, rs.getString(4));
                return checkPass;
            }
        } catch (Exception e) {
            System.out.println("Log in failed. Error: " + e.getMessage());
        }
        return false;
    }

    /*public String login(String email, String pass) {
        try {
            String sql = "select * from department where email = ?";
            stmt = cnt.prepareStatement(sql);
            stmt.setString(1, account);
            stm = cnt.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "select * from department where email = '" + email + "'";
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                boolean checkPass = BCrypt.checkpw(pass, rs.getString(3));
                return rs.getString(3);
            }
        } catch (Exception e) {
            System.out.println("Log in failed. Error: " + e.getMessage());
        }
        return "invalid";
    }*/

    public String createAccount(String username, String fullname, String email, String pass, int pm) {
        String mess = "";
        if (username.matches("^[0-9a-zA-Z]+$") == false) {
            mess = "Username using only letters and numbers";
            return mess;
        }
        try {
            String sql = "insert into employee values (?,?,?,?,null,?,?)";
            stmt = cnt.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, fullname);
            stmt.setString(3, email);
            String hash = BCrypt.hashpw(pass, BCrypt.gensalt(11));
            stmt.setString(4, hash);
            stmt.setInt(5, pm);
            stmt.setString(6, "image/default-avatar.png");
            stmt.execute();
            mess = "Create new account successful";
            return mess;
        } catch (Exception e) {
            System.out.println("Create account failed! Error : " + e.getMessage());
        }
        return mess = "Create account failed!";
    }

    public User checkAccount(String username) {
        try {
            String sql = "select * from employee where username = ?";
            stmt = cnt.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String fullname = rs.getString(2);
                String email = rs.getString(3);
                String proID = rs.getString(5);
                String role = "Employee";
                if (rs.getBoolean(6)){
                    role = "PM";
                }
                String avatar = rs.getString(7);
                User u = new User();
                u.setAvatar(avatar);
                u.setEmail(email);
                u.setFullname(fullname);
                u.setRole(role);
                u.setUsername(username);
                return u;
            }
        } catch (Exception e) {
            System.out.println("Log in failed. Error: " + e.getMessage());
        }
        return null;
    }

    public void changePass(String username, String pass) {
        try {
            String sql = "update * from tblUser where username = ?";
            stmt = cnt.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String fullname = rs.getString(2);
                String email = rs.getString(3);
                String proID = rs.getString(5);
                String role = "Employee";
                if (rs.getBoolean(6)){
                    role = "PM";
                }
                String avatar = rs.getString(7);
                User u = new User();
                u.setAvatar(avatar);
                u.setEmail(email);
                u.setFullname(fullname);
                u.setRole(role);
                u.setUsername(username);
            }
        } catch (Exception e) {
            System.out.println("Log in failed. Error: " + e.getMessage());
        }
    }
}
