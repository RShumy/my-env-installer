package org.myenv.project.model;

import lombok.Getter;
import org.myenv.project.model.commands.packagemanager.PackageManager;

import static org.myenv.project.utils.os.LinuxInfo.resolveDistroType;

@Getter
public enum OS {

    WINDOWS("windows", ""),
    LINUX("linux", resolveDistroType()),
    MACOS("macOS", ""),
    UNIX("unix", ""),
    UNKNOWN("unknown", "");

    @Getter
    private final String osTypeAndVersion;

    private PackageManager packageManager;

    private String shell;

    OS(String name, String osType) {

        this.osTypeAndVersion = osType;

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

}
