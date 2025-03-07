package org.myenv.project.utils.os;

import org.myenv.project.model.OS;

public class OSUtil {

    public static final OS os = getGeneralOS();

    private static OS getGeneralOS() {
        String osName = getOSName();
        return osName.contains("window") ? OS.WINDOWS :
                osName.contains("linux") ? OS.LINUX :
                osName.contains("mac") ? OS.MACOS :
                osName.contains("unix") ? OS.UNIX :
                        OS.UNKNOWN;
    }

    private static String getOSName() {
        return System.getProperty("os.name").toLowerCase();
    }

    private static boolean isWindows() {
        return (os.toString().equals("windows"));
    }

    private static boolean isMacOS() {
        return (os.toString().equals("macOS"));
    }

    private static boolean isLinux() {
        return (os.toString().equals("linux"));
    }
}
