package org.myenv.project.utils.parsers;

import org.json.*;
import org.myenv.project.model.Application;
import org.myenv.project.model.OS;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class ParseConfigFile {

    File configFile;
    Map<OS, List<Application>> applications;

    public ParseConfigFile(File configFile) {
        this.configFile = configFile;
    }

    public void parseJsonFile() throws IOException {
            Path filePath = Paths.get(configFile.getPath());
            String fileContent = Files.readString(filePath);
//            String replaced = fileContent.replaceFirst("\s+[A-z]+\s+$", "Mata-i Grasa");
            JSONObject jsonFile = new JSONObject(fileContent);

            System.out.println(jsonFile);
    }


}
