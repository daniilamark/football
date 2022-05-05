package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.Const;
import main.DBHandler;
import model.City;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SelectCityController implements Initializable {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnChoose;

    @FXML
    private TableColumn<City, String> colName;

    @FXML
    private TableView<City> tvSelectCity;

    ObservableList<City> cityList;

    DBHandler dbHandler = new DBHandler();

    @FXML
    void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnCancel){
            btnCancel.getScene().getWindow().hide();
        }else if(event.getSource() == btnChoose){

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCity();
    }

    public void showCity() {
        ObservableList<City> listCity = getCityList();

        colName.setCellValueFactory(new PropertyValueFactory<City, String>(Const.CITY_NAME));

        tvSelectCity.setItems(listCity);
    }

    public ObservableList<City> getCityList() {
        cityList = FXCollections.observableArrayList();

        Connection conn = null;
        try {
            conn = dbHandler.getDBConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "SELECT " + Const.CITY_ID + ","+ Const.CITY_NAME + " FROM " + Const.CITY_TABLE;

        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            City city;
            while(rs.next()) {
                city = new City(rs.getInt(Const.CITY_ID), rs.getString(Const.CITY_NAME));

                cityList.add(city);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return cityList;
    }


}
