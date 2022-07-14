        /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Msi
 */
public class ProjectDAO {
    PreparedStatement stmt;
    ResultSet rs;
    Statement stm;
    Connection cnt;
    
    public ProjectDAO() {
        connectDB();
    }

    public void connectDB() {
        try {
            cnt = (new DBContext()).getConnection();
            System.out.println("Connect successful!");
        } catch (Exception e) {
            System.out.println("Connect failed! Error: " + e.getMessage());
        }
    }
    
    public void addProject(String proID, String proName, String start, String end, String customer){
        try {
            stm = cnt.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strInsert = "insert into project values ('" + proID + "','" + proName + "','"+ 
                    start + "','" + end + "','" + customer + "')";
            stm.execute(strInsert);
        } catch (Exception e) { 
            System.out.println("Add error " + e.getMessage());
        }
    }
    
    public boolean checkExistNumber(String id) {
        try {
            stm = cnt.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "select* from project where proID ='" + id + "'";  
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Check id error: " + e.getMessage());
        }
        return false;
    }
    
    public ArrayList<Project> getProject(){
        ArrayList<Project> list = new ArrayList<>();
        
        try {
            stm = cnt.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "select* from project";  
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String start = rs.getString(3);
                String end = rs.getString(4);
                String customer = rs.getString(5);
                Project p = new Project(id, name, start, end, customer);
                list.add(p);
            }
        } catch (Exception e) { 
            System.out.println("Get project error " + e.getMessage());
        }
        return list;
    }
    
    public ArrayList<User> getUser(){
        ArrayList<User> list = new ArrayList<>();
        try {
            stm = cnt.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "select* from employee";  
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String username = rs.getString(1);
                String email = rs.getString(3);
                String role = rs.getString(6);
                String fullname = rs.getString(2);
                String avatar = rs.getString(7);
                User u = new User(username, email, role, fullname, avatar);
                list.add(u);
            }
        } catch (Exception e) { 
            System.out.println("Get employee error "+e.getMessage());
        }
        return list;
    }
}
