package org.myenv.project.model;

import lombok.Data;

import java.net.URI;
import java.util.Optional;

@Data
public class GitRepo {

    private URI baseURL;
    private String repoURL;
    private String branchName;
    private String repoURLHistory;
    private boolean hasUpdateAccess;

    public GitRepo(String repoURL) {
        this.repoURL = Optional.ofNullable(baseURL).isPresent() ? baseURL + repoURL : repoURL;
    }

    public GitRepo(URI baseURL, String repoURL) {
        this.baseURL = baseURL;
        this.repoURL = repoURL;
    }

    public GitRepo(String repoURL, String branchName) {
        this.repoURL = repoURL;
        this.branchName = branchName;
    }

}
