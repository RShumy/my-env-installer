package org.myenv.project.model;

public enum AppPathType {

    DESTINATION("destination"),
    LOCATION("location");

    private final String value;
    AppPathType(String value) {
        this.value = value;
    }

    public String toLowerCase() {
        return value;
    }
}
