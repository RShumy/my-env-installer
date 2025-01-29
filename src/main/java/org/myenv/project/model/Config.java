package org.myenv.project.model;

import lombok.Data;

import java.net.URI;
import java.nio.file.Path;
import java.util.List;
@Data
public class Config {

    String name;
    URI gitBaseUrl;
    OS operatingSystem;
    String user;
    List<Application> applications;
    Path userPath;

    public Config(){}

    public Config withName(String name) {
        this.name = name;
        return this;
    }

    public Config withGitBaseUrl(URI gitBaseUrl) {
        this.gitBaseUrl = gitBaseUrl;
        return this;
    }

    public Config withOperatingSystem(OS operatingSystem) {
        this.operatingSystem = operatingSystem;
        return this;
    }

    public Config withUser(String user) {
        this.user = user;
        return this;
    }

    public Config withApplications(List<Application> applications) {
        this.applications = applications;
        return this;
    }

    public Config withUserPath(Path userPath) {
        this.userPath = userPath;
        return this;
    }

    public Config addApplication(Application application) {
        if (!this.applications.isEmpty()) {
            this.applications.add(application);
        }
        return this;
    }
}
