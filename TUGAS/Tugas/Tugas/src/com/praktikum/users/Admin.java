package com.praktikum.users;

import com.praktikum.actions.AdminActions;

public class Admin extends User implements AdminActions {
    private String username;
    private String password;

    public Admin(String nama, String nim, String username, String password) {
        super(nama, nim);
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean login(String inputUser, String inputPassword) {
        return this.username.equals(inputUser) && this.password.equals(inputPassword);
    }

    @Override
    public void displayInfo() {
    }

    @Override
    public void manageItems() {
    }

    @Override
    public void manageUsers() {
    }

    @Override
    public void dispLayAppMenu() {
    }
}