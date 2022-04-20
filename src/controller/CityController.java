package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.City;

public class CityController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<City, Integer> colId;

    @FXML
    private TableColumn<City, String> colName;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfTitle;

    @FXML
    private TableView<City> tvCity;

    @FXML
    void handleButtonAction(ActionEvent event) {

    }

}

