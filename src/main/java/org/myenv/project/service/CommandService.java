package org.myenv.project.service;

import org.myenv.project.model.Application;

public interface CommandService<OS> {

    void install(Application application);
    void uninstall(Application application);
    void configure(Application application);
}
