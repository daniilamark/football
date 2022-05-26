package controller;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Const;
import main.DBHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AddAdminController implements Initializable {
    @FXML private Button btnClose;

    @FXML private Label errorMessageLabel;

    @FXML private ImageView btnShowPassword;

    @FXML private Button btnLogin;

    @FXML private PasswordField pfPassword;

    @FXML private TextField tfUsername;

    @FXML private Label lUser;
    DBHandler dbHandler = new DBHandler();

    private String errorMessage = "";

    public String USERNAME;
    public String PASSWORD;
    public boolean accessIsAllowed = false;

    public boolean isFieldFilled() {
        boolean isFilled = true;
        if (tfUsername.getText().isEmpty()){
            isFilled = false;
            errorMessage = "Введите логин!";
        }
        if (pfPassword.getText().isEmpty()){
            isFilled = false;
            if (errorMessage.isEmpty()){
                errorMessage = "Введите пароль!";
            } else {
                errorMessage += "\nВведите пароль!";
            }
        }
        errorMessageLabel.setText(errorMessage);

        return isFilled;
    }


    public boolean isValid(String username, String pass){
        boolean isValid = true;

        if (!tfUsername.getText().equals(username)){
            isValid = false;
            errorMessage = "Неправильный логин!";
        }

        if (!pfPassword.getText().equals(pass)){
            isValid = false;
            if (errorMessage.isEmpty()){
                errorMessage = "Неправильный пароль!";
            } else {
                errorMessage += "\nНеправильный пароль!";
            }
        }
        errorMessageLabel.setText(errorMessage);

        return isValid;
    }


    private void startHomeWindow() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/main_football.fxml"));
            Stage stage = new Stage();
            stage.setMaximized(true);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image("img/admin.png"));
            btnClose.getScene().getWindow().hide();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnLogin.getScene().getWindow().hide();
            }
        });

        btnLogin.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                USERNAME = tfUsername.getText();
                PASSWORD = pfPassword.getText();
                //if (isFieldFilled() && isValid()) {
                if (isFieldFilled() && isValid(USERNAME, PASSWORD)) {
                    addAdmin(USERNAME, PASSWORD);
                    errorMessageLabel.setText("Администратор создан успешно");
                    tfUsername.setText("");

                    pfPassword.setText("");
                    //PauseTransition pause = new PauseTransition(Duration.seconds(2));
                    //pause.setOnFinished(e -> errorMessageLabel.getScene().getWindow().hide());
                } else {
                    errorMessageLabel.setText("Что-то введено неправильно :(");
                    PauseTransition pause = new PauseTransition(Duration.seconds(2));
                    pause.setOnFinished(e -> errorMessageLabel.setText(""));
                    pause.play();
                    tfUsername.setText("");
                    pfPassword.setText("");
                }
            }
        });
    }

    @FXML
    void onMouseClickedBtnShowPassword(MouseEvent event) throws InterruptedException {
        String maskPassword = pfPassword.getText();
        errorMessageLabel.setText("Введён пароль: " + maskPassword);
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> errorMessageLabel.setText(""));
        pause.play();
    }

    @FXML
    void onClickUser(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/main_user_football.fxml"));
            Stage stage = new Stage();
            stage.setMaximized(true);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image("img/fun2.png"));
            btnClose.getScene().getWindow().hide();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAdmin(String login, String password){

        Connection conn = null;
        try {
            conn = dbHandler.getDBConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "INSERT INTO " + Const.USER_TABLE + "("+ Const.USER_LOGIN+ ","+ Const.USER_PASSWORD +")" + " VALUES ('" + login +"','"+ password+ "')";
        dbHandler.executeQuery(query);
        System.out.println(query);
        /////////////////////////////////////////////////////
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ///////////////////////////////////////////////
    }
}
