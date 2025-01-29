package org.myenv.project.utils.os;

public class OSUtil {

    public static String OS = getGeneralOS();

    private static String getGeneralOS() {
        String osName = getOSName();
        if (osName.contains("window")) {
            return "windows";
        }
        if (osName.contains("mac")) {
            return "macOS";
        }
        if (osName.contains("linux")) {
            return "linux";
        } else return "unix";
    }

    private static String getOSName() {
        return System.getProperty("os.name").toLowerCase();
    }

    private static boolean isWindows() {
        return (OS.equals("windows"));
    }

    private static boolean isMacOS() {
        return (OS.equals("macOS"));
    }

    private static boolean isLinux() {
        return (OS.equals("linux"));
    }
}
