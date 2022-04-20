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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private BorderPane borderpane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private ImageView btnLogout;

    @FXML
    private Label btn_city;

    @FXML
    private Label btn_math;

    @FXML
    private Label btn_player;

    @FXML
    private Label btn_stadium;

    @FXML
    private Label btn_team;

    @FXML
    private Label btn_trainer;

    @FXML
    void clickBtnCity(MouseEvent event) {
        loadUI("../views/ui_city");
    }

    @FXML
    void clickBtnMath(MouseEvent event) {

    }

    @FXML
    void clickBtnPlayer(MouseEvent event) {

    }

    @FXML
    void clickBtnStadium(MouseEvent event) {
        loadUI("../views/ui_stadium");
    }

    @FXML
    void clickBtnTeam(MouseEvent event) {

    }

    @FXML
    void clickBtnTrainer(MouseEvent event) {

    }

    @FXML
    void onMouseClickedLogout(MouseEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
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
}
