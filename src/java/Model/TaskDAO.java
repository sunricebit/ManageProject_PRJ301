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
 * @author MyPC
 */
public class TaskDAO {

    Connection cnt; //ket noi co so du lieu(CSDL)
    Statement stm; //thuc hien cac cau lenh sql
    ResultSet rs;
    PreparedStatement stmt;

    public TaskDAO() {
        connectDB();
    }

    private void connectDB() {
        try {
            cnt = (new DBContext()).getConnection();
            System.out.println("Connect successfully");
        } catch (Exception e) {
            System.out.println("Connect error: " + e.getMessage());
        }
    }

    public void AddTask(String taskID, String detail, String username, String proID, String status) {
        try {
            stm = cnt.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strInsert = "insert into task values ('" + taskID + "','" + detail + "','"
                    + username + "','" + proID + "','" + status + "')";
            stm.execute(strInsert);
        } catch (Exception e) {
            System.out.println("Add error" + e.getMessage());
        }
    }

    public ArrayList<Task> getAll() {
        ArrayList<Task> list = new ArrayList<Task>();
        try {
            stm = cnt.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "select * from task";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String taskID, detail, username, proID, status;
                taskID = rs.getString(1);
                detail = rs.getString(2);
                username = rs.getString(3);
                proID = rs.getString(4);
                status = rs.getString(5);

                list.add(new Task(taskID, detail, username, proID, status));

            }
        } catch (Exception e) {
            System.out.println("getAll err: " + e.getMessage());
        }

        return list;
    }

    public void deleteByID(String taskID) {

        try {
            stm = cnt.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strDelete = "Delete from task where taskID='" + taskID + "'";
            stm.execute(strDelete);

        } catch (Exception e) {
            System.out.println("DelbyID error " + e.getMessage());
        }

    }

    public void Count(String count) {

        try {
            stm = cnt.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strCount = "select count (*) as sum from task";
            rs = stm.executeQuery(strCount);
            while (rs.next()) {
                count = rs.getString(1);
                System.out.println("Total Row :" + count);
            }
        } catch (Exception e) {
            System.out.println("Count error " + e.getMessage());
        }

    }

    public ArrayList<Task> count() {
        ArrayList<Task> list = new ArrayList<Task>();
        try {
            stm = cnt.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "select count (*) from task";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String count;
                count = rs.getString(1);
                
                list.add(new Task(count));

            }
        } catch (Exception e) {
            System.out.println("getAll err: " + e.getMessage());
        }

        return list;
    }

    public boolean checkExistTask(String taskID) {
        try {
            stm = cnt.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "select* from task where taskID ='" + taskID + "'";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Check id error: " + e.getMessage());
        }
        return false;
    }
public ArrayList<Task> done() {
        ArrayList<Task> list = new ArrayList<Task>();
        try {
            stm = cnt.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "select count (stt) from task where stt='1'";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String count;
                count = rs.getString(1);
                
                list.add(new Task(count));

            }
        } catch (Exception e) {
            System.out.println("getAll err: " + e.getMessage());
        }

        return list;
    }
public ArrayList<Task> process() {
        ArrayList<Task> list = new ArrayList<Task>();
        try {
            stm = cnt.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "select count (stt) from task where stt='0'";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String count;
                count = rs.getString(1);
                
                list.add(new Task(count));

            }
        } catch (Exception e) {
            System.out.println("getAll err: " + e.getMessage());
        }

        return list;
    }
}
