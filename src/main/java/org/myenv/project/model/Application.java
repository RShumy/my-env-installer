package org.myenv.project.model;

import java.nio.file.Path;
import java.util.Map;

public class Application {

    String name;
    // TODO: Maybe consider including version at installation
    String version;
    OS operatingSystem;
    boolean justConfigure;
    Map<AppPathType, Path> osConfigPaths;
    GitConfigRepo gitConfigRepo;

}
