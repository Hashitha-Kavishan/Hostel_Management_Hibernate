package controller;

import bo.BOFactory;
import bo.custom.LoginBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.sql.SQLException;

public class ChangeUsernameFormController {
    public AnchorPane paneUsername;
    public JFXTextField txtOldUsername;
    public JFXTextField txtNewUsername;
    public Label lblError;
    public JFXButton btnChangeUsername;
    public JFXPasswordField txtPassword;

    private final LoginBO login = (LoginBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.LOGIN);

    public void changeUsernameOnAction(ActionEvent actionEvent) {
        String oldUN=txtOldUsername.getText();
        String newUN=txtNewUsername.getText();
        String pw=txtPassword.getText();

        if (oldUN.equals(null) || newUN.equals(null) || pw.equals(null)){
            lblError.setText("Please Fill Every Blank Spaces");
        }else {
            try {
                if(login.ifUserExists(oldUN,pw)){
                    if (login.changeUsername(oldUN,newUN)){
                        new Alert(Alert.AlertType.CONFIRMATION, "Username Updated.!").show();
                        clearFields();
                    }else {
                        new Alert(Alert.AlertType.ERROR, "Something Happened").show();
                    }
                }
                else{
                    lblError.setText("Enter correct username or password");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void clearFields() {
        txtNewUsername.setText(null);
        txtOldUsername.setText(null);
        txtPassword.setText(null);
    }

    public void goToNewUsername(ActionEvent actionEvent) {
        txtNewUsername.requestFocus();
    }

    public void goToPassword(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }
}
