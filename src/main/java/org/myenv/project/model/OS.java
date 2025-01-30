package org.myenv.project.model;

import lombok.Getter;
import lombok.Setter;

public enum OS {

    WINDOWS("windows"),
    LINUX("linux"),
    MACOS("macOS"),
    UNIX("unix"),
    UNKNOWN("unknown");

    private final String name;

    @Getter
    @Setter
    private PackageManager packageManager;

    @Getter
    @Setter
    private String shell;
    
    OS(String osName) {
        this.name = osName;
        this.packageManager = new PackageManager(this);
    }

    public String toString() {
        return name;
    }

}
