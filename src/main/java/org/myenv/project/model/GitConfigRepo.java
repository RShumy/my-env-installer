package org.myenv.project.model;

import lombok.Data;

@Data
public class GitConfigRepo {


    private String repoURL;
    private String branchName;
    private String repoURLHistory;
    private boolean hasUpdateAccess;

    public GitConfigRepo(String repoURL) {
        this.repoURL = repoURL;
    }

    public GitConfigRepo(String repoURL, String branchName) {
        this.repoURL = repoURL;
        this.branchName = branchName;
    }

}
