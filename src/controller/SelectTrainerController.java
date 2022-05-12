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
import model.Stadium;
import model.Trainer;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SelectTrainerController implements Initializable {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnChoose;

    @FXML
    private TableColumn<Trainer, String> colTrainerName;

    @FXML
    private TableView<Trainer> tvSelectTrainer;

    ObservableList<Trainer> trainerList;

    DBHandler dbHandler = new DBHandler();

    private static String res;

    public static TeamController teamController;


    @FXML
    void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnCancel){
            btnCancel.getScene().getWindow().hide();
        }else if(event.getSource() == btnChoose){
            try {
                String selectedItem = tvSelectTrainer.getSelectionModel().getSelectedItem().getName();
                //System.out.println(selectedItem);
                res = selectedItem;

                btnChoose.getScene().getWindow().hide();

                teamController.initDataSelectTrainerForTeam();

            } catch (Exception e){
                ShowAlert.showAlertInformation("Результат",  "Выберите тренера!");
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showTrainer();
    }

    public void showTrainer() {
        ObservableList<Trainer> listTrainer = getTrainerList();

        colTrainerName.setCellValueFactory(new PropertyValueFactory<Trainer, String>(Const.TRAINER_NAME));

        tvSelectTrainer.setItems(listTrainer);
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

        String query = "SELECT " + Const.TRAINER_ID + ","+ Const.TRAINER_NAME + " FROM " + Const.TRAINER_TABLE;

        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Trainer trainer;
            while(rs.next()) {
                trainer = new Trainer(rs.getInt(Const.TRAINER_ID), rs.getString(Const.TRAINER_NAME));

                trainerList.add(trainer);
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
        return trainerList;
    }

    public static String getRes(){
        return res;
    }

}
