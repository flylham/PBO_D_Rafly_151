package com.praktikum.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.praktikum.users.User; // Import User class
import com.praktikum.users.Mahasiswa; // Import Mahasiswa class
import com.praktikum.users.Admin; // Import Admin class

public class MainApp extends Application {

    private static Stage primaryStage;
    private static User loggedInUser; // Untuk menyimpan user yang berhasil login

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("Lost & Found Kampus");
        loadLoginScene(); // Memuat scene login pertama kali
    }

    // Metode untuk memuat scene login
    public static void loadLoginScene() {
        LoginPane loginPane = new LoginPane(); // Instansiasi kelas LoginPane
        Scene scene = new Scene(loginPane.getRoot(), 320, 300); // Mendapatkan root Node dari LoginPane
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Metode untuk memuat dashboard Mahasiswa
    public static void loadMahasiswaDashboard(Mahasiswa mahasiswa) {
        loggedInUser = mahasiswa; // Simpan user yang login
        MahasiswaDashboard mhsDashboard = new MahasiswaDashboard(mahasiswa); // Instansiasi dashboard dengan user
        Scene scene = new Scene(mhsDashboard.getRoot(), 800, 600); // Mendapatkan root Node
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lost & Found Kampus - Mahasiswa Dashboard");
        primaryStage.show();
    }

    // Metode untuk memuat dashboard Admin
    public static void loadAdminDashboard() {
        // Admin tidak butuh objek spesifik seperti Mahasiswa
        AdminDashboard adminDashboard = new AdminDashboard(); // Instansiasi dashboard
        Scene scene = new Scene(adminDashboard.getRoot(), 900, 600); // Mendapatkan root Node
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lost & Found Kampus - Admin Dashboard");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}