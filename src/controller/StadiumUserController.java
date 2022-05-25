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
import model.Stadium;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class StadiumUserController implements Initializable {


    @FXML private TableColumn<Stadium, Integer> colCapacity;

    @FXML private TableColumn<Stadium, String> colStadiumName;

    @FXML private TableColumn<Stadium, Integer> colTicketPrice;

    @FXML private TableColumn<Stadium, String> colCity;

    @FXML
     TableView<Stadium> tvStadium;

    ObservableList<Stadium> stadiumList;

    DBHandler dbHandler = new DBHandler();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showStadium();
    }

    public void showStadium() {
        ObservableList<Stadium> listStadium = getStadiumList();

        colCity.setCellValueFactory(new PropertyValueFactory<>(Const.CITY_NAME));
        colStadiumName.setCellValueFactory(new PropertyValueFactory<Stadium, String>(Const.STADIUM_NAME));
        colCapacity.setCellValueFactory(new PropertyValueFactory<Stadium, Integer>(Const.STADIUM_CAPACITY));
        colTicketPrice.setCellValueFactory(new PropertyValueFactory<Stadium, Integer>(Const.STADIUM_TICKET_PRICE));

        tvStadium.setItems((ObservableList<Stadium>) listStadium);
    }


    public ObservableList<Stadium> getStadiumList() {
        stadiumList = FXCollections.observableArrayList();

        Connection conn = null;
        try {
            conn = dbHandler.getDBConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "SELECT " + Const.CITY_NAME + ","+ Const.STADIUM_NAME + ","+ Const.STADIUM_CAPACITY
                + "," + Const.STADIUM_TICKET_PRICE + " FROM " + Const.STADIUM_TABLE + "," + Const.CITY_TABLE
                + " WHERE " + Const.STADIUM_TABLE + "." + Const.STADIUM_CITY_ID + " = " + Const.CITY_TABLE+ "." + Const.CITY_ID + ";"+"";
        System.out.println(query);
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Stadium stadium;
            while(rs.next()) {
                stadium = new Stadium(rs.getString(Const.CITY_NAME), rs.getString(Const.STADIUM_NAME), rs.getInt(Const.STADIUM_CAPACITY), rs.getInt(Const.STADIUM_TICKET_PRICE));
                //System.out.println(rs.getString(Const.CITY_NAME));
                stadiumList.add(stadium);
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

        return stadiumList;
    }

}

