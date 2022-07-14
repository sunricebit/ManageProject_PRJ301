/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author MyPC
 */
public class Task {
    String taskID,detail, username,proID,status;

  
    String count,count1;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Task(String count) {
        this.count = count;
     
    }

    public String getCount1() {
        return count1;
    }

    public void setCount1(String count1) {
        this.count1 = count1;
    }

   

    public Task(String taskID, String detail, String username, String proID, String status) {
        this.taskID = taskID;
        this.detail = detail;
        this.username = username;
        this.proID = proID;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
   

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProID() {
        return proID;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }
    
}
