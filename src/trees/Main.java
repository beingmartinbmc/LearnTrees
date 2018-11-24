package trees;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML/View.fxml"));
            primaryStage.setTitle("Trees");
            primaryStage.setScene(new Scene(root, 410, 340));
        }
        catch (NullPointerException e){
            e.getMessage();
        }
        primaryStage.getIcons().add(new Image("file:data/icon.jpg"));
        primaryStage.show();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "This is a data-tree animation created by Ankit Sharma!", ButtonType.OK);
        alert.getDialogPane().setMinHeight(80);
        alert.show();
    }
}
