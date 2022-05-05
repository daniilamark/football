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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Const;
import main.DBHandler;
import main.Help;
import main.ShowAlert;
import model.City;
import model.Stadium;
import model.Trainer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class StadiumController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnSelectCity;

    @FXML
    private TableColumn<Stadium, Integer> colCapacity;

    @FXML
    private TableColumn<Stadium, String> colStadiumName;

    @FXML
    private TableColumn<Stadium, Integer> colTicketPrice;

    @FXML
    private TableColumn<Stadium, String> colCity;

    @FXML
    private TextField tfCapacity;

    @FXML
    private TextField tfStadiumName;

    @FXML
    private TextField tfCity;

    @FXML
    private TextField tfTicketPrice;

    @FXML
    private TableView<Stadium> tvStadium;

    ObservableList<Stadium> stadiumList;

    DBHandler dbHandler = new DBHandler();

    private double x, y;

    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        if(event.getSource() == btnInsert){
            insertRecord();
        }else if(event.getSource() == btnDelete){
            deleteButton();
        }else if(event.getSource() == btnSelectCity){
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../views/select_city.fxml"));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root));


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
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showStadium();
    }

    public void showStadium() {
        ObservableList<Stadium> listStadium = getStadiumList();

        colCity.setCellValueFactory(new PropertyValueFactory<Stadium, String>(Const.STADIUM_ID));
        colStadiumName.setCellValueFactory(new PropertyValueFactory<Stadium, String>(Const.STADIUM_NAME));
        colCapacity.setCellValueFactory(new PropertyValueFactory<Stadium, Integer>(Const.STADIUM_CAPACITY));
        colTicketPrice.setCellValueFactory(new PropertyValueFactory<Stadium, Integer>(Const.STADIUM_TICKET_PRICE));

        tvStadium.setItems(listStadium);
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

        String query = "SELECT " + Const.STADIUM_ID + "," + Const.STADIUM_CITY_ID + ","+ Const.STADIUM_NAME + ","+ Const.STADIUM_CAPACITY
                + "," + Const.STADIUM_TICKET_PRICE + " FROM " + Const.STADIUM_TABLE;

        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Stadium stadium;
            while(rs.next()) {
                stadium = new Stadium(rs.getInt(Const.STADIUM_ID),rs.getInt(Const.STADIUM_CITY_ID), rs.getString(Const.STADIUM_NAME), rs.getInt(Const.STADIUM_CAPACITY), rs.getInt(Const.STADIUM_TICKET_PRICE));

                stadiumList.add(stadium);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return stadiumList;
    }

    private void insertRecord(){
        if (Help.isFieldFill(tfCity) && Help.isFieldFill(tfStadiumName) && Help.isFieldFill(tfCapacity) && Help.isFieldFill(tfTicketPrice)){
            String query = "INSERT INTO " + Const.STADIUM_TABLE + "("+ Const.STADIUM_CITY_ID + "," + Const.STADIUM_NAME + "," + Const.STADIUM_CAPACITY + "," + Const.STADIUM_TICKET_PRICE +")"
                    + " VALUES ('" + tfCity.getText() +"'" + ","+"'" +tfStadiumName.getText()+"'" +"," +"'" + tfCapacity.getText()+"'" +"," +"'" + tfTicketPrice.getText()+"')";
            dbHandler.executeQuery(query);
            showStadium();
            tfCity.setText("");
            tfStadiumName.setText("");
            tfCapacity.setText("");
            tfTicketPrice.setText("");
            ShowAlert.showAlertInformation("Результат",  "Запись сделана!");
        } else {
            ShowAlert.showAlertInformation("Результат",  "Заполните поле!");
        }

    }

    private void deleteButton(){
        int selectedItem = tvStadium.getSelectionModel().getSelectedItem().getStadium_id();
        System.out.println(selectedItem);

        //String query = "SET SQL_SAFE_UPDATES = 0; DELETE FROM " + Const.CITY_TABLE + " WHERE "+ Const.CITY_NAME + " = " + "'" + tfCityName.getText() + "';" +  " ";
        String query = "DELETE FROM " + Const.STADIUM_TABLE + " WHERE " + Const.STADIUM_ID + " = " + "'"+ selectedItem + "'" + ";";
        dbHandler.executeQuery(query);
        tfCity.setText("");
        tfStadiumName.setText("");
        tfCapacity.setText("");
        tfTicketPrice.setText("");
    }



}

