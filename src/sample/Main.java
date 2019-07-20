package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Search text in file");
        primaryStage.setMinWidth(1030.0D);
        primaryStage.setMinHeight(630.0D);
        primaryStage.setScene(new Scene(root, 1200.0D, 600.0D));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
