package org.myenv.project.utils.properties;

import org.myenv.project.model.PackageManager;

import java.io.IOException;
import java.io.InputStream;

public class ConfigProperties {

    public static final java.util.Properties packageManager = new java.util.Properties();

    // Load properties once at startup
    static {
        try (InputStream input = PackageManager.class.getClassLoader()
                .getResourceAsStream("packageManagerDefaults.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find packageManagerDefaults.properties");
            }
            packageManager.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties file", e);
        }
    }
}
