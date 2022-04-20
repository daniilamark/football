package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Const;
import main.DBHandler;
import model.City;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private TextField tfTitle;

    @FXML
    private TableView<City> tvCity;


    DBHandler dbHandler = new DBHandler();
    @FXML
    void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnInsert){
            insertRecord();
        }else if (event.getSource() == btnUpdate){
            updateRecord();
        }else if(event.getSource() == btnDelete){
            deleteButton();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showCity();
    }

    private void executeQuery(String query) {
        Connection conn = null;
        try {
            conn = dbHandler.getDBConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void showCity() {
        ObservableList<City> listCity = getCityList();

        colName.setCellValueFactory(new PropertyValueFactory<City, String>(Const.CITY_NAME));

        tvCity.setItems(listCity);
    }

    public ObservableList<City> getCityList() {
        ObservableList<City> cityList = FXCollections.observableArrayList();

        Connection conn = null;
        try {
            conn = dbHandler.getDBConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "SELECT" + Const.CITY_NAME + "FROM" + Const.CITY_TABLE;

        Statement st;
        ResultSet rs;
        //System.out.println("good12");
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            City city;
            while(rs.next()) {
                city = new City(rs.getString(Const.CITY_NAME));

                cityList.add(city);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return cityList;
    }

    private void insertRecord(){
        String query = "INSERT INTO city VALUES ('" + tfTitle.getText() +"')";
        executeQuery(query);
        showCity();
    }

    private void updateRecord() {
        String query = "UPDATE city SET city_name  = '" + tfTitle.getText() + "'";
        executeQuery(query);
        showCity();
    }

    private void deleteButton(){
        String query = "DELETE FROM city WHERE city_name = " + tfTitle.getText() + "";
        executeQuery(query);
        showCity();
    }

}

