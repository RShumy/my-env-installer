package org.myenv.project.model;

import lombok.Data;
import org.myenv.project.utils.properties.ConfigProperties;

import java.util.Properties;

@Data
public class PackageManager {

    private String pmName;
    private String installURL;
    private String command;

    private Properties properties = ConfigProperties.packageManager;

    PackageManager(OS os) {
        String osName = os.toString();
        this.pmName = properties.getProperty(osName + ".name");
        this.installURL = properties.getProperty(osName + ".url");
        this.command = properties.getProperty(osName + ".cmd");
    }


}
