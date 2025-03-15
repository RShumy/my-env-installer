package org.myenv.project.model.commands;

import java.util.ArrayList;

// Not sure if I need this interface !?
public interface CommandBuilder<T extends Command> {

    T withActions(ArrayList<CommandAction> commandAction);
    T withFlags(ArrayList<CommandFlag> flags);
    T pipe(Command pipedCommand);
    T addActions(CommandAction commandAction);
    T addFlag(CommandFlag flag);


}
