package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.Const;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainUserController implements Initializable {

    @FXML private BorderPane borderpane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML private ImageView btnLogout;

    @FXML private Label btn_math;

    @FXML private Label btn_player;

    @FXML private Label btn_stadium;

    @FXML private Label btn_team;


    @FXML
    void clickBtnMath(MouseEvent event) {
        loadUI(Const.UI_USER_MATH);
        btn_stadium.setText("Стадионы");
        btn_team.setText("Команды");
        btn_math.setText("Матчи <");
        btn_player.setText("Игроки");
    }

    @FXML
    void clickBtnPlayer(MouseEvent event) {
        loadUI(Const.UI_USER_PLAYER);
        btn_stadium.setText("Стадионы");
        btn_team.setText("Команды");
        btn_math.setText("Матчи");
        btn_player.setText("Игроки <");
    }

    @FXML
    void clickBtnStadium(MouseEvent event) {
        loadUI(Const.UI_USER_STADIUM);
        btn_stadium.setText("Стадионы <");
        btn_team.setText("Команды");
        btn_math.setText("Матчи");
        btn_player.setText("Игроки");
    }

    @FXML
    void clickBtnTeam(MouseEvent event) {
        loadUI(Const.UI_USER_TEAM);
        btn_stadium.setText("Стадионы");
        btn_team.setText("Команды <");
        btn_math.setText("Матчи");
        btn_player.setText("Игроки");
    }

    @FXML
    void onMouseClickedLogout(MouseEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(Const.UI_LOGIN));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("img/admin.png"));
        btnLogout.getScene().getWindow().hide();
        stage.show();
    }


    private void loadUI(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderpane.setCenter(root);
    }

    public FXMLLoader getLoader(String ui) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ui + ".fxml"));

        return loader;
    }
}
