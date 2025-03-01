package com.sd.pt.entity;

public class Users {
    private Long id;
    private String username;
    private String password;
    private String role;
    private String type;
    private String phoneOremail;
    private String ipAddress;
    private int ifWork;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getIpAddress() {return ipAddress;}
    public void setIpAddress(String ipAddress) {this.ipAddress = ipAddress;}
    public int getIfWork() {return ifWork;}
    public void setIfWork(int ifWork) {this.ifWork = ifWork;}
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
    public String getPhoneOremail() {return phoneOremail;}
    public void setPhoneOremail(String phoneOremail) {this.phoneOremail = phoneOremail;}
}