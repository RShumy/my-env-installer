package org.myenv.project;

import org.myenv.project.model.Config;
import org.myenv.project.utils.os.OSUtil;
import org.myenv.project.utils.parsers.ParseConfigFile;

import java.io.File;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {

        // Convert the URL to a File object
        File configFile = new File("src/main/resources/EnvironmentConfig.json");
        Config config = new ParseConfigFile(configFile).parseConfigFile();
        System.out.println(OSUtil.OS);
        System.out.println("Config Parsed :" + config);
    }
}