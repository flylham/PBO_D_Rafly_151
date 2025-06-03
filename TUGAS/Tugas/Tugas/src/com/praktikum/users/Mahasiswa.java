package com.praktikum.users;

import com.praktikum.actions.MahasiswaActions;

public class Mahasiswa extends User implements MahasiswaActions {

    public Mahasiswa(String nama, String nim){
        super(nama,nim);
    }

    @Override
    public boolean login(String inputNama, String inputNim){
        return this.getNama().equals(inputNama) && this.getNim().equals(inputNim);
    }

    @Override
    public void reportItem() {
    }

    @Override
    public void viewReportedItems() {
    }

    @Override
    public void dispLayAppMenu() {
    }
}