package org.myenv.project;

import org.myenv.project.model.Config;
import org.myenv.project.model.OS;
import org.myenv.project.model.commands.changedir.ChangeDir;
import org.myenv.project.model.commands.git.Git;
import org.myenv.project.model.commands.link.Link;
import org.myenv.project.utils.os.OSUtil;
import org.myenv.project.utils.parsers.ParseConfigFile;

import java.io.File;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {

        // Convert the URL to a File object
        File configFile = new File("src/main/resources/EnvironmentConfig.json");
        OS os = OSUtil.os;
        Config config = new ParseConfigFile(configFile).parseConfigFile();
        System.out.println(os);
        System.out.println(os.getPackageManager());
        System.out.println(os.getShell());
        System.out.println("Config Parsed :" + config);
        System.out.println(config.getGitBaseUrl().toString());
        System.out.println(Git.clone(config.getGitBaseUrl().toString()).get());
        System.out.println(Link.symbolic("d:/JavaProjects", "d:/path/destination").get());
        System.out.println(ChangeDir.changeDir("D:/JavaProjects").get());
    }
}