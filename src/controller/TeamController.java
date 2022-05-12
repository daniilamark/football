package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Const;
import main.DBHandler;
import main.Help;
import main.ShowAlert;
import model.City;
import model.Stadium;
import model.Team;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TeamController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnSelectCity;

    @FXML
    private Button btnSelectStadium;

    @FXML
    private Button btnSelectTrainer;

    @FXML
    private TableColumn<Team, String> colCity;

    @FXML
    private TableColumn<Team, Integer> colLastRating;

    @FXML
    private TableColumn<Team, String> colName;

    @FXML
    private TableColumn<Team, String> colStadium;

    @FXML
    private TableColumn<Team, String> colTrainer;

    @FXML
    private TextField tfCity;

    @FXML
    private TextField tfLastRating;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfStadium;

    @FXML
    private TextField tfTrainer;

    @FXML
    private TableView<Team> tvTeam;

    ObservableList<Team> teamList;

    DBHandler dbHandler = new DBHandler();
    CityController cityController = new CityController();

    private double x, y;

    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == btnInsert) {
            insertRecord();
        } else if (event.getSource() == btnDelete) {
            deleteButton();
        } else if (event.getSource() == btnSelectCity) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/select_city.fxml"));
            Parent root = loader.load();
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
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            SelectCityController.teamController = this;
            stage.show();
        } else if (event.getSource() == btnSelectStadium) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/select_stadium.fxml"));
            Parent root = loader.load();
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
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            SelectStadiumController.teamController = this;
            stage.show();
        } else if (event.getSource() == btnSelectTrainer) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/select_trainer.fxml"));
            Parent root = loader.load();
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
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            SelectTrainerController.teamController = this;
            stage.show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showTeam();
    }

    public void showTeam() {
        ObservableList<Team> listTeam = getTeamList();

        colStadium.setCellValueFactory(new PropertyValueFactory<>(Const.STADIUM_NAME));
        colTrainer.setCellValueFactory(new PropertyValueFactory<>(Const.STADIUM_CAPACITY));
        colCity.setCellValueFactory(new PropertyValueFactory<>(Const.CITY_NAME));
        colName.setCellValueFactory(new PropertyValueFactory<Team, String>(Const.TEAM_NAME));
        colLastRating.setCellValueFactory(new PropertyValueFactory<Team, Integer>(Const.TEAM_LAST_YEAR_RATING));

        tvTeam.setItems((ObservableList<Team>) listTeam);
    }


    public ObservableList<Team> getTeamList() {
        teamList = FXCollections.observableArrayList();

        Connection conn = null;
        try {
            conn = dbHandler.getDBConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "SELECT " + Const.STADIUM_NAME+ ","+ Const.TRAINER_NAME + "," + Const.CITY_NAME
                + "," + Const.TEAM_NAME + "," + Const.TEAM_LAST_YEAR_RATING + " FROM " + Const.STADIUM_TABLE + "," + Const.CITY_TABLE+ "," + Const.TEAM_TABLE + "," + Const.TRAINER_TABLE
                + " WHERE " + Const.TEAM_TABLE + "." + Const.STADIUM_ID + " = " + Const.STADIUM_ID + "." + Const.STADIUM_ID + // ; ????
                              Const.TEAM_TABLE + "." + Const.TRAINER_ID + " = " + Const.TRAINER_TABLE + "." + Const.TRAINER_ID  +
                              Const.TEAM_TABLE + "." + Const.CITY_ID + " = " + Const.CITY_TABLE + "." + Const.CITY_ID + ";" + "";
        System.out.println(query);
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Team team;
            while(rs.next()) {
                team = new Team(rs.getString(Const.STADIUM_NAME), rs.getString(Const.TRAINER_NAME), rs.getString(Const.CITY_NAME), rs.getString(Const.TEAM_NAME), rs.getInt(Const.TEAM_LAST_YEAR_RATING));
                //System.out.println(rs.getString(Const.CITY_NAME));
                teamList.add(team);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        /////////////////////////////////////////////////////
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ///////////////////////////////////////////////

        return teamList;
    }


    private void insertRecord() {
        if (Help.isFieldFill(tfStadium) && Help.isFieldFill(tfTrainer) && Help.isFieldFill(tfCity) && Help.isFieldFill(tfName) && Help.isFieldFill(tfLastRating)) {
            String query = "INSERT INTO " + Const.TEAM_TABLE + "(" + Const.STADIUM_ID + "," + Const.TRAINER_ID + "," + Const.CITY_ID + "," + Const.TEAM_NAME + "," + Const.TEAM_LAST_YEAR_RATING +")"
                    + " VALUES ('" + dbHandler.getIdFromName(tfStadium.getText()) + "' , '" + dbHandler.getIdFromName(tfTrainer.getText()) + "' , '" + dbHandler.getIdFromName(tfCity.getText()) + "' , '"
                    + tfName.getText() + "' , '" + tfLastRating.getText() + "')";
            dbHandler.executeQuery(query);
            showTeam();
            tfStadium.setText("");
            tfTrainer.setText("");
            tfCity.setText("");
            tfName.setText("");
            tfLastRating.setText("");


            ShowAlert.showAlertInformation("Результат", "Запись сделана!");
        } else {
            ShowAlert.showAlertInformation("Результат", "Заполните поле!");
        }
    }

    private void deleteButton() {
        String selectedItem = tvTeam.getSelectionModel().getSelectedItem().getTeam_name();
        System.out.println(selectedItem);
        String query = "DELETE FROM " + Const.TEAM_TABLE + " WHERE " + Const.TEAM_NAME + " = " + "'"+ selectedItem + "'" + ";";
        dbHandler.executeQuery(query);
        tfStadium.setText("");
        tfTrainer.setText("");
        tfCity.setText("");
        tfName.setText("");
        tfLastRating.setText("");
    }

    public void initDataSelectCityForTeam() {
        tfCity.setText(SelectCityController.getRes());
    }

    public void initDataSelectStadiumForTeam() {
        tfStadium.setText(SelectStadiumController.getRes());
    }

    public void initDataSelectTrainerForTeam() {
        tfTrainer.setText(SelectTrainerController.getRes());
    }

    public boolean isInitDataTeam() {
        return true;
    }

}

