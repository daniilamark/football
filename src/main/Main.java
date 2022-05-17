package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    // TODO
    // регистрация пользователей
    // создание админов
    // аунтификация
    // доработать БД
    // вью юзера
    // доработать игроков бд и матчи
    // комментарии в код
    // документация и презентация
    // бд в облаке и EXE файл


    public static final String USERNAME = "admin";
    public static final String PASSWORD = "1";
    private double x, y;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/main_football.fxml"));
        stage.setTitle("Панель организатора");
        stage.getIcons().add(new Image("img/admin.png"));
        stage.setScene(new Scene(root, 1315, 890));
        stage.setMaximized(true);


        //Parent root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
        //stage.initStyle(StageStyle.TRANSPARENT);
        // stage.setScene(new Scene(root));


        /*
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

         */

        stage.show();
    }

    public static void exit(){
        System.exit(0);
    }

    public static void main(String[] args) {
        launch();
    }
}