package com.myapp.contentsapi.driver.db;

import lombok.Data;

@Data
public class UserInfo {
    public String id;
    public String firstName;
    public String lastName;
    public String department;

    public UserInfo() {}
    public UserInfo(String id, String firstName, String lastName, String department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }
}
