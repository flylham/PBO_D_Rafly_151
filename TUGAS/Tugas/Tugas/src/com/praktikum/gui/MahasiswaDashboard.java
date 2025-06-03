package com.praktikum.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import com.praktikum.data.Item; //
import com.praktikum.data.DataStore; // Tambahkan import untuk DataStore
import com.praktikum.users.Mahasiswa; //

public class MahasiswaDashboard {

    private VBox root;
    private Label welcomeLabel;
    private TextField itemNameField;
    private TextField itemDescriptionField;
    private TextField itemLocationField;
    private Label reportMessageLabel;
    private ListView<String> reportedItemsListView;

    private Mahasiswa currentUser; // Referensi ke user mahasiswa yang login

    public MahasiswaDashboard(Mahasiswa mahasiswa) {
        this.currentUser = mahasiswa;
        initializeUI();
        updateReportedItemsList(); // Perbarui daftar saat dashboard dimuat
    }

    private void initializeUI() {
        // --- Komponen UI ---
        welcomeLabel = new Label("Selamat datang, " + currentUser.getNama()); //
        welcomeLabel.setFont(new Font(18));

        Separator separator1 = new Separator();

        Label reportTitle = new Label("Laporkan Barang Hilang/Temuan");
        reportTitle.setFont(new Font(14));

        itemNameField = new TextField();
        itemNameField.setPromptText("Nama Barang");
        itemDescriptionField = new TextField();
        itemDescriptionField.setPromptText("Deskripsi Barang");
        itemLocationField = new TextField();
        itemLocationField.setPromptText("Lokasi Terakhir/Ditemukan");
        TextField itemReportField = new TextField();
        itemReportField.setPromptText("Nama Pelapor");
        Button reportButton = new Button("Laporkan");
        reportMessageLabel = new Label();
        reportMessageLabel.setTextFill(Color.RED);

        HBox reportFields = new HBox(10, new Label("Nama Barang:"), itemNameField, new Label("Deskripsi:"), itemDescriptionField, new Label("Lokasi:"), itemLocationField, new Label("Pelapor"),itemReportField, reportButton);
        reportFields.setAlignment(Pos.CENTER_LEFT);
        reportFields.setPadding(new Insets(0, 0, 0, 0)); // No extra padding for HBox

        Separator separator2 = new Separator();

        Label reportedItemsTitle = new Label("Daftar Laporan Anda");
        reportedItemsTitle.setFont(new Font(14));

        Label nameItemsTitle = new Label("Nama");
        nameItemsTitle.setFont(new Font(16));

        reportedItemsListView = new ListView<>();
        Button logoutButton = new Button("Logout");

        // --- Event Handling ---
        reportButton.setOnAction(event -> handleReportItem());
        logoutButton.setOnAction(event -> MainApp.loadLoginScene());

        // --- Tata Letak (Layout) ---
        root = new VBox(10);
        root.setPadding(new Insets(14));
        root.getChildren().addAll(welcomeLabel, separator1, reportTitle, reportFields, reportMessageLabel,
                separator2, reportedItemsTitle, reportedItemsListView, logoutButton);
    }

    private void handleReportItem() {
        String itemName = itemNameField.getText();
        String description = itemDescriptionField.getText();
        String location = itemLocationField.getText();

        if (itemName.isEmpty() || description.isEmpty() || location.isEmpty()) {
            reportMessageLabel.setText("Harap lengkapi semua field laporan!");
            return;
        }

        // Perhatikan urutan parameter konstruktor Item (itemName, location, description, reported)
        Item item = new Item(itemName, location, description, currentUser); //
        DataStore.getReportedItems().add(item); // Gunakan DataStore untuk mendapatkan list

        reportMessageLabel.setText("Barang berhasil dilaporkan!");
        itemNameField.clear();
        itemDescriptionField.clear();
        itemLocationField.clear();
        updateReportedItemsList(); // Perbarui daftar setelah melaporkan
    }

    private void updateReportedItemsList() {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Item item : DataStore.getReportedItems()) { // Gunakan DataStore untuk mendapatkan list
            // Hanya tampilkan barang yang dilaporkan oleh user ini dan statusnya 'Reported'
            if (item.getreported().equals(currentUser) && "Reported".equals(item.getStatus())) { //
                items.add("Nama: " + item.getitemName() + " | Lokasi: " + item.getlocation() + " | Deskripsi: " + item.getdescription()  + " | Pelapor: " + currentUser.getNama());
            }
        }
        if (items.isEmpty()) {
            items.add("Belum ada laporan barang.");
        }
        reportedItemsListView.setItems(items);
    }

    public Parent getRoot() {
        return root;
    }
}