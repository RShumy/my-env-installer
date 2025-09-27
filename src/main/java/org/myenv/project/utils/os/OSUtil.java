package org.myenv.project.utils.os;

import org.myenv.project.model.OS;

import java.util.List;

public class OSUtil {

    public final static String WINDOWS = "windows";
    public final static String LINUX = "linux";
    public final static String MAC = "mac";
    public final static String MACOS = "macOs";
    public final static String UNIX = "unix";

    public static final OS os = getGeneralOS();

    private static OS getGeneralOS() {
        return isWindows() ? OS.WINDOWS :
                isLinux() ? OS.LINUX :
                isMac() ? OS.MACOS :
                isUnix() ? OS.UNIX :
                        OS.UNKNOWN;
    }

    private static String getOSName() {
        return System.getProperty("os.name").toLowerCase();
    }

    public static boolean isWindows(){
        return getOSName().toLowerCase().contains(WINDOWS);
    }
    public static boolean isLinux(){
        return getOSName().toLowerCase().contains(LINUX);
    }
    public static boolean isMac(){
        return getOSName().toLowerCase().contains(MAC);
    }
    public static boolean isUnix(){
        return getOSName().toLowerCase().contains(UNIX);
    }
}
