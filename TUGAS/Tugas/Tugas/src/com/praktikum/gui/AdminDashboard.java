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
import javafx.scene.layout.Priority;

import com.praktikum.data.Item;
import com.praktikum.data.DataStore;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;

import java.util.Iterator;

public class AdminDashboard {

    private VBox root;
    private ListView<String> reportedItemsListView;
    private TextField itemIndexToClaimField;
    private Label itemMessageLabel;

    private ListView<String> userListView;
    private TextField newUserNameField; // Digunakan untuk Nama (tambah)
    private TextField newUserNimField;  // Digunakan untuk NIM (tambah dan hapus)
    private Label userMessageLabel;

    private Button addUserButton;
    private Button removeUserButton;

    public AdminDashboard() {
        initializeUI();
        updateReportedItemsList();
        updateUserList();
    }

    private void initializeUI() {
        // --- Komponen UI ---
        Label welcomeLabel = new Label("Halo, Administrator admin");
        welcomeLabel.setFont(new Font(18));

        Separator separator1 = new Separator();

        // --- Laporan Barang Section (Tidak berubah) ---
        Label itemReportsTitle = new Label("Laporan Barang");
        itemReportsTitle.setFont(new Font(14));
        reportedItemsListView = new ListView<>();
        itemIndexToClaimField = new TextField();
        itemIndexToClaimField.setPromptText("Indeks");
        Button claimItemButton = new Button("Tandai Claimed");
        itemMessageLabel = new Label();
        itemMessageLabel.setTextFill(Color.RED);
        HBox itemActionBox = new HBox(10, itemIndexToClaimField, claimItemButton);
        itemActionBox.setPadding(new Insets(5, 0, 0, 0)); // Margin atas

        VBox itemReportsSection = new VBox(10, itemReportsTitle, reportedItemsListView, itemActionBox, itemMessageLabel);
        itemReportsSection.setPrefWidth(400); // Lebar tetap
        VBox.setMargin(itemReportsSection, new Insets(0, 10, 0, 0)); // Margin kanan


        // --- Data Mahasiswa Section (Bagian ini yang diubah) ---
        Label userDataTitle = new Label("Data Mahasiswa");
        userDataTitle.setFont(new Font(14));
        userListView = new ListView<>();

        // Input fields untuk Nama dan NIM (tanpa Label "Nama:" dan "NIM:")
        newUserNameField = new TextField();
        newUserNameField.setPromptText("Nama"); // Ubah prompt text
        newUserNameField.setMaxWidth(150); // Batasi lebar

        newUserNimField = new TextField();
        newUserNimField.setPromptText("NIM"); // Ubah prompt text
        newUserNimField.setMaxWidth(150); // Batasi lebar

        // Langsung masukkan TextField ke HBox untuk posisi Horizontal
        HBox inputFieldsVBox = new HBox(25); // Spasi Horizontal antar field
        inputFieldsVBox.getChildren().addAll(newUserNameField, newUserNimField);
        inputFieldsVBox.setAlignment(Pos.CENTER);


        // Tombol Tambah dan Hapus dalam satu HBox
        addUserButton = new Button("Tambah");
        addUserButton.setPrefWidth(80); // Atur lebar tombol

        removeUserButton = new Button("Hapus");
        removeUserButton.setPrefWidth(80); // Atur lebar tombol


        HBox actionButtonsBox = new HBox(15, addUserButton, removeUserButton); // Spasi antar tombol 10
        actionButtonsBox.setAlignment(Pos.CENTER); // Perataan tombol
        VBox.setMargin(actionButtonsBox, new Insets(20, 0, 0, 0));

        userMessageLabel = new Label();
        userMessageLabel.setTextFill(Color.RED);
        userMessageLabel.setAlignment(Pos.CENTER);

        // Gabungkan semua komponen Data Mahasiswa ke dalam VBox
        VBox userDataSection = new VBox(10); // Spasi 10 antar elemen VBox ini
        userDataSection.setPrefWidth(400); // Lebar tetap
        userDataSection.getChildren().addAll(userDataTitle, userListView, inputFieldsVBox, actionButtonsBox, userMessageLabel);
        userDataSection.setAlignment(Pos.TOP_CENTER);

        HBox mainContent = new HBox(20, itemReportsSection, userDataSection); // Spasi 20px antar dua VBox

        Button logoutButton = new Button("Logout");
        Button LoginMhswsButton = new Button("Login");

        // --- Event Handling ---
        claimItemButton.setOnAction(event -> handleClaimItem());
        addUserButton.setOnAction(event -> handleAddUser());
        removeUserButton.setOnAction(event -> handleRemoveUser());
        logoutButton.setOnAction(event -> MainApp.loadLoginScene());

        claimItemButton.setOnAction(event -> handleClaimItem());
        addUserButton.setOnAction(event -> handleAddUser());
        removeUserButton.setOnAction(event -> handleRemoveUser());
        LoginMhswsButton.setOnAction(event -> MainApp.loadLoginScene());

        // --- Initial List Updates ---
        updateReportedItemsList();
        updateUserList();

        // --- Tata Letak (Layout) ---
        root = new VBox(10);
        root.setPadding(new Insets(14));
        root.getChildren().addAll(welcomeLabel, separator1, mainContent, logoutButton);
    }

