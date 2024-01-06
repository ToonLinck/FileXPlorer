package FileXPlorer.Backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileManager {
    File currentFolder;
    File selectedFile;
    File[] folderContents;

    public FileManager(){
        currentFolder = new File(System.getProperty("user.dir"));
    }
    public void OpenFolder(String folderPath)
    {
        currentFolder = new File(folderPath);
        OpenFolder();
    }

    public void OpenFolder(){
        folderContents = currentFolder.listFiles();

        //Debug PrintFolderContents();
    }
    public void PrintFolderContents() {
        if ((folderContents == null))
        {
            System.out.println("404 Folder not found");
            return;
        }
        if (folderContents.length == 0){
            System.out.println("Folder is Empty");
            return;
        }
        for (File f : folderContents) {
            System.out.println(f.getName());
        }
    }

    public String OpenFile(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
            String line;
            StringBuilder fileContents = new StringBuilder();
            while((line = reader.readLine()) != null){
                fileContents.append(line).append("\n");
            }
            return fileContents.toString();
        } catch (Exception e) {
            return "An Error occurred while reading the File at: '" + selectedFile.getAbsolutePath() + "', Please ensure the File exists and can be read.";
        }
    }

    public static void main(String[] args) {
        FileManager manager = new FileManager();
        manager.OpenFolder();
        manager.CreateNewFile();
        manager.selectedFile = new File(manager.currentFolder.getAbsolutePath() + File.separator + "Unnamed.txt");
        manager.PrintFolderContents();
    }

    public boolean CreateNewFile(){
        String baseName = "Unnamed";
        String name;
        int counter = 1;

        File temp = new File(currentFolder + File.separator + baseName + ".txt");

        try{
            while(!temp.createNewFile()){
                name = baseName + "(" + counter +")";
                temp = new File(currentFolder + File.separator + name + ".txt");
                counter++;
            }
            return true;
        }
        catch(Exception e){
            System.out.println("File could not be created!");
            return false;
        }
    }

    public boolean CreateNewFile(String name, String Content){
        return true;
    }
}
