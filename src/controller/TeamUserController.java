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
import model.Team;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TeamUserController implements Initializable {

    @FXML private TableColumn<Team, String> colCity;

    @FXML private TableColumn<Team, Integer> colLastRating;

    @FXML private TableColumn<Team, String> colName;

    @FXML private TableColumn<Team, String> colStadium;

    @FXML private TableColumn<Team, String> colTrainer;

    @FXML private TableView<Team> tvTeam;

    ObservableList<Team> teamList;

    DBHandler dbHandler = new DBHandler();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showTeam();
    }

    public void showTeam() {
        ObservableList<Team> listTeam = getTeamList();

        colStadium.setCellValueFactory(new PropertyValueFactory<>(Const.STADIUM_NAME));
        colTrainer.setCellValueFactory(new PropertyValueFactory<>(Const.TRAINER_NAME));
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
                + " WHERE " + Const.TEAM_TABLE + "." + Const.STADIUM_ID + " = " + Const.STADIUM_TABLE + "." + Const.STADIUM_ID + " AND " // ; ????
                            +  Const.TEAM_TABLE + "." + Const.TRAINER_ID + " = " + Const.TRAINER_TABLE + "." + Const.TRAINER_ID  + " AND "
                            +  Const.TEAM_TABLE + "." + Const.CITY_ID + " = " + Const.CITY_TABLE + "." + Const.CITY_ID + ";" + "";
        //System.out.println(query);
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Team team;
            while(rs.next()) {
                team = new Team(rs.getString(Const.STADIUM_NAME), rs.getString(Const.TRAINER_NAME), rs.getString(Const.CITY_NAME), rs.getString(Const.TEAM_NAME), rs.getInt(Const.TEAM_LAST_YEAR_RATING));
                teamList.add(team);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        ///////////////////////////////////////////////
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ///////////////////////////////////////////////

        return teamList;
    }
}

