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
@Table(name = "\"Role\"")

public class Role {

    @Column(name = "\"role\"")
    private String role;

    @Column(name = "\"company\"")
    private String company;

    @Column(name = "\"skills\"")
    private String skills;

    @Id
    @Column(name = "\"usernameKey\"")
    private String usernameKey;

    //  constructors
    public Role() {
        super();
    }

    //  parameterized constructors
    public Role(String role, String company, String skills, String usernameKey) {
        this.role = role;
        this.company = company;
        this.skills = skills;
        this.usernameKey = usernameKey;
    }


    //    getters
    public String getRole() {
        return role;
    }

    public String getCompany() {
        return company;
    }

    public String getSkills() {
        return skills;
    }

    public String getUsernameKey() {
        return usernameKey;
    }

    //    setters
    public void setRole(String role) {
        this.role = role;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setUsernameKey(String usernameKey) {
        this.usernameKey = usernameKey;
    }
}
