package com.cimba.lightsout;

import com.cimba.lightsout.factory.LightsOutFactory;
import com.cimba.lightsout.factory.LightsOutFactoryImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LightsOutApp extends Application {

    private static final Image image = new Image("file:src/main/java/com/cimba/lightsout/img/gear_wow_cursor.png");


    @Override
    public void start(Stage primaryStage) {

        try {
            LightsOutFactory factory = new LightsOutFactoryImpl();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LightsOutGUI.fxml"));
            loader.setControllerFactory(param -> new LightsOutController(factory));
            BorderPane root = loader.load();

            Scene scene = new Scene(root);
            scene.setCursor(new ImageCursor(image));

            primaryStage.setScene(scene);
            primaryStage.show();

            root.requestFocus();
        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();
            showAlertAndExit();
        }
    }

    private void showAlertAndExit() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Failed to start the application.");
        alert.showAndWait();
        System.exit(1);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
