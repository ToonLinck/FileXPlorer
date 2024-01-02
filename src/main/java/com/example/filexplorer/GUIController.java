package com.example.filexplorer;

import javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class GUIController {

    @FXML
    ChoiceBox MainWindowToolBarChoiceBox;

    @FXML
    public void initialize() {
        MainWindowToolBarChoiceBox.getItems().add("Groeße ASC");
        MainWindowToolBarChoiceBox.getItems().add("Groeße DESC");
        MainWindowToolBarChoiceBox.getItems().add("ALter ASC");
        MainWindowToolBarChoiceBox.getItems().add("Alter DESC");
        MainWindowToolBarChoiceBox.getItems().add("Alphabetisch");
    }



    /**
     * Diese Methode wird ausgeführt, wenn der "Open" knopf gedrückt wird
     */
    public void Open() {

    }

}