package org.myenv.project.model.commands;

import static java.util.Objects.isNull;
import static org.myenv.project.model.commands.FlagType.EMPTY;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

/** Stands for a terminal sub command that is passed to the main command */
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

    /**
     * Static argument() serves as a static instantiation of
     * an empty CommandAction with only one argument that calls
     * withArgument()
     * @param argument that is being passed to the Command
     * @return CommandAction
     */
    public static CommandAction argument(String argument){
        return new CommandAction(" ", new HashMap<>()).withArgument(argument);
    }

    public CommandAction putFlag(String name,CommandFlag flag){
        flags.put(name, flag);
        return this;
    }

    /** withArgument has the purpose to build the Action with a simple argument
     * @param argument will be appended to the action string to compose the final action
     *                 Ex: withArgument("anArgument")<br/>
     *                 <code>finalAction = "action anArgument"<code/>
     * @return CommandAction
     */
    public CommandAction withArgument(String argument){
        if (isNull(argument)) {
            throw new NullPointerException("Exception while building an command action, argument cannot be null");
        }
        finalAction = String.join(" ", action.trim(), argument);
        return this;
    }

    public CommandAction withFlag(CommandFlag flag, String... arguments){
        String flagString = flag.getFlag();
        if (flags.containsKey(flagString)) {
            finalAction = action + flags.get(flagString).getFinalFlag() + String.join(" ", arguments);
            return this;
        }
        else
            return withArguments(arguments);
    }

    public CommandAction withArguments(String... arguments){
        if ( !isNull(currentFlag) )
            return buildCurrentFlag(arguments);
        else
            finalAction = String.join(" ", action, String.join(" ", arguments).trim());
        return this;
    }

    public CommandAction withFlag(CommandFlag flag){
        //TODO: null checks, ponder structure
        currentFlag = flag;
        return putFlag(flag.getFlag(), flag).buildCurrentFlag();
    }

    public CommandAction buildCurrentFlag(String argument){
       buildCurrentFlag();
       finalAction = String.join(" ",finalAction, argument);
       return this;
    }

    private CommandAction buildCurrentFlag(){
        if (isNull(finalAction)) {
            finalAction = String.join(" ", action.trim(), currentFlag.buildFinalFlag().trim());
        } else {
            finalAction = String.join(" ", finalAction, currentFlag.buildFinalFlag());
        }
        return this;
    }


    private CommandAction buildCurrentFlag(String... arguments){
       buildCurrentFlag();
       finalAction = String.join(" ", finalAction.trim(), String.join(" ", arguments));
       return this;
    }


}
