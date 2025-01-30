package org.myenv.project.utils.os;

import org.myenv.project.model.OS;

public class OSUtil {

    public static final OS os = getGeneralOS();

    private static OS getGeneralOS() {
        String osName = getOSName();
        if (osName.contains("window")) {
            return OS.WINDOWS;
        }
        if (osName.contains("mac")) {
            return OS.MACOS;
        }
        if (osName.contains("linux")) {
            return OS.LINUX;
        }
        if (osName.contains("unix")) {
            return OS.UNIX;
        }
        else return OS.valueOf("unknown".toUpperCase());
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