    // ... (Metode-metode lain seperti updateReportedItemsList, updateUserList,
    //        handleClaimItem, handleAddUser, handleRemoveUser, getRoot tetap sama)

    private void updateReportedItemsList() {
        ObservableList<String> items = FXCollections.observableArrayList();
        if (DataStore.getReportedItems().isEmpty()) { //
            items.add("Belum ada barang yang dilaporkan.");
        } else {
            int i = 1;
            for (Item item : DataStore.getReportedItems()) { //
                items.add("[" + i++ + "] " + item.getitemName() + " | Lokasi: " + item.getlocation() + " | Status: " + item.getStatus()); //
            }
        }
        reportedItemsListView.setItems(items);
    }

    private void updateUserList() {
        ObservableList<String> users = FXCollections.observableArrayList();
        int i = 1;
        for (User user : DataStore.getUserList()) { //
            if (user instanceof Mahasiswa) { //
                users.add("[" + i++ + "] Nama: " + user.getNama() + ", NIM: " + user.getNim()); //
            }
        }
        if (users.isEmpty()) {
            users.add("Belum ada mahasiswa yang terdaftar.");
        }
        userListView.setItems(users);
    }

    private void handleClaimItem() {
        String indexText = itemIndexToClaimField.getText();
        if (indexText.isEmpty()) {
            itemMessageLabel.setText("Masukkan indeks barang!");
            return;
        }

        try {
            int indeks = Integer.parseInt(indexText);
            if (indeks <= 0 || indeks > DataStore.getReportedItems().size()) { //
                itemMessageLabel.setText("Indeks tidak valid.");
                return;
            }
            Item item = DataStore.getReportedItems().get(indeks - 1); //
            item.setStatus("Claimed"); //
            itemMessageLabel.setText("Barang '" + item.getitemName() + "' berhasil ditandai sebagai 'Claimed'."); //
            updateReportedItemsList(); // Perbarui tampilan setelah perubahan
        } catch (NumberFormatException e) {
            itemMessageLabel.setText("Input harus berupa angka!");
        } catch (IndexOutOfBoundsException e) {
            itemMessageLabel.setText("Indeks di luar jangkauan!");
        } finally {
            itemIndexToClaimField.clear();
        }
    }

    private void handleAddUser() {
        String nama = newUserNameField.getText();
        String nim = newUserNimField.getText();

        if (nama.isEmpty() || nim.isEmpty()) {
            userMessageLabel.setText("Nama dan NIM harus diisi!");
            return;
        }

        // Cek apakah NIM sudah ada (untuk mencegah duplikasi)
        for (User user : DataStore.getUserList()) { //
            if (user instanceof Mahasiswa && user.getNim().equals(nim)) { //
                userMessageLabel.setText("Mahasiswa dengan NIM tersebut sudah ada!");
                return;
            }
        }

        Mahasiswa mhs = new Mahasiswa(nama, nim); //
        DataStore.getUserList().add(mhs); //
        userMessageLabel.setText("Mahasiswa '" + nama + "' berhasil ditambahkan.");
        newUserNameField.clear();
        newUserNimField.clear();
        updateUserList(); // Perbarui daftar pengguna
    }

    private void handleRemoveUser() {
        String nimToRemove = newUserNimField.getText(); // Menggunakan newUserNimField
        if (nimToRemove.isEmpty()) {
            userMessageLabel.setText("Masukkan NIM mahasiswa di field NIM untuk dihapus!");
            return;
        }

        boolean removed = false;
        Iterator<User> iterator = DataStore.getUserList().iterator(); //
        while (iterator.hasNext()) {
            User user = iterator.next();
            // Hanya hapus jika itu Mahasiswa dan NIM cocok
            if (user instanceof Mahasiswa && user.getNim().equals(nimToRemove)) { //
                iterator.remove();
                removed = true;
                userMessageLabel.setText("Mahasiswa dengan NIM " + nimToRemove + " berhasil dihapus.");
                break;
            }
        }
        if (!removed) {
            userMessageLabel.setText("Mahasiswa dengan NIM tersebut tidak ditemukan.");
        }
        newUserNameField.clear(); // Hapus juga nama jika sudah diisi
        newUserNimField.clear(); // Kosongkan field NIM setelah hapus
        updateUserList(); // Perbarui daftar pengguna
    }

    public Parent getRoot() {
        return root;
    }
}