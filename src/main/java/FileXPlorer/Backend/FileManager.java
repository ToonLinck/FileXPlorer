package FileXPlorer.Backend;

import java.io.*;

public class FileManager {
    File currentFolder;
    File selectedFile;
    File[] folderContents;

    /**
     * returns the Contents of the currently viewed folder as a List of files
     * @return
     */
    public File[] getFolderContents() {
        return folderContents;
    }

    /**
     * returns the currently viewed Folder
     * @return
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
     * @param folderPath
     * @return
     */
    public boolean openFolder(String folderPath)
    {
        currentFolder = new File(folderPath);
        return openFolder();
    }

    /**
     * shorthand for opening the current Folder, fails when said Folder doesn't exist
     * @return
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
     * returns the contents of the currently selected text file as a String
     * @return
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
     * returns the contents of a text file at the specified location
     * @param path
     * @return
     * @throws IOException
     */
    public String openTxtFile(String path) throws IOException {
        selectedFile = new File(path);
        return openTxtFile();
    }

    /**
     * creates a new text file in the currently viewed folder
     * @return
     */
    public boolean createNewTxtFile(){
        return createNewTxtFile("Unnamed", "");
    }

    /**
     * creates a new text file at a specified location with specified content
     * @param baseName
     * @param Content
     * @return
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
     * @param content
     * @return
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
     * @param path
     * @param content
     * @return
     * @throws IOException
     */
    public boolean editTxtFile(File path, String content) throws IOException {
        selectedFile = path;
        return editTxtFile(content);
    }

    /**
     * deletes the currently selected File
     * @return
     */
    public boolean deleteFile(){
        if(selectedFile.exists()){
            return selectedFile.delete();
        }
        return false;
    }

    /**
     * deletes the FIle at the specified location
     * @param path
     * @return
     */
    public boolean deleteFile(String path){
        selectedFile = new File(path);
        return deleteFile();
    }
}
