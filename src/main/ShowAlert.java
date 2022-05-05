package main;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ShowAlert {

    public static void showAlertInformation(String title, String content) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("img/admin.png"));

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();
    }

    public static void showAlertError(String title, String res,String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(res);
        alert.setContentText(content);

        alert.showAndWait();
    }

}
