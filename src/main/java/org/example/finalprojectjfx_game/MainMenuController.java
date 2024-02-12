package org.example.finalprojectjfx_game;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button EnterName;

    @FXML
    private Button Exit1;

    @FXML
    private Button LeaderBoard1;

    @FXML
    private Button PlayButton;

    @FXML
    private TextField PlayerName;

    @FXML
    void EnterName(ActionEvent event) throws IOException {
        PrintWriter writer = new PrintWriter("src/main/resources/org/example/finalprojectjfx_game/Tables/NameStorage.txt");
        writer.print(PlayerName.getText());
        writer.close();
    }

    @FXML
    void ExitGame(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void LeaderBoardOpen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LeaderBoard.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void PlayGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GameSceen.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getRoot().requestFocus();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() throws IOException {
        assert EnterName != null : "fx:id=\"EnterName\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert Exit1 != null : "fx:id=\"Exit1\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert LeaderBoard1 != null : "fx:id=\"LeaderBoard1\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert PlayButton != null : "fx:id=\"PlayButton\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert PlayerName != null : "fx:id=\"PlayerName\" was not injected: check your FXML file 'MainMenu.fxml'.";
        RegName();
    }

    void RegName() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/org/example/finalprojectjfx_game/Tables/NameStorage.txt"));
        PlayerName.setText(reader.readLine());
    }

}
