package org.myenv.project.utils.parsers;

import org.json.*;
import org.myenv.project.model.Config;
import org.myenv.project.model.OS;
import org.myenv.project.utils.os.OSUtil;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ParseConfigFile {

    File configFile;

    public ParseConfigFile(File configFile) {
        this.configFile = configFile;
    }

    public Config parseConfigFile() throws IOException {
        Path filePath = Paths.get(configFile.getPath());
        String fileContent = Files.readString(filePath);
        JSONObject jsonFile = new JSONObject(fileContent);
        OS osEnum = OSUtil.os;
        Config config = new Config()
            .withOperatingSystem(osEnum)
            .withGitBaseUrl(URI.create(jsonFile.getString("gitBaseURL")));
        JSONObject osJsonObject = jsonFile.getJSONObject("os");
        config
            .withUser(osJsonObject.getJSONObject(osEnum.toString()).getString("user"))
            .withApplications(
                ParseApplication.parseApps( config.getGitBaseUrl(), config.getOperatingSystem(),
                        osJsonObject.getJSONObject(osEnum.toString()).getJSONArray("applications")));
        return config;
    }




}
