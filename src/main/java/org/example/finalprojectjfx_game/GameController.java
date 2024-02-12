package org.example.finalprojectjfx_game;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.finalprojectjfx_game.game.PlayerModel;
import org.example.finalprojectjfx_game.game.collisionDetection;
import org.example.finalprojectjfx_game.leaderbord.LeaderRepository;
import org.example.finalprojectjfx_game.platform.Platform;
import org.example.finalprojectjfx_game.platform.PlatformControler;

import java.io.IOException;
import java.util.ArrayList;

import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    AnimationTimer gameLoop;

    @FXML
    private Pane EndGameWindow;

    @FXML
    private AnchorPane Plane;


    @FXML
    private Rectangle PlayerModel;

    @FXML
    private Label ScorePanel;

    @FXML
    private Text Score;

    boolean leftPressed = false;
    boolean rightPressed = false;
    private PlatformControler platformControler;
    private collisionDetection collision;
    private org.example.finalprojectjfx_game.game.PlayerModel PlayerComponent;
    Random random = new Random();
    ArrayList<Platform> PlatformMap = new ArrayList<>();
    public double score;
    double pDelta = 0.005;




    @FXML
    void ClickExit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ClickRestart(ActionEvent event) {
        ResetGame();
    }

    @FXML
    void PressedButton(KeyEvent event) {
        if(event.getCode() == KeyCode.SPACE){
            PlayerComponent.jump();
        }

        if(event.getCode() == KeyCode.D){
            rightPressed = true;
        }

        if(event.getCode() == KeyCode.A){
            leftPressed = true;
        }
    }

    @FXML
    void ReleasedKey(KeyEvent event) {
        if(event.getCode() == KeyCode.D){
            rightPressed = false;
        }

        if(event.getCode() == KeyCode.A){
            leftPressed = false;
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        platformControler = new PlatformControler(Plane);
        PlayerComponent = new  PlayerModel(PlayerModel);
        collision = new collisionDetection();
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }
        };
        gameLoop.start();
        load();
    }

    //Called every game frame
    private void update() {

        Score.setText(String.valueOf((int)score));

        PlayerComponent.PlayerUpdate();
        platformControler.removeOutOfscreen(PlatformMap);
        platformControler.platformGenerater(random.nextInt(3) + 1 ,PlatformMap, PlayerComponent);

        if ((PlayerModel.getY()+PlayerModel.getLayoutY() <= 300)&&( PlatformMap.get(PlatformMap.size()-1).getPlatform().getY() <= 300 )){screnMove();}

        if (collision.checkPlayerCollisionDetection(PlatformMap,PlayerComponent)){PlayerComponent.jump();}
        if (rightPressed){PlayerComponent.moveRight();}
        if (leftPressed){PlayerComponent.moveLeft();}

        if(PlayerComponent.isPlayerDead(Plane)){
            EndGameMenu();
        }
    }




    private void load(){
        System.out.println("Game starting");
        PlatformMap.add(platformControler.createPlatfhorm(200, 700));
        PlatformMap.add(platformControler.createPlatfhorm(90, 500));
        PlatformMap.add(platformControler.createPlatfhorm(200, 100));
        score = 0;
        Plane.requestFocus();
    }

    private void EndGameMenu(){
        try{
            new LeaderRepository("src/main/resources/org/example/finalprojectjfx_game/Tables/Leader.txt").addLeaderFile((long)score, "src/main/resources/org/example/finalprojectjfx_game/Tables/NameStorage.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            ScorePanel.setText(String.valueOf((int) score));
            EndGameWindow.setVisible(true);
            EndGameWindow.toFront();
        }
    }

    private void ResetGame()
    {
        platformControler.removeAll(PlatformMap);
        EndGameWindow.setVisible(false);
        EndGameWindow.toBack();
        PlayerComponent.resetPlayer();
        score = 0;
        load();
    }
    private void screnMove(){
        for (Platform platform: PlatformMap){
            platformControler.movePlatform(platform, 0,(Plane.getHeight() - (PlayerModel.getY()+PlayerModel.getLayoutY()))*pDelta);
        }
        PlayerComponent.setPlayer(0,(Plane.getHeight() - (PlayerModel.getY()+PlayerModel.getLayoutY()))*pDelta);
        score += (Plane.getHeight() - (PlayerModel.getY()+PlayerModel.getLayoutY())*pDelta)/ 10000;
    }

}

