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

public class ChangePasswordFormController {
    public JFXPasswordField txtNewPassword;
    public JFXPasswordField txtRePassword;
    public JFXButton btnChangePassword;
    public Label lblError;
    public JFXTextField txtUsername;
    public AnchorPane paneUsername;

    private final LoginBO login = (LoginBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.LOGIN);

    public void changePasswordOnAction(ActionEvent actionEvent) throws InterruptedException {
        String UN=txtUsername.getText();
        String newPW=txtNewPassword.getText();
        String rePW=txtRePassword.getText();

        if (UN.equals(null) || newPW.equals(null) || rePW.equals(null)){
            lblError.setText("Please Fill Every Blank Spaces");
        }else {
            if (newPW.equals(rePW)){
                try {
                    if(login.ifUserExists(UN)){
                        if (login.changePassword(UN,newPW)){
                            new Alert(Alert.AlertType.CONFIRMATION, "Password Updated.!").show();
                            clearFields();
                        }else {
                            new Alert(Alert.AlertType.ERROR, "Something Happened").show();
                        }
                    }else{
                        lblError.setText("Enter correct username or password");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }else {
                lblError.setText("Check Password Again");
            }

        }
    }

    private void clearFields() {
        txtNewPassword.setText(null);
        txtRePassword.setText(null);
        txtUsername.setText(null);
    }

    public void goToNewPassword(ActionEvent actionEvent) {
        txtNewPassword.requestFocus();
    }

    public void goToRePassword(ActionEvent actionEvent) {
        txtRePassword.requestFocus();
    }


}
