package controller;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.*;
import model.City;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CityController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<City, String> colName;

    @FXML
    private TextField tfCityName;

    @FXML
    private TableView<City> tvCity;


    ObservableList<City> cityList;

    DBHandler dbHandler = new DBHandler();

    @FXML
    void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnInsert){
            insertRecord();
        }else if(event.getSource() == btnDelete){
            deleteButton();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showCity();
    }



    public void showCity() {
        ObservableList<City> listCity = getCityList();
        colName.setCellValueFactory(new PropertyValueFactory<City, String>(Const.CITY_NAME));

        tvCity.setItems(listCity);
    }

    public ObservableList<City> getCityList(){
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

        /////////////////////////////////////////////////////
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ///////////////////////////////////////////////
        return cityList;
    }

    private void insertRecord(){
        if (Help.isFieldFill(tfCityName) && Help.isString(tfCityName.getText()) ){
            String query = "INSERT INTO " + Const.CITY_TABLE + "("+ Const.CITY_NAME +")" + " VALUES ('" + tfCityName.getText() + "')";
            dbHandler.executeQuery(query);
            showCity();
            tfCityName.setText("");
            ShowAlert.showAlertInformation("Результат",  "Запись сделана!");
        } else {
            ShowAlert.showAlertInformation("Результат",  "Заполните поле!");
        }

    }

    private void deleteButton(){
        String selectedItem = tvCity.getSelectionModel().getSelectedItem().getCity_name();
        System.out.println(selectedItem);

        //String query = "SET SQL_SAFE_UPDATES = 0; DELETE FROM " + Const.CITY_TABLE + " WHERE "+ Const.CITY_NAME + " = " + "'" + tfCityName.getText() + "';" +  " ";
        String query = "DELETE FROM " + Const.CITY_TABLE + " WHERE " + Const.CITY_NAME + " = " + "'"+ selectedItem + "'" + ";";

        dbHandler.executeQuery(query);
        showCity();
        tfCityName.setText("");
        btnDelete.setDisable(true);
    }
    public void clickItem(MouseEvent event) {
        btnDelete.setDisable(false);

    }


}

