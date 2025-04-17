package org.myenv.project.model.commands.link;

import static org.myenv.project.model.commands.CommandFlag.flag;

import java.util.Map;

import org.myenv.project.model.commands.CommandAction;
import org.myenv.project.model.commands.FlagType;

import lombok.Getter;

public enum LinkLinuxEntries {

    SYMBOLIC("s");

    private final CommandAction action;

    @Getter
    private final Map.Entry<String, CommandAction> entry;
    
    LinkLinuxEntries(String option) {
        this.action = CommandAction.emptyAction()
            .build(flag(option).withFlagType(FlagType.SHORT));
        this.entry = Map.entry(option, this.action);
    }

    public CommandAction get(){
        return this.action;
    }

}
