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
    
    public void insertProject(String proID, String proName, String start, String end, String customer){
        try {
            stm = cnt.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strInsert = "insert into project values ('" + proID + "','" + proName + "','"+ 
                    start + "','" + end + "','" + customer + "')";
            stm.execute(strInsert);
        } catch (Exception e) { 
            System.out.println("Add error" + e.getMessage());
        }
    }
}
