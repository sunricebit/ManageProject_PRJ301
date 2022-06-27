/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import context.DBContext;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 *
 * @author ADMIN
 */
public class UserDAO {
    PreparedStatement stmt; 
    ResultSet rs;

    public UserDAO() {
        connectDB();
    }
    Connection cnt;
    public void connectDB(){
        try {
            cnt = (new DBContext()).getConnection();
            System.out.println("Connect successful!");
        } catch (Exception e) {
            System.out.println("Connect failed! Error: " + e.getMessage());
        }
    }
    
    public boolean login(String account, String pass){
        try {
            String sql = "select from user where account = ? and pass = ?";
            stmt = cnt.prepareStatement(sql);
            stmt.setString(1, account);
            stmt.setString(2, pass);
            rs = stmt.executeQuery();
            while(rs.next()){
                return true;
            }
        } catch (Exception e) {
            System.out.println("Log in failed. Error: " + e.getMessage());
        }
        return false;
    }
    
}
