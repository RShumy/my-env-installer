package org.myenv.project.model;

import lombok.Data;
import org.myenv.project.utils.properties.ConfigProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Data
public class PackageManager {

    private String osName;
    private String pmName;
    private String installURL;
    private String command;

    private Properties properties = ConfigProperties.packageManager;

    public PackageManager(OS os) {
        String osName = os.toString();
        this.pmName = properties.getProperty(osName + ".name");
        this.installURL = properties.getProperty(osName + ".url");
        this.command = properties.getProperty(osName + ".cmd");
    }

    private static HashMap<String, String> distroPackageManager = new HashMap<>(Map.ofEntries(
            Map.entry("debian", "apt"),
            Map.entry("suse", "zypper"),
            Map.entry( "rhel", "dnf"),
            Map.entry( "fedora", "dnf"),
            Map.entry( "centos", "dnf")
    ));

}
