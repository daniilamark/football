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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Const;
import main.DBHandler;
import main.Help;
import main.ShowAlert;
import model.City;
import model.Math;
import model.Stadium;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MathController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnInsert;
    
    @FXML
    private Button btnSelectTeam1;

    @FXML
    private Button btnSelectTeam2;

    @FXML
    private TableColumn<Math, String> colDate;

    @FXML
    private TableColumn<Math, Integer> colScore;

    @FXML
    private TableColumn<Math, String> colTeam1;

    @FXML
    private TableColumn<Math, String> colTeam2;

    @FXML
    private DatePicker dpDate;

    @FXML
    private TextField tfScore;

    @FXML
    private TextField tfTeam1;

    @FXML
    private TextField tfTeam2;

    @FXML
    private TableView<Math> tvMath;

    ObservableList<Math> mathList1;
    ObservableList<Math> mathList2;

    DBHandler dbHandler = new DBHandler();
    TeamController teamController = new TeamController();
    MainController mainController = new MainController();

    private double x, y;
    private int numTeam = 0;
    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == btnInsert) {
            //cityController.getCityList();
            insertRecord();
        } else if (event.getSource() == btnDelete) {
            deleteButton();
        } else if (event.getSource() == btnSelectTeam1) {
            Stage stage = new Stage();
            setNumTeam(1);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/select_team.fxml"));
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
            SelectTeamController.mathController = this;
            stage.show();
        }

        else if (event.getSource() == btnSelectTeam2) {
            Stage stage = new Stage();
            setNumTeam(2);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/select_team.fxml"));
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
            SelectTeamController.mathController = this;
            stage.show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainController.setNumUI(1);
        showMath1();
        //showMath2();
    }

    public void showMath1() {
        ObservableList<Math> listMath1 = getMathList1();

        colTeam1.setCellValueFactory(new PropertyValueFactory<Math, String>("team_name1"));


        tvMath.setItems((ObservableList<Math>) listMath1);
    }
    public void showMath2() {
        ObservableList<Math> listMath2 = getMathList2();

        colTeam2.setCellValueFactory(new PropertyValueFactory<>("team_name2"));
        colDate.setCellValueFactory(new PropertyValueFactory<Math, String>(Const.MATH_DATE));
        colScore.setCellValueFactory(new PropertyValueFactory<Math, Integer>(Const.MATH_SCORE));

        tvMath.setItems((ObservableList<Math>) listMath2);
    }

    public ObservableList<Math> getMathList1() {
        mathList1 = FXCollections.observableArrayList();

        Connection conn = null;
        try {
            conn = dbHandler.getDBConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "SELECT " + Const.TEAM_NAME + " FROM " + Const.TEAM_TABLE +","+ Const.MATH_TABLE +""
                + " WHERE " + Const.TEAM_TABLE + "." + Const.TEAM_ID + " = " + Const.MATH_TABLE + "." + Const.MATH_TEAM_ID_1 + "";
                //+ Const.TEAM_TABLE + "." + Const.TEAM_ID + " = " + Const.MATH_TABLE + "." + Const.MATH_TEAM_ID_2 +";"+"";
        System.out.println(query);
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Math math;
            while(rs.next()) {
                math = new Math(rs.getString(Const.TEAM_NAME));
                //System.out.println(rs.getString(Const.TEAM_NAME));
                mathList1.add(math);
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

        return mathList1;
    }
    public ObservableList<Math> getMathList2() {
        mathList2 = FXCollections.observableArrayList();

        Connection conn = null;
        try {
            conn = dbHandler.getDBConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "SELECT " + Const.TEAM_NAME +","+ Const.MATH_DATE +","+ Const.MATH_SCORE + " FROM " + Const.TEAM_TABLE +","+ Const.MATH_TABLE +""
                + " WHERE " + Const.TEAM_TABLE + "." + Const.TEAM_ID + " = " + Const.MATH_TABLE + "." + Const.MATH_TEAM_ID_2 + "";
        //+ Const.TEAM_TABLE + "." + Const.TEAM_ID + " = " + Const.MATH_TABLE + "." + Const.MATH_TEAM_ID_2 +";"+"";
        System.out.println(query);
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Math math;
            while(rs.next()) {
                math = new Math(rs.getString(Const.TEAM_NAME),rs.getString(Const.MATH_DATE),rs.getInt(Const.MATH_SCORE));
                //System.out.println(rs.getString(Const.MATH_SCORE));
                mathList2.add(math);
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

        return mathList2;
    }




    private void insertRecord(){
        if (Help.isFieldFill(tfTeam1) && Help.isFieldFill(tfTeam2) && Help.isFieldFill(tfScore) && Help.isFieldFill(dpDate)){
            String query = "INSERT INTO " + Const.MATH_TABLE + "("+ Const.MATH_TEAM_ID_1 + "," + Const.MATH_TEAM_ID_2 + "," + Const.MATH_DATE + "," + Const.MATH_SCORE +")"
                    + " VALUES ('" + dbHandler.getIdFromName(tfTeam1.getText(), "team") +"'" + ","+"'" + dbHandler.getIdFromName(tfTeam2.getText(), "team") +"'" +"," +"'" + dpDate.getPromptText()+"'" +"," +"'" + tfScore.getText()+"')";
            dbHandler.executeQuery(query);
            showMath1();
            showMath2();
            tfTeam1.setText("");
            tfTeam2.setText("");
            dpDate.setPromptText("");
            tfScore.setText("");
            ShowAlert.showAlertInformation("Результат",  "Запись сделана!");
        } else {
            ShowAlert.showAlertInformation("Результат",  "Заполните поле!");
        }

    }

    private void deleteButton() {
        int selectedItem = tvMath.getSelectionModel().getSelectedItem().getMath_id();
        System.out.println(selectedItem);
        String query = "DELETE FROM " + Const.MATH_TABLE + " WHERE " + Const.MATH_ID + " = " + "'"+ selectedItem + "'" + ";";
        dbHandler.executeQuery(query);
        showMath1();
        showMath2();
        tfTeam1.setText("");
        tfTeam2.setText("");
        dpDate.setPromptText("");
        tfScore.setText("");
        btnDelete.setDisable(true);
    }

    public void initDataTeam1FromMath() {
        tfTeam1.setText(SelectTeamController.getRes());
    }


    public void initDataTeam2FromMath() {
        tfTeam2.setText(SelectTeamController.getRes());
    }

    public void setNumTeam(int num) {
        this.numTeam = num;
    }

    public int getNumTeam() {
        return numTeam;
    }


    public void clickItem(MouseEvent event) {
        btnDelete.setDisable(false);
    }
}

