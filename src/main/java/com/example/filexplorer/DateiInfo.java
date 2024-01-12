package com.example.filexplorer;

import java.util.Date;

public class DateiInfo {

    public String getDateiName() {
        return dateiName;
    }

    public void setDateiName(String dateiName) {
        this.dateiName = dateiName;
    }

    public String getDateiTyp() {
        return dateiTyp;
    }

    public void setDateiTyp(String dateiTyp) {
        this.dateiTyp = dateiTyp;
    }

    public int getDateigröße() {
        return dateigröße;
    }

    public void setDateigröße(int dateigröße) {
        this.dateigröße = dateigröße;
    }

    public Date getDateiAenderungsdatum() {
        return dateiAenderungsdatum;
    }

    public void setDateiAenderungsdatum(Date dateiAenderungsdatum) {
        this.dateiAenderungsdatum = dateiAenderungsdatum;
    }

    public Date getDateiErstellungsdatum() {
        return dateiErstellungsdatum;
    }

    public void setDateiErstellungsdatum(Date dateiErstellungsdatum) {
        this.dateiErstellungsdatum = dateiErstellungsdatum;
    }

    public String getTrueDateiName() {
        return trueDateiPfad;
    }

    public void setTrueDateiName(String trueDateiPfad) {
        this.trueDateiPfad = trueDateiPfad;
    }

    public String trueDateiPfad;
    public String dateiName;
    public String dateiTyp;
    public int dateigröße;
    public Date dateiAenderungsdatum;
    public Date dateiErstellungsdatum;

}
