package org.myenv.project.model.commands;

import org.myenv.project.utils.PersistentProcess;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;


public abstract class Command {

    private final String mainCommand;
    private final HashMap<String, CommandAction> actions;
    private String finalCommand;

    public Command(String mainCommand) {
        this.mainCommand = mainCommand;
        this.actions = new HashMap<>(Map.of(" ", CommandAction.emptyAction()));
    }

    public Command(String mainCommand, HashMap<String, CommandAction> actions) {
        this(mainCommand);
        this.actions.putAll(actions);
    }

    public String execute() {
        return PersistentProcess.getINSTANCE().runCommand(finalCommand);
    }

    public <T extends Command> T withAction(CommandAction action) {
        replaceActionInMap(action);
        if (isNull(finalCommand))
            finalCommand = String.join(" ", mainCommand, action.getFinalAction().trim());
        else
            finalCommand = String.join(" ", finalCommand, action.getFinalAction().trim());
        return (T) this;
    }

    /** withActions resets the finalCommand and rebuilds it
     * with the actions given in the same order they are introduced
     * @param actions an array
     * @return <T> is the NamedCommand class that extends the Command class
     *  <br/> Example: new Remove("rm").withActions
     */
    public <T extends Command> T withActions(CommandAction... actions){
        finalCommand = null;
        for (CommandAction action : actions) {
            this.withAction(action);
        }
        return (T) this;
    }

    private void replaceActionInMap(CommandAction action){
        if (!actions.containsKey(action.getAction()))
            actions.put(action.getAction(), action);
        if (!actions.containsValue(action))
            actions.put(action.getAction(), action);
    }

    public String get() {
        return this.finalCommand;
    }

}
