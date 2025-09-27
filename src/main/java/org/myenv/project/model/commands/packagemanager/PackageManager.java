package org.myenv.project.model.commands.packagemanager;

import lombok.Getter;
import org.myenv.project.model.OS;
import org.myenv.project.model.commands.Command;
import org.myenv.project.utils.properties.ConfigProperties;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.myenv.project.utils.os.LinuxInfo.resolveLinuxPM;
import static org.myenv.project.utils.os.OSUtil.*;


@Getter
public class PackageManager extends Command {

    private final String osName;
    private final String pmName;
    private final String installURL;

    private static final Properties properties = ConfigProperties.packageManager;

    private static final List<String> winSupported = new ArrayList<>(List.of("choco"));
    private static final List<String> macSupported = new ArrayList<>(List.of("brew"));

    public PackageManager(OS os) {
        super(resolvePMCommand(os));
        osName = os.getName();
        this.pmName = properties.getProperty(osName + ".name");
        this.installURL = properties.getProperty(osName + ".url");
    }

    private static String resolvePMCommand(OS os) {
        String propertiesCommand = properties.getProperty(os.getName() + ".cmd");
        if (checkPropertiesCommand(os, propertiesCommand)) {
            return propertiesCommand;
        }
        // rethink resolving default PM for windows and mac, maybe collect all these in a Data Structures
        // especially if these are not installed
        return switch (os.getName()) {
            case WINDOWS -> winSupported.get(0);
            case LINUX -> resolveLinuxPM(os.getOsTypeAndVersion());
            case MAC -> macSupported.get(0);
            default -> null;
        };
    }

    private static boolean checkPropertiesCommand(OS os, String propertiesCommand) {
        return !isEmpty(propertiesCommand) && os.equals(OS.WINDOWS) ? winSupported.contains(propertiesCommand)
                : !isEmpty(propertiesCommand) && os.equals(OS.LINUX) ? propertiesCommand.equalsIgnoreCase(resolvePMCommand(os))
                : !isEmpty(propertiesCommand) && os.equals(OS.MACOS) && macSupported.contains(propertiesCommand);
    }


}
