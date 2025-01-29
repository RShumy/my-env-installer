package org.myenv.project.model;

public enum OS {

    WINDOWS("windows"),
    LINUX("linux"),
    MACOS("macOS");

    private final String name;
    
    OS(String osName) {
        this.name = osName;
    }
    
    public String toString() {
        return name;
    }
}
