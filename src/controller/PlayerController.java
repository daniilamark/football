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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Const;
import main.DBHandler;
import main.Help;
import main.ShowAlert;
import model.City;
import model.Player;
import model.Stadium;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class PlayerController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnSelectTeam;

    @FXML
    private TableColumn<Player, Integer> colAge;

    @FXML
    private TableColumn<Player, String> colFamily;

    @FXML
    private TableColumn<Player, String> colLastName;

    @FXML
    private TableColumn<Player, String> colName;

    @FXML
    private TableColumn<Player, Integer> colNumber;

    @FXML
    private TableColumn<Player, String> colRole;

    @FXML
    private TableColumn<Player, String> colTeam;

    @FXML
    private TextField tfAge;

    @FXML
    private TextField tfFamily;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfNumber;

    @FXML
    private TextField tfRole;

    @FXML
    private TextField tfTeam;

    @FXML
    private TableView<Player> tvPlayer;

    ObservableList<Player> playerList;

    DBHandler dbHandler = new DBHandler();
    MainController mainController = new MainController();

    private double x, y;

    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == btnInsert) {
            //cityController.getCityList();
            insertRecord();
        } else if (event.getSource() == btnDelete) {
            deleteButton();
        } else if (event.getSource() == btnSelectTeam) {
            Stage stage = new Stage();
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
            SelectTeamController.playerController = this;
            stage.show();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainController.setNumUI(1);
        showPlayer();
    }

    public void showPlayer() {
        ObservableList<Player> listPlayer = getPlayerList();

        colTeam.setCellValueFactory(new PropertyValueFactory<>(Const.TEAM_NAME));
        colFamily.setCellValueFactory(new PropertyValueFactory<Player, String>(Const.PLAYER_FAMILY));
        colName.setCellValueFactory(new PropertyValueFactory<Player, String>(Const.PLAYER_NAME));
        colLastName.setCellValueFactory(new PropertyValueFactory<Player, String>(Const.PLAYER_LAST_NAME));
        colAge.setCellValueFactory(new PropertyValueFactory<Player, Integer>(Const.PLAYER_AGE));
        colRole.setCellValueFactory(new PropertyValueFactory<Player, String>(Const.PLAYER_ROLE));
        colNumber.setCellValueFactory(new PropertyValueFactory<Player, Integer>(Const.PLAYER_NUMBER));

        tvPlayer.setItems((ObservableList<Player>) listPlayer);
    }


    public ObservableList<Player> getPlayerList() {
        playerList = FXCollections.observableArrayList();

        Connection conn = null;
        try {
            conn = dbHandler.getDBConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "SELECT " + Const.TEAM_NAME + ","+ Const.PLAYER_FAMILY + ","+ Const.PLAYER_NAME
                + "," + Const.PLAYER_LAST_NAME + "," + Const.PLAYER_AGE + "," + Const.PLAYER_ROLE + "," + Const.PLAYER_NUMBER +" FROM " + Const.TEAM_TABLE + "," + Const.PLAYER_TABLE
                + " WHERE " + Const.PLAYER_TABLE + "." + Const.PLAYER_TEAM_ID + " = " + Const.TEAM_TABLE + "." + Const.TEAM_ID + ";"+"";
        //System.out.println(query);
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Player player;
            while(rs.next()) {
                player = new Player(rs.getString(Const.TEAM_NAME), rs.getString(Const.PLAYER_FAMILY), rs.getString(Const.PLAYER_NAME),
                        rs.getString(Const.PLAYER_LAST_NAME), rs.getInt(Const.PLAYER_AGE), rs.getString(Const.PLAYER_ROLE), rs.getInt(Const.PLAYER_NUMBER));
                //System.out.println(rs.getString(Const.CITY_NAME));
                playerList.add(player);
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

        return playerList;
    }


    private void insertRecord(){
        // ПРОВЕРКА ВВОДА НУЖНА
        if (Help.isFieldFill(tfTeam) && Help.isFieldFill(tfFamily) && Help.isFieldFill(tfName)
                && Help.isFieldFill(tfLastName) && Help.isFieldFill(tfAge)
                && Help.isFieldFill(tfRole) && Help.isFieldFill(tfNumber)){
            String query = "INSERT INTO " + Const.PLAYER_TABLE + "("+ Const.PLAYER_TEAM_ID + "," + Const.PLAYER_FAMILY
                    + "," + Const.PLAYER_NAME + "," + Const.PLAYER_LAST_NAME + "," + Const.PLAYER_AGE + "," + Const.PLAYER_ROLE + "," + Const.PLAYER_NUMBER +")"
                    + " VALUES ('" + dbHandler.getIdFromName(tfTeam.getText(), "team") +"'" + ","+"'" +tfFamily.getText()+"'" +"," +"'" + tfName.getText()
                    +"'" +"," +"'" + tfLastName.getText()+"'"+"," +"'" + tfAge.getText()+ "'" +"," +"'" + tfRole.getText()
                    +"'" +"," +"'" + tfNumber.getText()+"')";
            System.out.println(query);
            dbHandler.executeQuery(query);
            showPlayer();
            tfTeam.setText("");
            tfFamily.setText("");
            tfName.setText("");
            tfLastName.setText("");
            tfAge.setText("");
            tfRole.setText("");
            tfNumber.setText("");
            ShowAlert.showAlertInformation("Результат",  "Запись сделана!");
        } else {
            ShowAlert.showAlertInformation("Результат",  "Заполните поле!");
        }

    }

    private void deleteButton() {
        int selectedItem = tvPlayer.getSelectionModel().getSelectedItem().getNumber();
        System.out.println(selectedItem);
        String query = "DELETE FROM " + Const.PLAYER_TABLE + " WHERE " + Const.PLAYER_NUMBER + " = " + "'"+ selectedItem + "'" + ";";
        System.out.println(query);
        dbHandler.executeQuery(query);
        showPlayer();

        tfTeam.setText("");
        tfFamily.setText("");
        tfName.setText("");
        tfLastName.setText("");
        tfAge.setText("");
        tfRole.setText("");
        tfNumber.setText("");
        btnDelete.setDisable(true);
    }

    public void initDataPlayerFromTeam() {
        tfTeam.setText(SelectTeamController.getRes());
    }

    public boolean isInitDataPlayerFromTeam() {
        return true;
    }

    public void clickItem(MouseEvent event) {
        btnDelete.setDisable(false);
    }


}

