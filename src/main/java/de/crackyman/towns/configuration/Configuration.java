package de.crackyman.towns.configuration;

import com.google.gson.Gson;
import com.google.inject.Inject;

import java.util.Objects;

public class Configuration {


    private static Configuration instance = null;
    private String databaseName = "mctownsdb";

    public static Configuration getInstance(){
        if(Objects.isNull(instance)){
            loadInstance();
        }
        return instance;
    }

    private static void loadInstance(){
       instance = new Gson().fromJson("",Configuration.class);
    }

    public String save(){
        return new Gson().toJson(instance);
    }

    public String getDatabaseName() {
        return databaseName;
    }

}
