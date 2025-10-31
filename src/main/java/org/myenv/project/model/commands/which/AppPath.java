package org.myenv.project.model.commands.which;

import org.myenv.project.model.OS;
import org.myenv.project.model.commands.Command;
import org.myenv.project.utils.os.OSUtil;

import static org.myenv.project.model.commands.CommandAction.argument;

/**
 * Command to query if an application or program is installed
 * TODO: generify for windows use also, find another namings for Class name and methods
 */
public class AppPath extends Command {

    private AppPath() {
        super(OSUtil.os.equals(OS.WINDOWS) ? "where" : "which");
    }

    private static AppPath instance(String argument){
        return new AppPath().withAction(argument(argument));
    }

    public static String ask(String application) {
        return AppPath.instance(application).execute();
    }
}
