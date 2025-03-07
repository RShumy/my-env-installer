package org.myenv.project.model;

import lombok.Getter;
import lombok.Setter;

public enum OS {

    WINDOWS,
    LINUX,
    MACOS,
    UNIX,
    UNKNOWN;

    @Getter
    @Setter
    private PackageManager packageManager;

    @Getter
    @Setter
    private String shell;

    OS() {
        switch (this) {
            case WINDOWS -> this.withShell("cmd").withPackageManager(new PackageManager(this));
            case LINUX,UNIX -> this.withShell("bash").withPackageManager(new PackageManager(this));
            case MACOS -> this.withShell("zsh").withPackageManager(new PackageManager(this));
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

}
