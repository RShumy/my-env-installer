package org.myenv.project.model;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Getter
public enum OS {

    WINDOWS("windows", ""),
    LINUX("linux", resolveDistroType()),
    MACOS("macOS", ""),
    UNIX("unix", ""),
    UNKNOWN("unknown", "");

    private final String osName;

    private PackageManager packageManager;

    private String shell;

    OS(String name, String osName) {

        this.osName = osName;

        switch (name.toUpperCase()) {
            /* TODO ? method for the user to choose the preferred Windows shell (cmd or powershell) */
            case "WINDOWS" ->
                this.withShell("cmd")
                    .withPackageManager(new PackageManager(this));
            case "LINUX","UNIX" ->
                this.withShell("bash").withPackageManager(new PackageManager(this));
            case "MACOS" ->
                this.withShell("zsh")
                    .withPackageManager(new PackageManager(this));
            default ->
                this.shell = "unknown";
        }
    }

    public OS withShell(String shell) {
        this.shell = shell;
        return this;
    }

    public void withPackageManager(PackageManager packageManager) {
        this.packageManager = packageManager;
    }



    public String getName(){
        return this.name().toLowerCase();
    }

    // only in case of Linux
    private final List<String> distroTypes = List.of("suse", "debian", "arch", "fedora", "centos", "rhel");


    private static String resolveDistroType(){
        String distroType = "";
        try (BufferedReader br = new BufferedReader(new FileReader("/etc/os-release"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.matches("^ID_LIKE"))
                    distroType = line.split("=")[1].trim();
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return distroType;
    }

}
