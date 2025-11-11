package org.myenv.project.model.commands.git;

import org.myenv.project.model.commands.Command;
import org.myenv.project.model.commands.CommandFlag;

import java.util.HashMap;
import java.util.Map;

import static org.myenv.project.model.commands.CommandFlag.flag;
import static org.myenv.project.model.commands.FlagType.*;
import static org.myenv.project.model.commands.git.GitActionsEntries.*;

public class Git extends Command {

    private static final CommandFlag progressFlag = flag("progress").withFlagType(DOUBLE);

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
        return new Git().withAction(ADD.get().withFlag(flag("all").withFlagType(DOUBLE)));
    }

    public static Git clone(String gitRepo){
        return new Git().withAction(CLONE.get().withFlag(progressFlag, gitRepo));
    }

    public static Git clone(String gitRepo, String branch, String customPath){
        String description = "-b flag stands for branch and enables choosing a specific branch when cloning";
        return new Git().withAction(CLONE.get().withFlag(progressFlag).withFlag(flag("b").withFlagType(SHORT).withArgument(gitRepo).withArgument(branch).withArgument(customPath)));
    }

    public static Git clone(String gitRepo, String branch){
        return clone(gitRepo, branch, "");
    }
}
