package org.myenv.project.utils.os;

import org.myenv.project.model.commands.which.AppPath;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class LinuxInfo {

    private static final String redHatPM = resolveRedHatPM();

    // only in case of Linux
    private final static HashMap<String, String> distroPackageManager = new HashMap<>(Map.ofEntries(
            Map.entry("debian", "apt"),
            Map.entry("suse", "zypper"),
            Map.entry("arch", "pacman"),
            Map.entry("alpine", "apk"),
            Map.entry("gentoo", "emerge"),
            Map.entry( "rhel", redHatPM),
            Map.entry( "fedora", redHatPM),
            Map.entry( "centos", redHatPM)
    ));

    public static String resolveDistroType(){
        String distroType = "";
        if (OSUtil.isLinux())
            try (BufferedReader br = new BufferedReader(new FileReader("/etc/os-release"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.contains("ID_LIKE")) {
                        distroType = line.split("=")[1].split(" ")[0];
                        System.out.println(line);
                        System.out.println(distroType);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return distroType;
    }

    private static String resolveRedHatPM(){
        try {
            if (resolveDistroType().contains("rhel") || resolveDistroType().contains("fedora")) {
                if ( !AppPath.ask("dnf").contains("ERROR") )
                    return "dnf";
            }
            if ( !AppPath.ask("yum").contains("ERROR") )
                return "yum";
        }
        catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "dnf";
    }

    public static String resolveLinuxPM(String distroType){
        return distroPackageManager.getOrDefault(distroType, null);
    }
}
