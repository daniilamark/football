package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.Const;
import main.DBHandler;
import model.City;

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
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colScore;

    @FXML
    private TableColumn<?, ?> colTeam1;

    @FXML
    private TableColumn<?, ?> colTeam2;

    @FXML
    private DatePicker dpDate;

    @FXML
    private TextField tfScore;

    @FXML
    private TextField tfTeam1;

    @FXML
    private TextField tfTeam2;

    @FXML
    private TableView<?> tvMath;

    @FXML
    void handleButtonAction(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void clickItem(MouseEvent event) {
        btnDelete.setDisable(false);

    }
}

