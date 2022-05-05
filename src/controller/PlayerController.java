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

public class PlayerController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnInsert;

    @FXML
    private TableColumn<?, ?> colAge;

    @FXML
    private TableColumn<?, ?> colFamily;

    @FXML
    private TableColumn<?, ?> colLastName;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNumber;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private TableColumn<?, ?> colTeam;

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
    private TableView<?> tvPlayer;

    @FXML
    void handleButtonAction(ActionEvent event) {

    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}

