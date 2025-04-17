package org.myenv.project.model.commands;

import java.util.HashMap;
import java.util.Map;


public abstract class Command {

    private final String mainCommand;
    private final HashMap<String, CommandAction> actions;
    private String finalCommand;

    public Command(String mainCommand) {
        this.mainCommand = mainCommand;
        this.actions = new HashMap<>(Map.of("empty", CommandAction.emptyAction()));
    }

    public Command(String mainCommand, HashMap<String, CommandAction> actions) {
        this(mainCommand);
        this.actions.putAll(actions);
    }

    public <T extends Command> T action(CommandAction action) {
        this.finalCommand = String.join(" ",mainCommand , action.getFinalAction().trim());
        return (T) this;
    }

    public String get() {
        return this.finalCommand;
    }



}
