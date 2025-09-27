package org.myenv.project.model;

import lombok.Getter;
import org.myenv.project.model.commands.packagemanager.PackageManager;
import org.myenv.project.utils.os.OSUtil;

import static org.myenv.project.utils.os.LinuxInfo.resolveDistroType;

@Getter
public enum OS {

    WINDOWS("windows", "windows"),
    LINUX("linux", resolveDistroType()),
    MACOS("macOS", "macOs"),
    UNIX("unix", "unix"),
    UNKNOWN("unknown", "");

    private final String osTypeAndVersion;

    private PackageManager packageManager;

    private String shell;

    OS(String name, String osType) {

        this.osTypeAndVersion = osType;

        switch (name) {
            /* TODO ? method for the user to choose the preferred Windows shell (cmd or powershell) */
            case OSUtil.WINDOWS ->
                this.withShell("cmd.exe")
                    .withPackageManager(new PackageManager(this));
            case OSUtil.LINUX, OSUtil.UNIX ->
                this.withShell("bash")
                        .withPackageManager(new PackageManager(this));
            case OSUtil.MACOS ->
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

    public boolean isWindows() {
        return this.equals(OS.WINDOWS);
    }

    public boolean isMacOS() {
        return this.equals(OS.MACOS);
    }

    public boolean isLinux() {
        return this.equals(OS.LINUX);
    }

}
