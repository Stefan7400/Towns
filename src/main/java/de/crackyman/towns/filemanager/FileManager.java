package de.crackyman.towns.filemanager;

import de.crackyman.towns.TownsMain;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class FileManager {

    private static FileManager instance = null;

    private File dataFolder;

    public static FileManager getInstance() {
        if(Objects.isNull(instance)){
            instance = new FileManager();
        }
        return instance;
    }

    private FileManager(){
        createPluginFolderIfNeeded();
    }

    private void createPluginFolderIfNeeded(){
        this.dataFolder = TownsMain.getINSTANCE().getDataFolder();
        if(!dataFolder.exists()){
            dataFolder.mkdir();
        }
    }

    private void createNeededFiles(){

    }

    public Optional<File> getFileForName(String fileName){
        return Arrays.stream(this.dataFolder.listFiles()).filter(file -> file.getName().equals(fileName)).findFirst();
    }

    public void save(File file){

    }

    public File getDataFolder() {
        return dataFolder;
    }
}
