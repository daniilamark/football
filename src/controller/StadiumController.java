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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
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
    private TextField tfCity ;

    @FXML
    private TextField tfTicketPrice;

    @FXML
    private TableView<Stadium> tvStadium;

    ObservableList<Stadium> stadiumList;

    DBHandler dbHandler = new DBHandler();
    CityController cityController = new CityController();

    private double x, y;

    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == btnInsert) {
            //cityController.getCityList();
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
            SelectCityController.stadiumController = this;
            stage.show();

        }
    }

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


    private void insertRecord(){
        if (Help.isFieldFill(tfCity) && Help.isFieldFill(tfStadiumName) && Help.isFieldFill(tfCapacity) && Help.isFieldFill(tfTicketPrice)){
            String query = "INSERT INTO " + Const.STADIUM_TABLE + "("+ Const.STADIUM_CITY_ID + "," + Const.STADIUM_NAME + "," + Const.STADIUM_CAPACITY + "," + Const.STADIUM_TICKET_PRICE +")"
                    + " VALUES ('" + dbHandler.getIdFromName(tfCity.getText()) +"'" + ","+"'" +tfStadiumName.getText()+"'" +"," +"'" + tfCapacity.getText()+"'" +"," +"'" + tfTicketPrice.getText()+"')";
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

    private void deleteButton() {
        String selectedItem = tvStadium.getSelectionModel().getSelectedItem().getStadium_name();
        System.out.println(selectedItem);
        String query = "DELETE FROM " + Const.STADIUM_TABLE + " WHERE " + Const.STADIUM_NAME + " = " + "'"+ selectedItem + "'" + ";";
        dbHandler.executeQuery(query);
        showStadium();
        tfCity.setText("");
        tfStadiumName.setText("");
        tfCapacity.setText("");
        tfTicketPrice.setText("");
    }

    public void initDataStadium() {
        tfCity.setText(SelectCityController.getRes());
    }
}

