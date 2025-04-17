package org.myenv.project.model.commands.link;

import static org.myenv.project.model.commands.CommandAction.emptyAction;
import static org.myenv.project.model.commands.CommandFlag.flag;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.myenv.project.model.commands.CommandAction;
import org.myenv.project.model.commands.FlagType;

import lombok.Getter;

public enum LinkWindowsEntries {

    FILE(""),
    DIR("d");

    private final CommandAction action;

    @Getter
    private final Map.Entry<String, CommandAction> entry;
    
    LinkWindowsEntries(String option) {

        this.action = StringUtils.isEmpty(option) ? emptyAction() :
            emptyAction()
            .build(flag(option).withFlagType(FlagType.SLASH));
        this.entry = Map.entry(option, this.action);
    }

    public CommandAction get(){
        return this.action;
    }

}
