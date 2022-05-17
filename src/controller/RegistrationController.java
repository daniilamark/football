//package controller;
//
//import javafx.animation.PauseTransition;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//import javafx.util.Duration;
//import main.Main;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class RegistrationController implements Initializable {
//    @FXML
//    private Button btnClose;
//
//    @FXML
//    private Button btnRegistration;
//
//    @FXML
//    private ImageView btnShowPassword;
//
//    @FXML
//    private Label messageLabel;
//
//    @FXML
//    private PasswordField pfPassword;
//
//    @FXML
//    private TextField tfUsername;
//
//    LoginController loginController = new LoginController();
//    private String messageLabelStr = "";
//    private double x, y;
//
//    @FXML
//    void onMouseClickedBtnShowPassword(MouseEvent event) throws InterruptedException {
//        String maskPassword = pfPassword.getText();
//        messageLabel.setText("Введён пароль: " + maskPassword);
//        PauseTransition pause = new PauseTransition(Duration.seconds(2));
//        pause.setOnFinished(e -> messageLabel.setText(""));
//        pause.play();
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        btnClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                System.exit(0);
//            }
//        });
//
//        btnRegistration.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                messageLabelStr = "";
//
//                if (loginController.isFieldFilled() && loginController.isValid()) {
//                    try {
//                        startLoginWindow();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//    }
//
//    private void startLoginWindow() throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
//        stage.initStyle(StageStyle.TRANSPARENT);
//        stage.setScene(new Scene(root));
//
//
//
//        root.setOnMousePressed(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                x = mouseEvent.getSceneX();
//                y = mouseEvent.getSceneY();
//            }
//        });
//
//        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                stage.setX(mouseEvent.getScreenX() - x);
//                stage.setY(mouseEvent.getScreenY() - y);
//            }
//        });
//
//
//
//        stage.show();
//    }
//}
