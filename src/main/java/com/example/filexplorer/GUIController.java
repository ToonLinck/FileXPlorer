package com.example.filexplorer;

import FileXPlorer.Backend.DateiInfo;
import javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GUIController {


    public List<DateiInfo> dateiList = new ArrayList<DateiInfo>();

    @FXML
    ChoiceBox mainWindowToolBarChoiceBox;

    @FXML
    Button mainWindowToolBarZurueckButton, mainWindowToolBarOeffnenButton, mainWindowToolBarLoeschenButton,
            mainWindowToolBarNeuButton, mainWindowToolSubBarAnzeigenButton, mainWindowToolSubBarBearbeitenbButton,
            mainWindowToolSubBarSuchenButton;

    @FXML
    CheckBox mainWindowToolSubBarOrdnerAnzeigenCheckBox, mainWindowToolSubBarDateienAnzeigenCheckBox1;

    @FXML
    Accordion mainWindowAccordion;

    @FXML
    public void initialize() {
        mainWindowToolBarChoiceBox.getItems().add("Groeße ASC");
        mainWindowToolBarChoiceBox.getItems().add("Groeße DESC");
        mainWindowToolBarChoiceBox.getItems().add("ALter ASC");
        mainWindowToolBarChoiceBox.getItems().add("Alter DESC");
        mainWindowToolBarChoiceBox.getItems().add("Alphabetisch");

        DateiInfo testDatei = new DateiInfo();
        testDatei.dateiName = "test.txt";
        testDatei.dateiTyp = "Textdatei";
        testDatei.dateigröße = 2333;
        testDatei.trueDateiPfad = "C:/test.txt";


        dateiList.add(testDatei);


        loadFileOverview();
    }



    /**
     * Diese Methode wird ausgeführt, wenn der "Open" knopf gedrückt wird. Wenn der beigelegte Dateipfad zu einem
     * Ordner führt, wird dieser Geöffnet und Inhalte werden geladen.
     */
    public void openFolder(String dateiPfad) {
        if (dateiPfad != null && !dateiPfad.isEmpty()){

        }
    }

    /**
     * Läd die Dateien im Ausgewählten Ordner und zeigt sie in der Oberfläche an
     */
    public void loadFileOverview() {

        mainWindowAccordion.getPanes().clear();

        for (DateiInfo dateiInfo : dateiList)
        {
            mainWindowAccordion.getPanes().add(createElementFromDateiInfo(dateiInfo));
        }
    }

    /**
     * CreateElementFromDateiInfo erstellt nach eingabe eines Objektes vom Typen DateiInfo ein TitledPane Element
     *
     * @param dateiInfo Das DateiInfo Objekt enthält relevante Informationen über die darzustellende Datei
     * @return Ein fertiges TitledPane Element, in das die Daten der Datei eingefügt wurden
     */
    private TitledPane createElementFromDateiInfo (DateiInfo dateiInfo) {
        TitledPane returnTitledPane = new TitledPane();
        returnTitledPane.setText(dateiInfo.trueDateiPfad);
        returnTitledPane.setAnimated(false);

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

                TextField nameTextField = new TextField();
                    nameTextField.setText(dateiInfo.dateiName);
                    nameTextField.setPrefSize(122,25);
                    nameTextField.setLayoutX(83);
                    nameTextField.setLayoutY(14);

                Button saveButton = new Button();
                    saveButton.setText("Änderungen speichern");
                    saveButton.setLayoutY(200);
                    saveButton.setLayoutX(450);
                    saveButton.setOnAction(event -> {
                         if (!nameTextField.getText().isEmpty()) {
                             dateiInfo.dateiName = nameTextField.getText();
                             ChangeDateiInfo(dateiInfo);
                         }
                    });

                TextField groesseTextField = new TextField();
        groesseTextField.setText("" + dateiInfo.dateigröße);
        groesseTextField.setPrefSize(122,25);
        groesseTextField.setLayoutX(83);
        groesseTextField.setLayoutY(84);
        groesseTextField.setEditable(false);

        TextField dateiTypTextField = new TextField();
        dateiTypTextField.setText(dateiInfo.dateiTyp);
        dateiTypTextField.setPrefSize(122,25);
        dateiTypTextField.setLayoutX(83);
        dateiTypTextField.setLayoutY(49);
        dateiTypTextField.setEditable(false);

            DatePicker aenderungsungsdatumPicker = new DatePicker();
                aenderungsungsdatumPicker.setLayoutX(368);
                aenderungsungsdatumPicker.setLayoutY(14);
                aenderungsungsdatumPicker.setEditable(false);

            nAnchorPane.getChildren().add(dateiTypTextField);
            nAnchorPane.getChildren().add(nameLabel);
            nAnchorPane.getChildren().add(dateitypLabel);
            nAnchorPane.getChildren().add(aenderungsdatumLabel);
            nAnchorPane.getChildren().add(groesseLabel);
            nAnchorPane.getChildren().add(nameTextField);
            nAnchorPane.getChildren().add(saveButton);
            nAnchorPane.getChildren().add(groesseTextField);
            nAnchorPane.getChildren().add(aenderungsungsdatumPicker);



        returnTitledPane.setContent(nAnchorPane);

        return returnTitledPane;
    }

    private void ChangeDateiInfo(DateiInfo nDateiInfo) {
        //todo Datei info wird geändert
    }

    /**
     * Sucht in der dateiList Liste nach einem DateiInfo Objekt, wobei der DateiPfad gegeben wird
     *
     * @param searchTruePath der Pfad der Datei, nach dessen DateiInfo Objekt gesucht werden soll. Ausgabe defaultet auf Null, falls nicht vorhanden
     * @return Das zugehörige DateiInfo Objekt aus der Liste dateiList. Defaultet auf Null als ausgabe, wenn nicht vorhanden.
     */
    private DateiInfo findDateiInfoFromTruePath (String searchTruePath) {
        if (searchTruePath == null || searchTruePath.isEmpty())
            return null;

        for (DateiInfo dateiInfo: dateiList) {
            if (dateiInfo.trueDateiPfad.equals(searchTruePath)) {
                return dateiInfo;
            }
        }

        return null;
    }

}