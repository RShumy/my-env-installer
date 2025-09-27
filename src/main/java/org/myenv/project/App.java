package org.myenv.project;

import org.myenv.project.model.Config;
import org.myenv.project.model.OS;
import org.myenv.project.model.commands.changedir.ChangeDir;
import org.myenv.project.model.commands.git.Git;
import org.myenv.project.utils.os.OSUtil;
import org.myenv.project.utils.parsers.ParseConfigFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class App {

    public static void main(String[] args) throws IOException, URISyntaxException {

        File jarFile = new File(App.class.getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI());

        // Get the directory that contains the JAR
        File jarDir = jarFile.getParentFile().getParentFile();

        // Resolve the file in the same directory
        File configFile = new File(jarDir, "EnvironmentConfig.json");

        // Convert the URL to a File object
//        File configFile = new File("EnvironmentConfig.json");
        OS os = OSUtil.os;
        Config config = new ParseConfigFile(configFile).parseConfigFile();
        System.out.println(os);
        System.out.println(os.getPackageManager().getPmName());
        System.out.println(os.getShell());
        System.out.println("Config Parsed: " + config);
        System.out.println(config.getGitBaseUrl().toString());
        System.out.println(Git.clone(config.getGitBaseUrl().toString()).get());
        ChangeDir.changeDir("D:/JavaProjects").execute();
    }
}