package org.myenv.project.model.commands;

import java.util.ArrayList;
import java.util.List;

public interface CommandBuilder<T extends Command> {

    T withActions(ArrayList<CommandAction> commandAction);
    T withFlags(ArrayList<CommandFlag> flags);
    T withArguments(ArrayList<CommandArgument> arguments);
    T pipe(Command pipedCommand);
    T addActions(CommandAction commandAction);
    T addFlag(CommandFlag flag);
    T addArgument(CommandArgument argument);

}
