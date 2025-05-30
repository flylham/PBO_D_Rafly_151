import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Random;

public class TebakAngkaAdvance extends Application {
    private int angkaAcak;
    private int jumlahTebakan = 0;

    private TextField inputField;
    private Label feedbackLabel;
    private Label percobaanLabel;
    private Button actionButton;

    @Override
    public void start(Stage primaryStage) {
        generateAngkaBaru();

        Label title = new Label("🔁 Tebak Angka 1–100");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextFill(Color.DARKBLUE);

        feedbackLabel = new Label();
        feedbackLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        inputField = new TextField();
        inputField.setPromptText("Masukkan tebakanmu!");
        inputField.setMaxWidth(200);

        actionButton = new Button("🟢 Coba Tebak!");
        actionButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        actionButton.setOnAction(e -> prosesTebakan());

        HBox inputArea = new HBox(10, inputField, actionButton);
        inputArea.setAlignment(Pos.CENTER);

        percobaanLabel = new Label("Jumlah percobaan: 0");
        percobaanLabel.setTextFill(Color.GRAY);

        VBox root = new VBox(15, title, feedbackLabel, inputArea, percobaanLabel);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #EAF6FF;");

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Tebak Angka Advance");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void prosesTebakan() {
        if (actionButton.getText().equals("Main Lagi")) {
            generateAngkaBaru();
            inputField.setDisable(false);
            inputField.clear();
            feedbackLabel.setText("");
            percobaanLabel.setText("Jumlah percobaan: 0");
            jumlahTebakan = 0;
            actionButton.setText("🟢 Coba Tebak!");
            actionButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
            return;
        }

        String input = inputField.getText();
        try {
            int tebakan = Integer.parseInt(input);
            jumlahTebakan++;

            if (tebakan < angkaAcak) {
                feedbackLabel.setText("🔻 Terlalu kecil!");
                feedbackLabel.setTextFill(Color.ORANGE);
            } else if (tebakan > angkaAcak) {
                feedbackLabel.setText("🔺 Terlalu besar!");
                feedbackLabel.setTextFill(Color.ORANGE);
            } else {
                feedbackLabel.setText("✅ Tebakan benar!");
                feedbackLabel.setTextFill(Color.GREEN);
                inputField.setDisable(true);
                actionButton.setText("🔄 Main Lagi");
                actionButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
            }

            percobaanLabel.setText("Jumlah percobaan: " + jumlahTebakan);
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("⚠️ Masukkan angka yang valid!");
            feedbackLabel.setTextFill(Color.RED);
        }
    }
    private void generateAngkaBaru() {
        Random rand = new Random();
        angkaAcak = rand.nextInt(100) + 1;
    }
    public static void main(String[] args) {
        launch(args);
    }
}