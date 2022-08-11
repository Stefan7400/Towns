package de.crackyman.towns.configuration;

import com.google.gson.Gson;
import de.crackyman.towns.filemanager.FileManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Configuration {

    private static final String CONFIG_FILE_NAME = "config.json";
    private static Configuration instance = null;
    private String databaseName = "mctownsdb";

    public static Configuration getInstance(){
        if(Objects.isNull(instance)){
            loadInstance();
        }
        return instance;
    }

    private static void loadInstance() {
        File possibleConfigFile = new File( FileManager.getInstance().getDataFolder().getPath() + File.separator + CONFIG_FILE_NAME);
        if(!possibleConfigFile.exists()){
            //Possible first creation
            instance = new Configuration();
            return;
        }
        try {
            instance = new Gson().fromJson(FileUtils.readFileToString(possibleConfigFile,StandardCharsets.UTF_8),Configuration.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(){
        File configFile = checkForConfigFile();
        try {
            FileUtils.writeStringToFile(configFile,new Gson().toJson(instance), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDatabaseName() {
        return databaseName;
    }

    private static File checkForConfigFile(){
        File configFile = new File( FileManager.getInstance().getDataFolder().getPath() + File.separator + CONFIG_FILE_NAME);
        if(!configFile.exists()){
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return configFile;
    }

}
