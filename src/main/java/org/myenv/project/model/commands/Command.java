package org.myenv.project.model.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

import java.util.LinkedHashSet;

@AllArgsConstructor
@Builder
public abstract class Command {

    private String mainCommand;
    private LinkedHashSet<CommandAction> actions;
    
}
