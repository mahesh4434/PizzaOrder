package com.pizzaDeliveryApp.pizzaApi.dto;

public class UserDto {
    private int userid;
    private String username;
    private double contact;
    private String emailId;
    private String password;

    public UserDto() {
    }

    public UserDto(int userid, String username, double contact, String emailId, String password) {
        this.userid = userid;
        this.username = username;
        this.contact = contact;
        this.emailId = emailId;
        this.password = password;
    }

    public int getuserid() {
        return userid;
    }

    public void setuserid(int userid) {
        this.userid = userid;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }
    public double getContact(){
        return contact;
    }
    public void setContact(double contact){
        this.contact = contact;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CustomDTO{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", contact='" + contact + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
