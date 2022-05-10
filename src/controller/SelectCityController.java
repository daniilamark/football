package controller;

import javafx.application.Platform;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.Const;
import main.DBHandler;
import main.ShowAlert;
import model.City;

import java.io.IOException;
import java.lang.constant.Constable;
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

    private static String res;

    public static StadiumController stadiumController;


    @FXML
    void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnCancel){
            btnCancel.getScene().getWindow().hide();
        }else if(event.getSource() == btnChoose){
            try {
                String selectedItem = tvSelectCity.getSelectionModel().getSelectedItem().getCity_name();
                //System.out.println(selectedItem);
                res = selectedItem;
                int a = dbHandler.getIdFromName(res);
                btnChoose.getScene().getWindow().hide();
                stadiumController.initData();

            } catch (Exception e){
                ShowAlert.showAlertInformation("Результат",  "Выберите город!");
            }
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


    public static String getRes(){
        return res;
    }

    /*
    @FXML
    void clickBtnCancel(ActionEvent event) {
        btnCancel.getScene().getWindow().hide();
    }

    @FXML
    void clickBtnChoose(ActionEvent event) {
        String selectedItem = tvSelectCity.getSelectionModel().getSelectedItem().getCity_name();
        //System.out.println(selectedItem);
        res = selectedItem;
        btnChoose.getScene().getWindow().hide();
        stadiumController.initData();


        try {
            String selectedItem = tvSelectCity.getSelectionModel().getSelectedItem().getCity_name();
            //System.out.println(selectedItem);
            res = selectedItem;
            btnChoose.getScene().getWindow().hide();
            controller1.sendToFirst(res);

        } catch (Exception e){
            ShowAlert.showAlertInformation("Результат",  "Выберите город!");
        }


    }

     */


}
