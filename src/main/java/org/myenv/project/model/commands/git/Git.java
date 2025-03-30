package org.myenv.project.model.commands.git;

import org.myenv.project.model.commands.Command;

import java.util.HashMap;
import java.util.Map;

import static org.myenv.project.model.commands.CommandFlag.flag;
import static org.myenv.project.model.commands.FlagType.*;
import static org.myenv.project.model.commands.git.GitActionsEntries.*;

public class Git extends Command {

    private Git(){
        super("git", new HashMap<>(
                Map.ofEntries(CLONE.getEntry(),
                        COMMIT.getEntry(),
                        BRANCH.getEntry(),
                        ADD.getEntry(),
                        PUSH.getEntry(),
                        SWITCH.getEntry()) )
        );
    }

    public static Git addAll(){
        return new Git().action(ADD.get().build(flag("all").withFlagType(DOUBLE)));
    }

    public static Git clone(String gitRepo){
        return new Git().action(CLONE.get().build(gitRepo));
    }

    public static Git clone(String gitRepo, String branch, String customPath){
        return new Git().action(CLONE.get().build(flag("b").withFlagType(SHORT).withArgument(gitRepo).withArgument(branch).withArgument(customPath)));
    }

}
