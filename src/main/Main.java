package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    public static final String USERNAME = "admin";
    public static final String PASSWORD = "1";

    @Override
    public void start(Stage stage) throws IOException {
        // Parent root = FXMLLoader.load(getClass().getResource("../views/main_football.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
        stage.setTitle("Панель организатора");
        stage.getIcons().add(new Image("img/admin.png"));
        stage.initStyle(StageStyle.TRANSPARENT);
        // stage.setScene(new Scene(root, 1315, 890));
        stage.setScene(new Scene(root));
        // stage.setMaximized(true);
        stage.show();
    }

    public static void exit(){
        System.exit(0);
    }

    public static void main(String[] args) {
        launch();
    }
}