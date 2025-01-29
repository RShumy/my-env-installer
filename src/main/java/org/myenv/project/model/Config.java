package org.myenv.project.model;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Config {

    String name;
    String gitBaseUrl;
    OS operatingSystem;
    List<Application> applications;
    Path userPath;
}
