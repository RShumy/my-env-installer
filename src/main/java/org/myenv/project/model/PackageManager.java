package org.myenv.project.model;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Properties;

@Data
public class PackageManager {

    private String pmName;
    private String installURL;
    private String command;

    private static final Properties properties = new Properties();

    // Load properties once at startup
    static {
        try (InputStream input = PackageManager.class.getClassLoader()
                                .getResourceAsStream("packageManagerDefaults.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find packageManagerDefaults.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties file", e);
        }
    }

    PackageManager(OS os) {
        String osName = os.toString();
        this.pmName = properties.getProperty(osName + ".name");
        this.installURL = properties.getProperty(osName + ".url");
        this.command = properties.getProperty(osName + ".cmd");
    }


}
