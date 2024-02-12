package org.example.finalprojectjfx_game;

import org.example.finalprojectjfx_game.MainMenuController;
import java.io.IOException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.finalprojectjfx_game.leaderbord.Leader;
import org.example.finalprojectjfx_game.leaderbord.LeaderRepository;

public class LeaderBoardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackMainMenuOneID;

    @FXML
    private TableView<Leader> Table0;

    @FXML
    void BackMainMenuOne(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void LeaderBoardTable(ActionEvent event) {

    }

    @FXML
    void initialize() throws IOException{
        assert BackMainMenuOneID != null : "fx:id=\"BackMainMenuOneID\" was not injected: check your FXML file 'LeaderBoard.fxml'.";
        assert Table0 != null : "fx:id=\"Table0\" was not injected: check your FXML file 'LeaderBoard.fxml'.";
        initData();
    }

    @FXML
    private void initData() throws IOException{
        TableColumn<Leader, String> rank = new TableColumn<Leader, String>("№");
        rank.setCellValueFactory(new PropertyValueFactory<Leader, String>("rank"));
        rank.setPrefWidth(37.60003662109375);

        TableColumn<Leader, Integer> name = new TableColumn<Leader, Integer>("Имя");
        name.setCellValueFactory(new  PropertyValueFactory<Leader, Integer>("name"));
        name.setPrefWidth(125.5999755859375);

        TableColumn<Leader, Long> score = new TableColumn<Leader, Long>("Счёт");
        score.setCellValueFactory(new PropertyValueFactory<Leader, Long>("score"));
        score.setPrefWidth(195.90003662109375);

        Table0.getColumns().add(rank);
        Table0.getColumns().add(name);
        Table0.getColumns().add(score);

        for (int i = 0; i < new LeaderRepository("src/main/resources/org/example/finalprojectjfx_game/Tables/Leader.txt").TableSize(); i++){
            Table0.getItems().add(new LeaderRepository("src/main/resources/org/example/finalprojectjfx_game/Tables/Leader.txt").getLeaderPosition(i));
        }
    }

}
