package org.myenv.project.model;

import lombok.Data;

import java.util.Map;

@Data
public class Application {

    String name;
    // TODO: Maybe consider including version at installation
//    String version;
    OS operatingSystem;
    boolean justConfigure;
    Map<AppPathType, String> osConfigPaths;
    GitConfigRepo gitConfigRepo;

    public Application withName(String name) {
        this.name = name;
        return this;
    }

/*
    public Application withVersion(String version) {
        this.version = version;
        return this;
    }
*/

    public Application withOperatingSystem(OS operatingSystem) {
        this.operatingSystem = operatingSystem;
        return this;
    }

    public Application withJustConfigure(boolean justConfigure) {
        this.justConfigure = justConfigure;
        return this;
    }

    public Application withGitConfigRepo(GitConfigRepo gitConfigRepo) {
        this.gitConfigRepo = gitConfigRepo;
        return this;
    }

    public Application withOsConfigPaths(Map<AppPathType, String> osConfigPaths) {
        this.osConfigPaths = osConfigPaths;
        return this;
    }
}
