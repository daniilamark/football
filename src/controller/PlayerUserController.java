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
import model.Player;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class PlayerUserController implements Initializable {

    @FXML private TableColumn<Player, Integer> colAge;

    @FXML private TableColumn<Player, String> colFamily;

    @FXML private TableColumn<Player, String> colLastName;

    @FXML private TableColumn<Player, String> colName;

    @FXML private TableColumn<Player, Integer> colNumber;

    @FXML private TableColumn<Player, String> colRole;

    @FXML private TableColumn<Player, String> colTeam;

    @FXML private TableView<Player> tvPlayer;

    ObservableList<Player> playerList;

    DBHandler dbHandler = new DBHandler();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showPlayer();
    }

    public void showPlayer() {
        ObservableList<Player> listPlayer = getPlayerList();

        colTeam.setCellValueFactory(new PropertyValueFactory<>(Const.TEAM_NAME));
        colFamily.setCellValueFactory(new PropertyValueFactory<Player, String>(Const.PLAYER_FAMILY));
        colName.setCellValueFactory(new PropertyValueFactory<Player, String>(Const.PLAYER_NAME));
        colLastName.setCellValueFactory(new PropertyValueFactory<Player, String>(Const.PLAYER_LAST_NAME));
        colAge.setCellValueFactory(new PropertyValueFactory<Player, Integer>(Const.PLAYER_AGE));
        colRole.setCellValueFactory(new PropertyValueFactory<Player, String>(Const.PLAYER_ROLE));
        colNumber.setCellValueFactory(new PropertyValueFactory<Player, Integer>(Const.PLAYER_NUMBER));

        tvPlayer.setItems((ObservableList<Player>) listPlayer);
    }


    public ObservableList<Player> getPlayerList() {
        playerList = FXCollections.observableArrayList();

        Connection conn = null;
        try {
            conn = dbHandler.getDBConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "SELECT " + Const.TEAM_NAME + ","+ Const.PLAYER_FAMILY + ","+ Const.PLAYER_NAME
                + "," + Const.PLAYER_LAST_NAME + "," + Const.PLAYER_AGE + "," + Const.PLAYER_ROLE + "," + Const.PLAYER_NUMBER +" FROM " + Const.TEAM_TABLE + "," + Const.PLAYER_TABLE
                + " WHERE " + Const.PLAYER_TABLE + "." + Const.PLAYER_TEAM_ID + " = " + Const.TEAM_TABLE + "." + Const.TEAM_ID + ";"+"";
        //System.out.println(query);
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Player player;
            while(rs.next()) {
                player = new Player(rs.getString(Const.TEAM_NAME), rs.getString(Const.PLAYER_FAMILY), rs.getString(Const.PLAYER_NAME),
                        rs.getString(Const.PLAYER_LAST_NAME), rs.getInt(Const.PLAYER_AGE), rs.getString(Const.PLAYER_ROLE), rs.getInt(Const.PLAYER_NUMBER));
                //System.out.println(rs.getString(Const.CITY_NAME));
                playerList.add(player);
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

        return playerList;
    }
}

