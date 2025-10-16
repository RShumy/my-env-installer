package org.myenv.project.model.commands.filesystem;

import org.myenv.project.model.OS;
import org.myenv.project.model.commands.Command;
import org.myenv.project.model.commands.FlagType;
import org.myenv.project.utils.os.OSUtil;

import java.util.HashMap;

import static org.myenv.project.model.commands.CommandAction.*;
import static org.myenv.project.model.commands.CommandFlag.flag;

public class Directory extends Command {

    private static final String windowsDrivePath = "^[A-Z]:/[A-z0-9=-_ /\\\\(){}\\]\\[&%#@!~`,.]+";

    private Directory() {
        super("cd", new HashMap<>());
    }
    private Directory(String command){ super(command, new HashMap<>()); }

    public static Directory changeDir(String path) {
        OS os = OSUtil.os;
        if (OSUtil.isWindows() &&
                os.getShell().equals("cmd.exe") &&
                path.matches(windowsDrivePath)) {
            return new Directory().action(
                        emptyAction().build(flag("d").withFlagType(FlagType.SLASH).withArgument(path)));
        }
        return new Directory().action(emptyAction(path));
    }

    public static Directory currentPath() {
        OS os = OSUtil.os;
        if (OSUtil.isWindows()) {
            return new Directory("cd").action(emptyAction().build());
        }
        else {
            return new Directory("pwd").action(emptyAction().build());
        }
    }
}
