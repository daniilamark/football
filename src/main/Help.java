package main;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class Help {

    public static boolean isFieldFill(TextField tf){

        if (tf.getText() == "") {
            return false;

        } else {
            return true;
        }
    }

    public static boolean isFieldFill(DatePicker dp){

        if (dp.getPromptText() == "") {
            return false;

        } else {
            return true;
        }
    }

    public static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isString(String s) throws NumberFormatException {
        try {
            String.valueOf(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
