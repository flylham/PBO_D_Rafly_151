package com.praktikum.users;
import java.util.Scanner;
import com.praktikum.actions.MahasiswaActions;


public class Mahasiswa extends User implements MahasiswaActions {
    Scanner scan = new Scanner(System.in);
    public Mahasiswa(String nama, String nim){
        super(nama,nim);
    }

    @Override
    public boolean login(String inputNama, String inputNim){
        return this.getNama().equals(inputNama) && this.getNim().equals(inputNim);
    }

    @Override
    public void displayInfo(){
        System.out.println("Login berhasil! Selamat datang, " + getNama() + "(com.praktikum.users.Mahasiswa)");
    }

    @Override
    public void reportItem() {
        System.out.println("masukkan nama barang: ");
        String nama = scan.nextLine();
        System.out.println("masukkan deskripsi barang: ");
        String deskripsi = scan.nextLine();
        System.out.println("masukkan lokasi terakhir/ditemukan: ");
        String lokasi = scan.nextLine();

        System.out.println("nama barang:"+nama);
        System.out.println("deskripsi barang:"+deskripsi);
        System.out.println("lokasi terakhir/ditemukan:"+lokasi);    }

    @Override
    public void viewReportedItems() {
        System.out.println(">> Fitur Lihat Laporan Belum Tersedia <<");

    }
    @Override
    public void dispLayAppMenu() {
        while (true){
            System.out.println("Judul menu utama mahasiswa\n");
            System.out.println("1. Laporkan Barang Temuan/Hilang ");
            System.out.println("2. Lihat Daftar Laporan");
            System.out.println("3. Logout");

            System.out.print("masukkan pilihan anda:");
            int pilihan = scan.nextInt();
            scan.nextLine();
            if (pilihan == 1){
                reportItem();
            } else if (pilihan == 2) {
                viewReportedItems();
            } else if (pilihan == 3) {
                System.out.println("dah keluar hehehe");
                break;
            }else
                System.out.println("Hayoo");
        }

    }
}