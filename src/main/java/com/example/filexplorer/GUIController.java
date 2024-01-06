package com.example.filexplorer;

import javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class GUIController {


    public List<DateiInfo> dateiList = new ArrayList<DateiInfo>();

    @FXML
    ChoiceBox MainWindowToolBarChoiceBox;

    @FXML
    Button MainWindowToolBarZurueckButton, MainWindowToolBarOeffnenButton, MainWindowToolBarLoeschenButton,
            MainWindowToolBarNeuButton, MainWindowToolSubBarAnzeigenButton, MainWindowToolSubBarBearbeitenbButton,
            MainWindowToolSubBarSuchenButton;

    @FXML
    CheckBox MainWindowToolSubBarOrdnerAnzeigenCheckBox, MainWindowToolSubBarDateienAnzeigenCheckBox1;

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


    public void LoadFileOverview() {

    }

    /**
     * CreateElementFromDateiInfo erstellt nach eingabe eines Objektes vom Typen DateiInfo ein TitledPane Element
     *
     * @param dateiInfo Das DateiInfo Objekt enthält relevante Informationen über die darzustellende Datei
     * @return Ein fertiges TitledPane Element, in das die Daten der Datei eingefügt wurden
     */
    private TitledPane CreateElementFromDateiInfo (DateiInfo dateiInfo) {
        TitledPane returnTitledPane = new TitledPane();
        returnTitledPane.setText(dateiInfo.getDateiName());

            AnchorPane nAnchorPane = new AnchorPane();
                Label nameLabel = new Label();
                    nameLabel.setText("Name");
                    nameLabel.setLayoutX(14);
                    nameLabel.setLayoutY(14);

                Label dateitypLabel = new Label();
                    dateitypLabel.setText("Dateityp");
                    dateitypLabel.setLayoutX(14);
                    dateitypLabel.setLayoutY(49);

                Label aenderungsdatumLabel = new Label();
                    aenderungsdatumLabel.setText("Änderungsdatum");
                    aenderungsdatumLabel.setLayoutX(257);
                    aenderungsdatumLabel.setLayoutY(14);

                Label groesseLabel = new Label();
                    groesseLabel.setText("Größe");
                    groesseLabel.setLayoutX(14);
                    groesseLabel.setLayoutY(84);

                Label erstellungsdatumLabel = new Label();
                    erstellungsdatumLabel.setText("Erstellungsdatum");
                    erstellungsdatumLabel.setLayoutX(275);
                    erstellungsdatumLabel.setLayoutY(49);


                TextField nameTextField = new TextField();
                    nameTextField.setText(dateiInfo.getDateiName());
                    nameTextField.setPrefSize(122,25);
                    nameTextField.setLayoutX(83);
                    nameTextField.setLayoutY(14);

                Button saveButton = new Button();
                    saveButton.setText("Änderungen speichern");
                    saveButton.setLayoutY(310);
                    saveButton.setLayoutX(488);
                    saveButton.setOnAction(event -> {
                         if (!nameTextField.getText().isEmpty()) {
                             dateiInfo.setDateiName(nameTextField.getText());
                             ChangeDateiInfo(dateiInfo);
                         }
                    });

        TextField groesseTextField = new TextField();
        nameTextField.setText("" + dateiInfo.getDateigröße());
        nameTextField.setPrefSize(122,25);
        nameTextField.setLayoutX(83);
        nameTextField.setLayoutY(84);








            nAnchorPane.getChildrenUnmodifiable().add(nameLabel);
            nAnchorPane.getChildrenUnmodifiable().add(dateitypLabel);
            nAnchorPane.getChildrenUnmodifiable().add(aenderungsdatumLabel);
            nAnchorPane.getChildrenUnmodifiable().add(groesseLabel);
            nAnchorPane.getChildrenUnmodifiable().add(erstellungsdatumLabel);

        returnTitledPane.getChildrenUnmodifiable().add(nAnchorPane);

        return returnTitledPane;
    }

    private void ChangeDateiInfo(DateiInfo nDateiInfo) {
        //todo Datei info wird geändert
    }

}