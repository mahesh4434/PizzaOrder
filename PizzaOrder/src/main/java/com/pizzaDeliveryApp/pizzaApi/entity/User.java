package com.pizzaDeliveryApp.pizzaApi.entity;


import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "contact"),
        @UniqueConstraint(columnNames = "emailId" )   })
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int userid;

    @NotNull
    @Column(name = "user_name", length = 255)
    private String username;


    @Column(name = "contact")
    private double contact;
    @Column(name = "emailId", length = 255)
    private String emailId;

    @Column(name = "password", length = 255)
    private String password;




    public User(int userid, String username, double contact, String emailId, String password) {
        this.userid = userid;
        this.username = username;
        this.contact = contact;
        this.emailId = emailId;
        this.password = password;


    }

    public User() {
    }
    public User(Integer userid) {
        this.userid = userid;
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

    public void setEmailId(String username) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
