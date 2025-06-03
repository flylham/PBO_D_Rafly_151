package com.praktikum.gui;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import com.praktikum.main.LoginSystem;
import com.praktikum.data.DataStore;
import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;

public class LoginPane {

    private VBox root;
    private ComboBox<String> userTypeComboBox;
    private TextField idField;
    private PasswordField passwordField;
    private Label messageLabel;

    public LoginPane() {
        initializeUI();
    }

    private void initializeUI() {
        // --- Komponen UI ---
        Label titleLabel = new Label("Login Sistem Lost & Found");
        titleLabel.setFont(new Font("System Bold", 18));

        Label nameLabel = new Label("Nama:");
        nameLabel.setFont(new Font("System", 14));

        userTypeComboBox = new ComboBox<>();
        userTypeComboBox.setItems(FXCollections.observableArrayList("Admin", "Mahasiswa"));
        userTypeComboBox.setValue("Mahasiswa");

        idField = new TextField();
        idField.setPromptText("Username / Nama");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password / NIM");

        Label pilihanLabel = new Label("Pilih Opsi:");
        pilihanLabel.setFont(new Font("System", 14));

        RadioButton rdOpsi1 = new RadioButton("Opsi 1");
        RadioButton rdOpsi2 = new RadioButton("Opsi 2");

        ToggleGroup toggleGroup = new ToggleGroup();
        rdOpsi1.setToggleGroup(toggleGroup);
        rdOpsi2.setToggleGroup(toggleGroup);
        rdOpsi1.setSelected(true); // default selected

        Button loginButton = new Button("Login");
        loginButton.setPrefWidth(80);

        messageLabel = new Label();
        messageLabel.setTextFill(Color.RED);

        // Event handler
        loginButton.setOnAction(event -> handleLogin());

        // Tata letak
        root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(
                titleLabel,
                userTypeComboBox,
                nameLabel,
                idField,
                passwordField,
                pilihanLabel,
                rdOpsi1,
                rdOpsi2,
                loginButton,
                messageLabel
        );
    }

    private void handleLogin() {
        String userType = userTypeComboBox.getValue();
        String id = idField.getText();
        String passwordOrNim = passwordField.getText();

        if (userType == null || id.isEmpty() || passwordOrNim.isEmpty()) {
            messageLabel.setText("Semua field harus diisi!");
            return;
        }

        User authenticatedUser = LoginSystem.authenticateUser(userType, id, passwordOrNim);

        if (authenticatedUser != null) {
            messageLabel.setText("Login Berhasil!");
            if (authenticatedUser instanceof Admin) {
                MainApp.loadAdminDashboard();
            } else if (authenticatedUser instanceof Mahasiswa) {
                MainApp.loadMahasiswaDashboard((Mahasiswa) authenticatedUser);
            }
        } else {
            messageLabel.setText("Login gagal, periksa kredensial.");
        }
    }

    public Parent getRoot() {
        return root;
    }
}
