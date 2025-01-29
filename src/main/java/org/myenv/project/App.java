package org.myenv.project;

import org.myenv.project.utils.os.OSUtil;
import org.myenv.project.utils.parsers.ParseConfigFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class App {

     static ParseConfigFile config;

    public static void main(String[] args) throws IOException {

        // Convert the URL to a File object
        File configFile = new File("src/main/resources/EnvironmentConfig.json");
        ParseConfigFile parseConfigFile = new ParseConfigFile(configFile);
        parseConfigFile.parseJsonFile();
        System.out.println(OSUtil.OS);
    }
}