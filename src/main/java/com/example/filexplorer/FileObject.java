package com.example.filexplorer;


import java.util.Date;

public class FileObject {




    String Name;
    String Type;
    String Path;
    int Size;
    Date Aenderung, Erstellung;

    public FileObject(String name, String type, String path, int size, Date aenderung, Date erstellung) {
        Name = name;
        Type = type;
        Path = path;
        Size = size;
        Aenderung = aenderung;
        Erstellung = erstellung;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        Size = size;
    }

    public Date getAenderung() {
        return Aenderung;
    }

    public void setAenderung(Date aenderung) {
        Aenderung = aenderung;
    }

    public Date getErstellung() {
        return Erstellung;
    }

    public void setErstellung(Date erstellung) {
        Erstellung = erstellung;
    }

}

