package org.myenv.project.model.commands.which;

import org.myenv.project.model.commands.Command;

import static org.myenv.project.model.commands.CommandAction.emptyAction;

/**
 * Command to query if an application or program is installed
 * TODO: generify for windows use also, find another namings for Class name and methods
 */
public class Which extends Command {

    private Which() {
        super("which");
    }

    private static Which instance(String argument){
        return new Which().action(emptyAction().build(argument));
    };

    public static String ask(String application) {
        return Which.instance(application).execute();
    }
}
