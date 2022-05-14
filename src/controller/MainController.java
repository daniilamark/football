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

public class MainController implements Initializable {

    StadiumController stadiumController;
    TeamController teamController;

    @FXML private BorderPane borderpane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SelectCityController.mainController = this; //////////////////////
    }

    @FXML private ImageView btnLogout;

    @FXML private Label btn_city;

    @FXML private Label btn_math;

    @FXML private Label btn_player;

    @FXML private Label btn_stadium;

    @FXML private Label btn_team;

    @FXML private Label btn_trainer;

    private int numUI = 0;

    public int getNumUI() {
        return numUI;
    }

    public void setNumUI(int numUI) {
        this.numUI = numUI;
    }

    @FXML
    void clickBtnCity(MouseEvent event) {
        loadUI(Const.UI_CITY);
    }

    @FXML
    void clickBtnMath(MouseEvent event) {
        loadUI(Const.UI_MATH);
    }

    @FXML
    void clickBtnPlayer(MouseEvent event) {
        loadUI(Const.UI_PLAYER);
    }

    @FXML
    void clickBtnStadium(MouseEvent event) {
        loadUI(Const.UI_STADIUM);
        setNumUI(1);
    }

    @FXML
    void clickBtnTeam(MouseEvent event) {
        loadUI(Const.UI_TEAM);
        setNumUI(2);
    }

    @FXML
    void clickBtnTrainer(MouseEvent event) {
        loadUI(Const.UI_TRAINER);
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
