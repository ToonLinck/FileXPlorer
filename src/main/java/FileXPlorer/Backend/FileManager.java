package FileXPlorer.Backend;

import java.io.*;

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

    private void OpenFolder(){
        folderContents = currentFolder.listFiles();

        //Debug PrintFolderContents();
    }
    public void PrintFolderContents() {
        OpenFolder();
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

    public String openTxtFile(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
            String line;
            StringBuilder fileContents = new StringBuilder();
            while((line = reader.readLine()) != null){
                fileContents.append(line).append("\n");
            }
            String result = fileContents.toString();
            reader.close();
            return result;
        } catch (Exception e) {
            return "An Error occurred while reading the File at: '" + selectedFile.getAbsolutePath() + "', Please ensure the File exists and can be read.";
        }
    }

    public static void main(String[] args) {
        FileManager manager = new FileManager();

        String fileName = "Test";

        manager.CreateNewFile(fileName, "Hewwuuuuuuu!");
        manager.selectedFile = new File(manager.currentFolder + File.separator + fileName + ".txt");
        System.out.println("File Contents: " + manager.openTxtFile());
    }

    public boolean CreateNewFile(){
        return CreateNewFile("Unnamed", "");
    }

    public boolean CreateNewFile(String baseName, String Content){
        String name;
        int counter = 1;

        File temp = new File(currentFolder + File.separator + baseName + ".txt");

        try{
            while(!temp.createNewFile()){
                name = baseName + "(" + counter +")";
                temp = new File(currentFolder + File.separator + name + ".txt");
                counter++;
            }

        }
        catch(Exception e){
            System.out.println("File could not be created!");
            return false;
        }

        while(!temp.exists()){ //wait for File Creation }
        return true;
    }

    public boolean editTxtFile(File path, String Content) throws Exception{
        if(true){
            BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
            writer.write("");
            writer.close();
        }
        return CreateNewFile();
    }

    public boolean DeleteFile(){
        if(true){
            return selectedFile.delete();
        }
        return false;
    }

    public boolean DeleteFile(String path){
        selectedFile = new File(path);
        return DeleteFile();
    }

    public File[] getRootDir () {
        return File.listRoots();
    }

    public File[] getFolderContents() {
        return folderContents;
    }

    public File getCurrentFolder() {
        return currentFolder;
    }


}
