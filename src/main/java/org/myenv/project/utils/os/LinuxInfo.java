package org.myenv.project.utils.os;

import org.myenv.project.model.commands.which.Which;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LinuxInfo {

    // only in case of Linux
    private final static HashMap<String, String> distroPackageManager = new HashMap<>(Map.ofEntries(
            Map.entry("debian", "apt"),
            Map.entry("suse", "zypper"),
            Map.entry("arch", "pacman"),
            Map.entry("apline", "apk"),
            Map.entry("gentoo", "emerge"),
            Map.entry( "rhel", resolveRedHat()),
            Map.entry( "fedora", resolveRedHat()),
            Map.entry( "centos", resolveRedHat())
    ));

    public static String resolveDistroType(){
        String distroType = "";
        if (System.getProperty("os.name").toLowerCase().contains("linux"))
            try (BufferedReader br = new BufferedReader(new FileReader("/etc/os-release"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.contains("ID_LIKE")) {
                        distroType = line;
                        System.out.println(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return distroType;
    }


    public static String resolveRedHat(){
        if (resolveDistroType().contains("rhel") || resolveDistroType().contains("fedora")) {
            String dnf = Which.ask("dnf");
            if (!dnf.contains("ERROR"))
                return dnf;
            String yum = Which.ask("yum");
            if (!yum.contains("ERROR"))
                return yum;
        }
        return "dnf";
    }
}
