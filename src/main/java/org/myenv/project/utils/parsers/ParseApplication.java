package org.myenv.project.utils.parsers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.myenv.project.model.AppPathType;
import org.myenv.project.model.Application;
import org.myenv.project.model.GitConfigRepo;
import org.myenv.project.model.OS;

import java.net.URI;
import java.util.*;

import static org.myenv.project.model.AppPathType.DESTINATION;
import static org.myenv.project.model.AppPathType.LOCATION;

class ParseApplication {

    static Application parseApp(URI gitBaseURL, OS os,
                             JSONObject jsonObject) {
        Application application = new Application()
                .withOperatingSystem( os )
                .withName(
                    Optional.ofNullable(jsonObject.getString("name")).orElse("")
                );
        application = jsonObject.has("justConfig") ?
                application.withJustConfigure(
                        jsonObject.getBoolean("justConfig") )
                : application.withJustConfigure(false);
        application = jsonObject.has("destination") && jsonObject.has("location") ?
                application.withOsConfigPaths(
                    parseAppPaths(jsonObject) )
                : application;
        application = jsonObject.has("gitRepo") ?
                application.withGitConfigRepo(
                    new GitConfigRepo(gitBaseURL, jsonObject.getString("gitRepo")) )
                : application;
        return application;
    }

    static Map<AppPathType, String> parseAppPaths(JSONObject jsonObject) {
        Map<AppPathType, String> appPaths = new HashMap<>();
        if ( jsonObject.has(DESTINATION.toLowerCase()) ) {
            appPaths.put(
                    DESTINATION,
                    Optional.ofNullable(jsonObject.getString(DESTINATION.toLowerCase())).orElse(""));
        }
        if (jsonObject.has(LOCATION.toLowerCase()) ) {
            appPaths.put(
                    LOCATION,
                    Optional.ofNullable(jsonObject.getString(LOCATION.toLowerCase())).orElse("")
            );
        }
        return appPaths;
    }

    static List<Application> parseApps(URI gitBaseURL, OS os, JSONArray jsonArray) {
        List<Application> apps = new ArrayList<>();
        jsonArray.forEach(
                jsonObject ->
                        apps.add(
                                parseApp(gitBaseURL, os, (JSONObject) jsonObject)
                        ));
        return apps;
    }
}
