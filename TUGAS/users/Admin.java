package com.praktikum.users;
import java.util.Scanner;
import com.praktikum.actions.AdminActions;

public class Admin extends User implements AdminActions {
    Scanner scan = new Scanner(System.in);
    private String username;
    private String password;

    public Admin(String nama, String nim, String username, String password){
        super(nama, nim);
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean login(String input1, String input2){
        return this.username.equals(input1) && this.password.equals(input2);
    }

    @Override
    public void displayInfo(){
        System.out.println("Login berhasil! Selamat datang, " + getNama() + "(com.praktikum.users.Admin)");
    }

    @Override
    public void manageItems() {
        System.out.println(">> Fitur Kelola Barang Belum Tersedia <<");
    }

    @Override
    public void manageUsers() {
        System.out.println(">> Fitur Kelola Mahasiswa Belum Tersedia <<");
    }
    @Override
    public void dispLayAppMenu() {
        while (true){
            System.out.println("Judul menu utama admin\n");
            System.out.println("1. Kelola Laporan Barang ");
            System.out.println("2. Kelola Data Mahasiswa");
            System.out.println("3. Logout");

            System.out.print("masukkan pilihan anda:");
            int pilihan = scan.nextInt();
            scan.nextLine();
            if (pilihan == 1){
                manageItems();
            } else if (pilihan == 2) {
                manageUsers();
            } else if (pilihan == 3) {
                System.out.println("anda logout habib murka");
                break;
            }else {
                System.out.println("logout");
            }

        }
    }
}