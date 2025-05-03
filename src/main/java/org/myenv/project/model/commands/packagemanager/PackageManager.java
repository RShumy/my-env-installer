package org.myenv.project.model.commands.packagemanager;

import lombok.Getter;
import org.myenv.project.model.OS;
import org.myenv.project.model.commands.Command;
import org.myenv.project.utils.properties.ConfigProperties;

import java.util.Properties;


@Getter
public class PackageManager extends Command {

    private final String osName;
    private final String pmName;
    private final String installURL;

    private static final Properties properties = ConfigProperties.packageManager;

    public PackageManager(OS os) {
        super(properties.getProperty(os.name().toLowerCase() + ".cmd"));
        osName = os.name().toLowerCase();
        this.pmName = properties.getProperty(osName + ".name");
        this.installURL = properties.getProperty(osName + ".url");
    }

}
