package FileXPlorer.Backend;

import java.io.*;

public class FileManager {
    File currentFolder;
    File selectedFile;
    File[] folderContents;

    /**
     * returns a List of Files in the current folder
     * @return list of Files
     */
    public File[] getFolderContents() {
        return folderContents;
    }

    /**
     * returns the currently viewed Folder
     * @return the current folder as a File
     */
    public File getCurrentFolder() {
        return currentFolder;
    }

    /**
     * constructs a new FileManager and sets the currently viewed folder to the current working directory
     */
    public FileManager(){
        currentFolder = new File(System.getProperty("user.dir"));
    }

    /**
     * opens the Folder at the specified location
     * @param folderPath absolute path of the folder
     * @return
     */
    public boolean openFolder(String folderPath)
    {
        currentFolder = new File(folderPath);
        return openFolder();
    }

    /**
     * shorthand for opening the current Folder
     * @return false when folder doesn't exist
     */
    private boolean openFolder(){
        if(currentFolder.exists()){
            folderContents = currentFolder.listFiles();
            return true;
        }
        return false;
    }

    /**
     * prints the current Folder's content to the Console, given that it has any
     */
    public void printFolderContents() {
        openFolder();
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

    /**
     * opens the currently selected text file
     * @return String of contents of the current text file
     * @throws IOException
     */
    public String openTxtFile() throws IOException {
        if(selectedFile.exists())
        {
            BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
            String line;
            StringBuilder fileContents = new StringBuilder();
            while((line = reader.readLine()) != null){
                fileContents.append(line).append("\n");
            }
            String result = fileContents.toString();
            reader.close();
            return result;
        }
        return "An Error occurred while reading the File at: '" + selectedFile.getAbsolutePath() + "', Please ensure the File exists and can be read.";
    }

    /**
     * Opens a text file at the specified location
     * @param path absolute path of the file
     * @return String of contents of the text file
     * @throws IOException
     */
    public String openTxtFile(String path) throws IOException {
        selectedFile = new File(path);
        return openTxtFile();
    }

    /**
     * creates a new text file in the currently viewed folder
     * @return true on successful creation
     */
    public boolean createNewTxtFile(){
        return createNewTxtFile("Unnamed", "");
    }

    /**
     * creates a new text file at a specified location with specified content
     * @param baseName preferred name of the file
     * @param Content String to be written to the file
     * @return true on success
     */
    public boolean createNewTxtFile(String baseName, String Content){
        String name;
        int counter = 1;

        File temp = new File(currentFolder + File.separator + baseName + ".txt");

        try{
            while(!temp.createNewFile()){
                name = baseName + "(" + counter +")";
                temp = new File(currentFolder + File.separator + name + ".txt");
                counter++;
            }
            while(!temp.exists()){ }
            editTxtFile(temp, Content);
        }
        catch(Exception e){
            System.out.println("File could not be created!");
            return false;
        }
        return true;
    }

    /**
     * changes the content of the currently selected text file to the specified String
     * @param content new content of text file
     * @return true on success
     * @throws IOException
     */
    public boolean editTxtFile(String content) throws IOException {
        if(selectedFile.exists()){
            BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
            writer.write(content != null ? content : "");
            writer.close();
            return true;
        }
        return createNewTxtFile(selectedFile.getName(), content);
    }

    /**
     * changes the content of a given text file to a specified String
     * @param path the absolute path
     * @param content new content of text file
     * @return true on success
     * @throws IOException
     */
    public boolean editTxtFile(File path, String content) throws IOException {
        selectedFile = path;
        return editTxtFile(content);
    }

    /**
     * deletes the currently selected File
     * @return true on success
     */
    public boolean deleteFile(){
        if(selectedFile.exists()){
            return selectedFile.delete();
        }
        return false;
    }

    /**
     * deletes the FIle at the specified location
     * @param path the absolute path of the file
     * @return true on success
     */
    public boolean deleteFile(String path){
        selectedFile = new File(path);
        return deleteFile();
    }

    /**
     * returns the contents of the root directory
     * @return list of files in the root directory of the system
     */
    public File[] getRootDir() {
        return File.listRoots();
    }
}
