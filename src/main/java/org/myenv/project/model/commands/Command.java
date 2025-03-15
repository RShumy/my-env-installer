package org.myenv.project.model.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.HashMap;

@AllArgsConstructor
@Builder
public abstract class Command {

    private String mainCommand;
    private HashMap<String, CommandAction> actions;

    public Command(String mainCommand) {
        this.mainCommand = mainCommand;
    }


}
