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
import main.Help;
import main.ShowAlert;
import model.City;
import model.Trainer;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TrainerController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnInsert;

    @FXML
    private TableColumn<Trainer,String> colFamily;

    @FXML
    private TableColumn<Trainer, String> colLastName;

    @FXML
    private TableColumn<Trainer, String> colName;

    @FXML
    private TextField tfFamily;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfName;

    @FXML
    private TableView<Trainer> tvTrainer;

    ObservableList<Trainer> trainerList;

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
        showTrainer();
    }

    public void showTrainer() {
        ObservableList<Trainer> listTrainer = getTrainerList();

        colFamily.setCellValueFactory(new PropertyValueFactory<Trainer, String>(Const.TRAINER_FAMILY));
        colName.setCellValueFactory(new PropertyValueFactory<Trainer, String>(Const.TRAINER_NAME));
        colLastName.setCellValueFactory(new PropertyValueFactory<Trainer, String>(Const.TRAINER_LAST_NAME));

        tvTrainer.setItems(listTrainer);
    }

    public ObservableList<Trainer> getTrainerList() {
        trainerList = FXCollections.observableArrayList();

        Connection conn = null;
        try {
            conn = dbHandler.getDBConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "SELECT " + Const.TRAINER_ID + "," + Const.TRAINER_FAMILY + ","+ Const.TRAINER_NAME
                + "," + Const.TRAINER_LAST_NAME + " FROM " + Const.TRAINER_TABLE;

        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Trainer trainer;
            while(rs.next()) {
                trainer = new Trainer(rs.getInt(Const.TRAINER_ID), rs.getString(Const.TRAINER_FAMILY), rs.getString(Const.TRAINER_NAME), rs.getString(Const.TRAINER_LAST_NAME));

                trainerList.add(trainer);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return trainerList;
    }

    private void insertRecord(){
        if (Help.isFieldFill(tfFamily) && Help.isFieldFill(tfName) && Help.isFieldFill(tfLastName)){
            String query = "INSERT INTO " + Const.TRAINER_TABLE + "("+ Const.TRAINER_FAMILY + "," + Const.TRAINER_NAME + "," + Const.TRAINER_LAST_NAME +")"
                    + " VALUES ('" + tfFamily.getText() +"'" + ","+"'" +tfName.getText()+"'" +"," +"'" + tfLastName.getText()+"')";
            dbHandler.executeQuery(query);
            showTrainer();
            tfFamily.setText("");
            tfName.setText("");
            tfLastName.setText("");
            ShowAlert.showAlertInformation("Результат",  "Запись сделана!");
        } else {
            ShowAlert.showAlertInformation("Результат",  "Заполните поле!");
        }

    }

    private void deleteButton(){
        int selectedItem = tvTrainer.getSelectionModel().getSelectedItem().getTrainer_id();
        System.out.println(selectedItem);

        //String query = "SET SQL_SAFE_UPDATES = 0; DELETE FROM " + Const.CITY_TABLE + " WHERE "+ Const.CITY_NAME + " = " + "'" + tfCityName.getText() + "';" +  " ";
        String query = "DELETE FROM " + Const.TRAINER_TABLE + " WHERE " + Const.TRAINER_ID + " = " + "'"+ selectedItem + "'" + ";";
        dbHandler.executeQuery(query);
        showTrainer();
        tfFamily.setText("");
        tfName.setText("");
        tfLastName.setText("");
    }

}

