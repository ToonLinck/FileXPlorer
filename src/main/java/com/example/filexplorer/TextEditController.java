package com.example.filexplorer;

import FileXPlorer.Backend.DateiInfo;
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

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TextEditController {


    private DateiInfo dateiInfo;
    FileManager fileManager = new FileManager();

    Stage stage = new Stage();

    @FXML
    Button textEditSaveButton;
    @FXML
    TextField textEditNameField;
    @FXML
    TextArea textEditContentField;

    public TextEditController (DateiInfo dateiInfo) {
        this.dateiInfo = dateiInfo;
    }

    public void show() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TextEdit-view.fxml"));
        loader.setController(this);
        Scene scene = new Scene(loader.load(),400, 640);

        stage.setScene(scene);
        stage.setTitle("Text Edit");
        stage.setMinWidth(640);
        stage.setMaxWidth(640);
        stage.show();

        String content = fileManager.openTxtFile(dateiInfo.trueDateiPfad);

        textEditContentField.setText(content);
        textEditNameField.setText(dateiInfo.dateiName);

        textEditSaveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    save();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @FXML
    private void save() throws IOException {

        dateiInfo.dateiName = checkNameForFileExtention(textEditNameField.getText());

        File saveFile = DateiInfo.convertInfoToFile(dateiInfo);

        fileManager.editTxtFile(saveFile, textEditContentField.getText());

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
