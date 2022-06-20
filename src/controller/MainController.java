package controller;

import javafx.event.EventHandler;
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
import javafx.stage.StageStyle;
import main.Const;
import main.DBHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    StadiumController stadiumController;
    TeamController teamController;

    @FXML private BorderPane borderpane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SelectCityController.mainController = this; //////////////////////
    }

    @FXML private ImageView btnExport;
    @FXML private ImageView btnLogout;
    @FXML private ImageView btnAddAdmin;

    @FXML private Label btn_city;

    @FXML private Label btn_math;

    @FXML private Label btn_player;

    @FXML private Label btn_stadium;

    @FXML private Label btn_team;

    @FXML private Label btn_trainer;
    DBHandler dbHandler = new DBHandler();
    private double x, y;
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
        btn_city.setText("Города <");
        btn_trainer.setText("Тренера");
        btn_stadium.setText("Стадионы");
        btn_team.setText("Команды");
        btn_math.setText("Матчи");
        btn_player.setText("Игроки");
    }

    @FXML
    void clickBtnMath(MouseEvent event) {
        loadUI(Const.UI_MATH);
        setNumUI(3);
        btn_city.setText("Города");
        btn_trainer.setText("Тренера");
        btn_stadium.setText("Стадионы");
        btn_team.setText("Команды");
        btn_math.setText("Матчи <");
        btn_player.setText("Игроки");
    }

    @FXML
    void clickBtnPlayer(MouseEvent event) {
        loadUI(Const.UI_PLAYER);
        setNumUI(4);
        btn_city.setText("Города");
        btn_trainer.setText("Тренера");
        btn_stadium.setText("Стадионы");
        btn_team.setText("Команды");
        btn_math.setText("Матчи");
        btn_player.setText("Игроки <");
    }

    @FXML
    void clickBtnStadium(MouseEvent event) {
        loadUI(Const.UI_STADIUM);
        setNumUI(1);
        btn_city.setText("Города");
        btn_trainer.setText("Тренера");
        btn_stadium.setText("Стадионы <");
        btn_team.setText("Команды");
        btn_math.setText("Матчи");
        btn_player.setText("Игроки");
    }

    @FXML
    void clickBtnTeam(MouseEvent event) {
        loadUI(Const.UI_TEAM);
        setNumUI(2);
        btn_city.setText("Города");
        btn_trainer.setText("Тренера");
        btn_stadium.setText("Стадионы");
        btn_team.setText("Команды <");
        btn_math.setText("Матчи");
        btn_player.setText("Игроки");
    }

    @FXML
    void clickBtnTrainer(MouseEvent event) {
        loadUI(Const.UI_TRAINER);
        btn_city.setText("Города");
        btn_trainer.setText("Тренера <");
        btn_stadium.setText("Стадионы");
        btn_team.setText("Команды");
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
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                x = mouseEvent.getSceneX();
                y = mouseEvent.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setX(mouseEvent.getScreenX() - x);
                stage.setY(mouseEvent.getScreenY() - y);
            }
        });

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

    @FXML
    void onMouseClickedAddAdmin(MouseEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(Const.UI_ADDADMIN));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("img/admin.png"));
        //btnAddAdmin.getScene().getWindow().hide();
        stage.show();
    }

    @FXML
    void onMouseClickedExport(MouseEvent event) {
        exportTables();
    }


    public void exportTables(){

        Connection conn = null;
        try {
            conn = dbHandler.getDBConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "SELECT * FROM city INTO OUTFILE '/tmp/name_file.csv' FIELDS TERMINATED BY ';'";
        dbHandler.executeQuery(query);
        System.out.println(query);
        System.out.println("lol2");
        ///////////////////////////////////////////////
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ///////////////////////////////////////////////
    }

}
