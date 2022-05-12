package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Const;
import main.DBHandler;
import main.ShowAlert;
import model.City;
import model.Stadium;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SelectStadiumController implements Initializable {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnChoose;

    @FXML
    private TableColumn<Stadium, String> colStadiumName;

    @FXML
    private TableView<Stadium> tvSelectStadium;

    ObservableList<Stadium> stadiumsList;

    DBHandler dbHandler = new DBHandler();

    private static String res;

    public static TeamController teamController;


    @FXML
    void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnCancel){
            btnCancel.getScene().getWindow().hide();
        }else if(event.getSource() == btnChoose){
            try {
                String selectedItem = tvSelectStadium.getSelectionModel().getSelectedItem().getStadium_name();
                //System.out.println(selectedItem);
                res = selectedItem;

                btnChoose.getScene().getWindow().hide();

                teamController.initDataSelectStadiumForTeam();

            } catch (Exception e){
                ShowAlert.showAlertInformation("Результат",  "Выберите стадион!");
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showStadium();
    }

    public void showStadium() {
        ObservableList<Stadium> listStadium = getStadiumList();

        colStadiumName.setCellValueFactory(new PropertyValueFactory<Stadium, String>(Const.STADIUM_NAME));

        tvSelectStadium.setItems(listStadium);
    }

    public ObservableList<Stadium> getStadiumList() {
        stadiumsList = FXCollections.observableArrayList();

        Connection conn = null;
        try {
            conn = dbHandler.getDBConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "SELECT " + Const.STADIUM_ID + ","+ Const.STADIUM_NAME + " FROM " + Const.STADIUM_TABLE;

        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Stadium stadium;
            while(rs.next()) {
                stadium = new Stadium(rs.getInt(Const.STADIUM_ID), rs.getString(Const.STADIUM_NAME));

                stadiumsList.add(stadium);
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
        return stadiumsList;
    }

    public static String getRes(){
        return res;
    }

}
