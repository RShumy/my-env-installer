package org.myenv.project.model.commands;

import static java.util.Objects.isNull;
import static org.myenv.project.model.commands.FlagType.EMPTY;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;


public class CommandAction {

    @Getter
    private final String action;
    @Getter
    private HashMap<String, CommandFlag> flags;
    private CommandFlag currentFlag;
    @Getter
    private String finalAction;

    public CommandAction(String action) {
        this.action = action;
    }

    public CommandAction(String action, HashMap<String, CommandFlag> flags) {
        this.action = " " + action;
        this.flags = flags;
    }

    public static CommandAction emptyAction(){
       return new CommandAction(" ", new HashMap<>(Map.ofEntries(
               Map.entry(EMPTY.name(), CommandFlag.emptyFlag())
       )));
    }

    public static CommandAction emptyAction(String argument){
        return new CommandAction(" ", new HashMap<>()).build(argument);
    }

    public CommandAction putFlag(String name,CommandFlag flag){
        this.flags.put(name, flag);
        return this;
    }

    public CommandAction build(String argument){
        //TODO: null checks, ponder structure
        this.finalAction = String.join(" ", this.action.trim(), argument);
        return this;
    }

    public CommandAction build(CommandFlag flag, String... arguments){
        String flagString = flag.getFlag();
        if (this.flags.containsKey(flagString)) {
            this.finalAction = this.action + this.flags.get(flagString).getFinalFlag() + String.join(" ", arguments);
            return this;
        }
        else
            return build(arguments);
    }

    public CommandAction build(String... arguments){
        if ( !isNull(currentFlag) )
            buildCurrentFlag(arguments);
        else
            this.finalAction = String.join(" ", this.action, String.join(" ", arguments).trim());
        return this;
    }

    public CommandAction build(CommandFlag flag){
        //TODO: null checks, ponder structure
        this.currentFlag = flag;
        this.putFlag(flag.getFlag(), flag);
        buildCurrentFlag();
        return this;
    }

    public void buildCurrentFlag(){
        this.finalAction = String.join(" ", this.action.trim(), currentFlag.buildFinalFlag().trim());
    }

    public CommandAction buildCurrentFlag(String argument){
       buildCurrentFlag();
       this.finalAction = String.join(" ",this.finalAction, argument);
       return this;
    }


    public CommandAction buildCurrentFlag(String... arguments){
       buildCurrentFlag();
       this.finalAction = String.join(" ", finalAction.trim(), String.join(" ", arguments));
       return this;
    }


}
