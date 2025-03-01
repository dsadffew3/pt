package com.sd.pt.entity;

import java.util.Date;

public class Tasks {
    private Long id;
    private String taskName;
    private Date startTime;
    private Date endTime;
    private String location;
    private Long employee1;
    private Long employee2;
    private Long employee3;
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getTaskName() {return taskName;}
    public void setTaskName(String taskName) {this.taskName = taskName;}
    public Date getStartTime() {return startTime;}
    public void setStartTime(Date startTime) {this.startTime = startTime;}
    public Date getEndTime() {return endTime;}
    public void setEndTime(Date endTime) {this.endTime = endTime;}
    public String getLocation() {return location;}
    public void setLocation(String location) {this.location = location;}
    public Long getEmployee1() {return employee1;}
    public void setEmployee1(Long employee1) {this.employee1 = employee1;}
    public Long getEmployee2() {return employee2;}
    public void setEmployee2(Long employee2) {this.employee2 = employee2;}
    public Long getEmployee3() {return employee3;}
    public void setEmployee3(Long employee3) {this.employee3 = employee3;}
}
