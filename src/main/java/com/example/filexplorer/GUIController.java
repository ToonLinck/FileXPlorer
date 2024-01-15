package com.example.filexplorer;

import FileXPlorer.Backend.DateiInfo;
import FileXPlorer.Backend.FileManager;
import javafx.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GUIController {

    public FileManager fileManager = new FileManager();
    public List<DateiInfo> dateiList = new ArrayList<DateiInfo>();

    private String currDirPath = "";

    @FXML
    ChoiceBox mainWindowToolBarChoiceBox;

    @FXML
    Button mainWindowToolBarZurueckButton, mainWindowToolBarOeffnenButton, mainWindowToolBarLoeschenButton,
            mainWindowToolBarNeuButton;

    @FXML
    CheckBox mainWindowToolSubBarOrdnerAnzeigenCheckBox, mainWindowToolSubBarDateienAnzeigenCheckBox;

    @FXML
    Accordion mainWindowAccordion;

    @FXML
    public void initialize() {

        dateiList = Arrays.asList(DateiInfo.ConvertFileArrayToInfoArray(fileManager.getRootDir()));

        loadFileOverview();
    }



    /**
     * Wenn der beigelegte Dateipfad zu einem
     * Ordner führt, wird dieser Geöffnet und Inhalte werden geladen.
     *
     * @param dateiInfo das zugehörige DateiInfo Objekt des zu öffnenden Ordners
     */
    private void openFolder(DateiInfo dateiInfo) {
        if (dateiInfo != null && !dateiInfo.trueDateiPfad.isEmpty()){
            if (dateiInfo.dateiTyp == "") {       // bei einem leeren String als Dateityp liegt ein Ordner vor

                List<DateiInfo> neueDateiInfoList = new ArrayList<>(); //eine neue, leere Liste an DateiInfo Objekten wird erstellt


                fileManager.OpenFolder(dateiInfo.trueDateiPfad); //Ordner wird geöffnet

                neueDateiInfoList = Arrays.asList(DateiInfo.ConvertFileArrayToInfoArray(fileManager.getFolderContents())); //Neue Liste wird mit Inhalten des Ordenr gefüllt

                dateiList = filterSuchergebnisse(neueDateiInfoList); //Neue liste wird gefiltert und ersetzt alte Liste

                loadFileOverview();
            }
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
        groesseTextField.setText("" + (dateiInfo.dateigröße / 1000));
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
                aenderungsungsdatumPicker.setValue(Instant.ofEpochMilli(dateiInfo.dateiAenderungsdatum).atZone(ZoneId.systemDefault()).toLocalDate());
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

    /**
     *  Wird Ausgeführt, wenn der Öffnen knopf betätigt wird
     */
    @FXML
    private void open() {
        if (mainWindowAccordion.getExpandedPane() != null) {
            String selectedTruePath = mainWindowAccordion.getExpandedPane().getText();
            DateiInfo selectedInfo = findDateiInfoFromTruePath(selectedTruePath);
            if (selectedInfo != null && selectedInfo.dateiTyp == "") {
                currDirPath = selectedTruePath;
                openFolder(selectedInfo);
            }
        }
    }

    /**
     * Wird ausgeführt, wenn der Zurück Knopf betätigt wird
     */
    @FXML
    private void openPrev() {
        if (!currDirPath.isEmpty()) {
            File parentFile = fileManager.getCurrentFolder().getParentFile();
            if (parentFile == null) {
                dateiList = Arrays.asList(DateiInfo.ConvertFileArrayToInfoArray(fileManager.getRootDir()));
                currDirPath = "";
                loadFileOverview();
            }
            else {
                currDirPath = parentFile.getPath();
                fileManager.OpenFolder(currDirPath);
                openFolder(DateiInfo.ConvertFileToInfo(fileManager.getCurrentFolder()));
            }
        }
    }

    /**
     * Wird ausgeführt, wenn der Löschen Knopf betätigt wird
     */
    @FXML
    private void löschen() {
        if (mainWindowAccordion.getExpandedPane() != null) {
            DateiInfo selectedInfo = findDateiInfoFromTruePath(mainWindowAccordion.getExpandedPane().getText());


            DateiInfo parentInfo = DateiInfo.ConvertFileToInfo(DateiInfo.convertInfoToFile(selectedInfo).getParentFile());

            fileManager.DeleteFile(selectedInfo.trueDateiPfad);

            openFolder(parentInfo);

            loadFileOverview();
        }
    }

    /**
     * Eine Liste mit Inhalt des Typen DateiInfo wird nach den Filtereinstellungen gefiltert
     * @param pList ungefilterte Liste
     * @return gefilterte Liste
     */
    private List<DateiInfo> filterSuchergebnisse (List<DateiInfo> pList) {
        List<DateiInfo> returnListe = new ArrayList<>();

        boolean showFolders = mainWindowToolSubBarOrdnerAnzeigenCheckBox.isSelected();
        boolean showFiles = mainWindowToolSubBarDateienAnzeigenCheckBox.isSelected();

        if (showFolders) {
            for(DateiInfo info: pList) {
                if (info.dateiTyp.equals(""))
                    returnListe.add(info);
            }
        }
        if (showFiles) {
            for(DateiInfo info: pList) {
                if (!info.dateiTyp.equals(""))
                    returnListe.add(info);
            }
        }


        return returnListe;
    }

    /**
     * Wird ausgeführt, wenn die Eingabe von einem der Filter checkboxen geändert wird
     */
    @FXML
    private void filterSettingsChanged() {
        openFolder(DateiInfo.ConvertFileToInfo(fileManager.getCurrentFolder()));
    }


}