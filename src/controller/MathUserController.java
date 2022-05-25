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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Const;
import main.DBHandler;
import main.Help;
import main.ShowAlert;
import model.Math;
import model.Math1;
import model.Math2;
import model.Math3;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MathUserController implements Initializable {


    @FXML private TableColumn<Math3, String> colData;

    @FXML private TableColumn<Math2, String> colScore;

    @FXML private TableColumn<Math, String> colTeam1;

    @FXML private TableColumn<Math1, String> colTeam2;


    @FXML private TableView<Math3> tvMathData;

    @FXML private TableView<Math2> tvMathScore;

    @FXML private TableView<Math> tvMathTeam1;

    @FXML private TableView<Math1> tvMathTeam2;

    ObservableList<Math> team1List;
    ObservableList<Math1> team2List;
    ObservableList<Math2> scoreList;
    ObservableList<Math3> dateList;

    DBHandler dbHandler = new DBHandler();
    TeamController teamController = new TeamController();
    MainController mainController = new MainController();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainController.setNumUI(1);
        showTeam1();
        showTeam2();
        showScore();
        showDate();
    }

    public void showTeam1() {
        ObservableList<Math> listTeam1 = getTeam1();

        colTeam1.setCellValueFactory(new PropertyValueFactory<>("team_name1"));
        //colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        //colScore.setCellValueFactory(new PropertyValueFactory<>("score"));

        tvMathTeam1.setItems((ObservableList<Math>) listTeam1);
    }

    public void showTeam2() {
        ObservableList<Math1> listTeam2 = getTeam2();

        colTeam2.setCellValueFactory(new PropertyValueFactory<>("team_name2"));
        //colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        //colScore.setCellValueFactory(new PropertyValueFactory<>("score"));

        tvMathTeam2.setItems((ObservableList<Math1>) listTeam2);
    }

    public void showScore() {
        ObservableList<Math2> listScore = getScore();

        //colTeam2.setCellValueFactory(new PropertyValueFactory<>("team_name2"));
        //colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colScore.setCellValueFactory(new PropertyValueFactory<>("score"));

        tvMathScore.setItems((ObservableList<Math2>) listScore);
    }

    public void showDate() {
        ObservableList<Math3> listDate = getDate();

        colData.setCellValueFactory(new PropertyValueFactory<>("date"));

        tvMathData.setItems((ObservableList<Math3>) listDate);
    }


    public ObservableList<Math> getTeam1() {
        team1List = FXCollections.observableArrayList();

        Connection conn = null;
        try {
            conn = dbHandler.getDBConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String query = "SELECT " + Const.TEAM_NAME + " FROM " + Const.TEAM_TABLE +","+ Const.MATH_TABLE +""
                    + " WHERE " + Const.TEAM_TABLE + "." + Const.TEAM_ID + " = " + Const.MATH_TABLE + "." + Const.MATH_TEAM_ID_1 + "";
            //+ Const.TEAM_TABLE + "." + Const.TEAM_ID + " = " + Const.MATH_TABLE + "." + Const.MATH_TEAM_ID_1 +";"+"";
            System.out.println(query);
            Statement st;
            ResultSet rs;
            try {
                st = conn.createStatement();
                rs = st.executeQuery(query);
                Math math;
                while (rs.next()) {
                    math = new Math(rs.getString(Const.TEAM_NAME));
                    team1List.add(math);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }catch (Exception e){

        }
        /////////////////////////////////////////////////////
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ///////////////////////////////////////////////

        return team1List;
    }


    public ObservableList<Math1> getTeam2() {
        team2List = FXCollections.observableArrayList();

        Connection conn = null;
        try {
            conn = dbHandler.getDBConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String query = "SELECT " + Const.TEAM_NAME + " FROM " + Const.TEAM_TABLE +","+ Const.MATH_TABLE +""
                    + " WHERE " + Const.TEAM_TABLE + "." + Const.TEAM_ID + " = " + Const.MATH_TABLE + "." + Const.MATH_TEAM_ID_2 + "";
            //+ Const.TEAM_TABLE + "." + Const.TEAM_ID + " = " + Const.MATH_TABLE + "." + Const.MATH_TEAM_ID_2 +";"+"";
            System.out.println(query);
            Statement st;
            ResultSet rs;
            try {
                st = conn.createStatement();
                rs = st.executeQuery(query);
                Math1 math2;
                while (rs.next()) {
                    math2 = new Math1(rs.getString(Const.TEAM_NAME));
                    team2List.add(math2);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }catch (Exception e){

        }
        /////////////////////////////////////////////////////
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ///////////////////////////////////////////////

        return team2List;
    }


    public ObservableList<Math2> getScore() {
        scoreList = FXCollections.observableArrayList();

        Connection conn = null;
        try {
            conn = dbHandler.getDBConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String query = "SELECT " + Const.MATH_SCORE + " FROM " + Const.MATH_TABLE +"";
            //+ " WHERE " + Const.TEAM_TABLE + "." + Const.TEAM_ID + " = " + Const.MATH_TABLE + "." + Const.MATH_TEAM_ID_1 + "";
            //+ Const.TEAM_TABLE + "." + Const.TEAM_ID + " = " + Const.MATH_TABLE + "." + Const.MATH_TEAM_ID_1 +";"+"";
            System.out.println(query);
            Statement st;
            ResultSet rs;
            try {
                st = conn.createStatement();
                rs = st.executeQuery(query);
                Math2 math3;
                while (rs.next()) {
                    math3 = new Math2(rs.getString(Const.MATH_SCORE));
                    scoreList.add(math3);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }catch (Exception e){

        }
        /////////////////////////////////////////////////////
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ///////////////////////////////////////////////

        return scoreList;
    }



    public ObservableList<Math3> getDate() {
        dateList = FXCollections.observableArrayList();

        Connection conn = null;
        try {
            conn = dbHandler.getDBConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String query = "SELECT " + Const.MATH_DATE + " FROM " + Const.MATH_TABLE +"";
            //+ " WHERE " + Const.TEAM_TABLE + "." + Const.TEAM_ID + " = " + Const.MATH_TABLE + "." + Const.MATH_TEAM_ID_1 + "";
            //+ Const.TEAM_TABLE + "." + Const.TEAM_ID + " = " + Const.MATH_TABLE + "." + Const.MATH_TEAM_ID_1 +";"+"";
            System.out.println(query);
            Statement st;
            ResultSet rs;
            try {
                st = conn.createStatement();
                rs = st.executeQuery(query);
                Math3 math4;
                while (rs.next()) {
                    math4 = new Math3(rs.getString(Const.MATH_DATE));
                    dateList.add(math4);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }catch (Exception e){

        }
        /////////////////////////////////////////////////////
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ///////////////////////////////////////////////

        return dateList;
    }
}