package com.example.filexplorer;

import FileXPlorer.Backend.FileManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;

public class TextCreateController {


    FileManager fileManager = new FileManager();

    Stage stage = new Stage();

    String savePath;
    @FXML
    Button textEditSaveButton;
    @FXML
    TextField textEditNameField;
    @FXML
    TextArea textEditConField;

    public TextCreateController (String savePath) {
        this.savePath = savePath;
    }

    public void show() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TextCreate-view.fxml"));
        loader.setController(this);
        Scene scene = new Scene(loader.load(),400, 640);

        stage.setScene(scene);
        stage.setTitle("Text Create");
        stage.setMinWidth(640);
        stage.setMaxWidth(640);
        stage.show();

        textEditSaveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                save();
            }
        });

    }

    @FXML
    private void save() {

        fileManager.openFolder(savePath);

        fileManager.createNewTxtFile(checkNameForFileExtention(textEditNameField.getText()), textEditConField.getText());

        stage.close();
    }

    private String checkNameForFileExtention (String name) {
        String returnName = name;

        if (returnName.isEmpty()) {
            returnName = "empty.txt";
        }

        int indexOfPeridot = returnName.indexOf(".");

        if (indexOfPeridot == -1 || !name.substring(indexOfPeridot, returnName.length()).equals(".txt")) {
            returnName += ".txt";
        }

        return returnName;
    }

}
