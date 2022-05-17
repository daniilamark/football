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
import main.Main;
import main.ShowAlert;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button btnClose;

    @FXML
    private Label errorMessageLabel;

    @FXML
    private ImageView btnShowPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private TextField tfUsername;

    private String errorMessage = "";


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


    public boolean isValid(){
        boolean isValid = true;

        if (!tfUsername.getText().equals(Main.USERNAME)){
            isValid = false;
            errorMessage = "Неправильный логин!";
        }

        if (!pfPassword.getText().equals(Main.PASSWORD)){
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
                System.exit(0);
            }
        });

        btnLogin.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                errorMessage = "";
                if (isFieldFilled() && isValid()) {
                    startHomeWindow();
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
    void onClickRegistration(MouseEvent event) {

    }
}
