package org.myenv.project.model.commands.changedir;

import org.myenv.project.model.OS;
import org.myenv.project.model.commands.Command;
import org.myenv.project.model.commands.FlagType;
import org.myenv.project.utils.os.OSUtil;

import java.util.HashMap;
import java.util.Objects;

import static org.myenv.project.model.OS.WINDOWS;
import static org.myenv.project.model.commands.CommandAction.*;
import static org.myenv.project.model.commands.CommandFlag.flag;

public class ChangeDir extends Command {

    private static final String windowsDrivePath = "^[A-Z]:/[A-z0-9=-_ /\\\\(){}\\]\\[&%#@!~`,.]+";

    private ChangeDir() {
        super("cd", new HashMap<>());
    }

    public static ChangeDir changeDir(String path) {
        OS os = OSUtil.os;
        if (Objects.requireNonNull(os).equals(WINDOWS)
                && os.getShell().equals("cmd.exe")
                && path.matches(windowsDrivePath)) {
            return new ChangeDir().action(
                        emptyAction().build(flag("d").withFlagType(FlagType.SLASH).withArgument(path)));
        }
        return new ChangeDir().action(emptyAction(path));
    }
}
