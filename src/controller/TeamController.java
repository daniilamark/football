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

public class TeamController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnInsert;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colLastRating;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colStadium;

    @FXML
    private TableColumn<?, ?> colTrainer;

    @FXML
    private TextField tfCity;

    @FXML
    private TextField tfLastRating;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfStadium;

    @FXML
    private TextField tfTrainer;

    @FXML
    private TableView<?> tvTeam;

    @FXML
    void handleButtonAction(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}

