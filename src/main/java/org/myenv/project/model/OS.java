package org.myenv.project.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum OS {

    WINDOWS("windows"),
    LINUX("linux"),
    MACOS("macOS"),
    UNIX("unix"),
    UNKNOWN("unknown");

    private PackageManager packageManager;

    private String shell;

    OS(String name) {
        switch (name.toUpperCase()) {
            case "WINDOWS" -> this.withShell("cmd").withPackageManager(new PackageManager(this));
            case "LINUX","UNIX" -> this.withShell("bash").withPackageManager(new PackageManager(this));
            case "MACOS" -> this.withShell("zsh").withPackageManager(new PackageManager(this));
            default -> this.shell = "unknown";
        }
    }

    public OS withShell(String shell) {
        this.shell = shell;
        return this;
    }

    public OS withPackageManager(PackageManager packageManager) {
        this.packageManager = packageManager;
        return this;
    }

    public String getName(){
        return this.name().toLowerCase();
    }

}
