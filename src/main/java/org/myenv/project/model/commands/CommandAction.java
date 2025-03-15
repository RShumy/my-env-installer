package org.myenv.project.model.commands;

import java.util.LinkedHashSet;

public abstract class CommandAction {

    String action;
    LinkedHashSet<CommandFlag> flags;
    String finalAction;

    CommandAction defaultEmptyAction(){
        this.action = "";
        this.flags = new LinkedHashSet<>();
        return this;
    }

    CommandAction buildSimpleAction(String argument){
        //TODO: null checks, ponder structure
        this.finalAction = this.action + " " + argument;
        return this;
    }

    CommandAction buildSimpleAction(CommandFlag flag){
        //TODO: null checks, ponder structure
        this.finalAction = this.action + flag.getFinalFlag();
        return this;
    }

}
