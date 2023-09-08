import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("view//LoggingForm.fxml"))));
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.setTitle("Logging Hostel Management System");
        primaryStage.show();
    }
}
