package ru.kata.spring.boot_security.demo.entity;

public class RoleNames {
    private String userRole1;
    private String userRole2;

    public RoleNames() {
    }

    public RoleNames(String userRole1, String userRole2) {
        this.userRole1 = userRole1;
        this.userRole2 = userRole2;
    }

    public String getUserRole1() {
        return userRole1;
    }

    public void setUserRole1(String userRole1) {
        this.userRole1 = userRole1;
    }

    public String getUserRole2() {
        return userRole2;
    }

    public void setUserRole2(String userRole2) {
        this.userRole2 = userRole2;
    }
}
