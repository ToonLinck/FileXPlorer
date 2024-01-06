package com.example.filexplorer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIApplication extends Application {





    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIApplication.class.getResource("Main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("FileXPlorer");
        stage.setScene(scene);
        stage.setWidth(640);
        stage.setHeight(400);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        applicationLaunch(args);
    }

    /**
     * Diese Methode startet die Application und führt zum Öffnen des Main-Fensters
     *
     * @param args in der Regel der String Array aus der Main Methode
     */
    public static void applicationLaunch(String[] args) {
        launch(args);
    }


    public void SetFiles() {

    }


}