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
import model.Team;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SelectTeamController implements Initializable {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnChoose;

    @FXML
    private TableColumn<Team, String> colName;

    @FXML
    private TableView<Team> tvSelectTeam;

    ObservableList<Team> teamList;

    DBHandler dbHandler = new DBHandler();

    private static String res1;
    private static String res2;

    public static TeamController teamController;
    public static MathController mathController;
    public static PlayerController playerController;
    public static MainController mainController;


    @FXML
    void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnCancel){
            btnCancel.getScene().getWindow().hide();
        }else if(event.getSource() == btnChoose){
            try {
                String selectedItem = tvSelectTeam.getSelectionModel().getSelectedItem().getTeam_name();
                //System.out.println(selectedItem);
                res1 = selectedItem;

                btnChoose.getScene().getWindow().hide();
                //stadiumController.initDataStadium();


                //mathController.initDataTeam2FromMath();
                playerController.initDataPlayerFromTeam();
                mathController.initDataTeam1FromMath();
                mathController.initDataTeam2FromMath();
                switch (mainController.getNumUI()) {
                    case 0:

                    case 3:
                        System.out.println(3); // math

                        switch (mathController.getNumTeam()){
                            case 1:
                                mathController.initDataTeam1FromMath();

                            case 2:
                                mathController.initDataTeam2FromMath();
                        }
                    case 4:
                        System.out.println(4); // player

                }



                //System.out.println(4);




            } catch (ExceptionInInitializerError e){
                /////////////////////// ВОЗМОЖНО ДРУГОЕ ИСКЛЧЕНИЕ НО ТЕПЕРЬ РАБОТАЕТ КАК_ТО
                ShowAlert.showAlertInformation("Результат",  "Выберите город!");
                System.out.println(e);
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showTeam();
    }

    public void showTeam() {
        ObservableList<Team> listTeam = getTeamList();

        colName.setCellValueFactory(new PropertyValueFactory<Team, String>(Const.TEAM_NAME));

        tvSelectTeam.setItems(listTeam);
    }

    public ObservableList<Team> getTeamList() {
        teamList = FXCollections.observableArrayList();

        Connection conn = null;
        try {
            conn = dbHandler.getDBConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "SELECT " + Const.TEAM_ID + ","+ Const.TEAM_NAME + " FROM " + Const.TEAM_TABLE;

        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Team team;
            while(rs.next()) {
                team = new Team(rs.getInt(Const.TEAM_ID), rs.getString(Const.TEAM_NAME));

                teamList.add(team);
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
        return teamList;
    }

    public static String getRes1(){
        return res1;
    }
    public static String getRes2(){
        return res2;
    }

}
