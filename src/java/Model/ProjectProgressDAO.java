/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import context.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author MyPC
 */
public class ProjectProgressDAO {

    Connection cnt; //ket noi co so du lieu(CSDL)
    Statement stm; //thuc hien cac cau lenh sql
    ResultSet rs;
    PreparedStatement stmt;

    public ProjectProgressDAO() {
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

    public ArrayList<ProjectProgress> getAll() {
        ArrayList<ProjectProgress> list = new ArrayList<ProjectProgress>();
        try {
            stm = cnt.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "select * from project";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String id, projectName, customer, status,
                        start, deadline;
                id = rs.getString(1);
                projectName = rs.getString(2);
                start = rs.getString(3);
                deadline = rs.getString(4);
                customer = rs.getString(5);
                status = rs.getString(6);
                list.add(new ProjectProgress(id, projectName, start, deadline, customer, status));

            }
        } catch (Exception e) {
            System.out.println("getAll err: " + e.getMessage());
        }

        return list;
    }

    public ArrayList<ProjectProgress> getListByPage(ArrayList<ProjectProgress> listAll, int start, int end) {
        ArrayList<ProjectProgress> list = new ArrayList<ProjectProgress>();
        for (int i = start; i <= end; i++) {
            list.add(listAll.get(i));
        }
        return list;
    }

    public void deleteByID(String id) {

        try {
            stm = cnt.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strDelete = "Delete from project where id='" + id + "'";
            stm.execute(strDelete);

        } catch (Exception e) {
            System.out.println("DelbyID error " + e.getMessage());
        }

    }

    public ProjectProgress getProjectByID(String id) {

        try {
            stm = cnt.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String strSelect = "Select * from project where id='" + id + "'";
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                return new ProjectProgress(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));

            }
        } catch (Exception e) {
        }
        return null;
    }

    public void updateProject(String id, String projectName, String start, String deadline, String customer, String status) {

        try {
            String strSelect = " update project set projectName='" + projectName + "',start='" + start + "', deadline='" + deadline + "', customer='" + customer + "', status='" + status + "' where id='" + id + "'    ";

            cnt = new DBContext().getConnection();
            stmt = cnt.prepareStatement(strSelect);
            stmt.setString(1, projectName);
            stmt.setString(2, start);
            stmt.setString(3, deadline);
            stmt.setString(4, customer);
            stmt.setString(5, status);
            stmt.setString(6, id);
            stmt.executeUpdate();
        } catch (Exception e) {
        }
    }

}
