/*
 *
 *  * Copyright (c) 2019. [Acme Corp]
 *  *
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  * of this software and associated documentation files (the "Software"), to deal
 *  * in the Software without restriction, including without limitation the rights
 *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  * copies of the Software, and to permit persons to whom the Software is
 *  * furnished to do so, subject to the following conditions:
 *  *
 *  * The above copyright notice and this permission notice shall be included in
 *  * all copies or substantial portions of the Software.
 *
 */

package com.bptn.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"UserID\"")
public class UserID {

    @Column(name = "\"name\"")
    private String name;

    @Column(name = "\"emailID\"")
    private String emailID;

    @Column(name = "\"phoneNumber\"")
    private String phoneNumber;

    @Column(name = "\"userPassword\"")
    private String userPassword;

    @Id
    @Column(name = "\"username\"")
    private String username;

    //    constructors
    public UserID() {
        super();
    }

    public UserID(String name, String emailID, String phoneNumber, String userPassword, String username) {

        this.name = name;
        this.emailID = emailID;
        this.phoneNumber = phoneNumber;
        this.userPassword = userPassword;
        this.username = username;
    }

    //	getter for name
    public String getName() {
        return this.name;
    }

    //	setter for name
    public void setName(String name) {
        this.name = name;
    }

    //	getter for emailId
    public String getEmailID() {
        return this.emailID;
    }

    //	setter for emailID
    public void setemailID(String emailID) {
        this.emailID = emailID;
    }

    //	getter for phoneNumber
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    //	setter for phoneNumber
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // getter for userPassword
    public String getUserPassword() {
        return this.userPassword;
    }

    //	setter for phoneNumber
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    // getter for username
    public String getUsername() {
        return this.username;
    }

    //		setter for username
    public void setUsername(String username) {
        this.username = username;
    }


}
