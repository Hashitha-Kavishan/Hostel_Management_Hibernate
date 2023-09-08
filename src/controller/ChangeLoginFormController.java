package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class ChangeLoginFormController {
    public Pane contentDisplayPane1;
    public Pane contentDisplayPane2;
    public AnchorPane root;
    


    public void loadUI() throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("../view/ChangePasswordForm.fxml"));
        contentDisplayPane1.getChildren().add(parent);
        Parent parent2= FXMLLoader.load(getClass().getResource("../view/ChangeUsernameForm.fxml"));
        contentDisplayPane2.getChildren().add(parent2);
    }

    public void initialize() throws IOException {
        loadUI();
    }
}
