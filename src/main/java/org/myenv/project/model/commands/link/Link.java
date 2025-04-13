package org.myenv.project.model.commands.link;

import static org.myenv.project.model.commands.link.LinkLinuxEntries.SYMBOLIC;
import static org.myenv.project.model.commands.link.LinkWindowsEntries.DIR;
import static org.myenv.project.model.commands.link.LinkWindowsEntries.FILE;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.myenv.project.model.commands.Command;
import org.myenv.project.model.commands.CommandAction;
import org.myenv.project.utils.os.OSUtil;


public class Link extends Command {

    private Link(){
        super(osSpecificCommand(), osSpecificMap());
    }

    public static Link symbolic(String origin, String destination) {
        Path path = Paths.get(origin);
        switch (OSUtil.os) {
            case WINDOWS -> {
                if (Files.isDirectory(path))
                    return new Link().action(DIR.get().buildCurrentFlag(origin, destination));
                else
                    return new Link().action(FILE.get().build(origin, destination));
            }
            default -> {
                return new Link().action(SYMBOLIC.get().build(origin, destination));
            }
        }
    }

    private static HashMap<String, CommandAction> osSpecificMap() {
        // TODO: Maybe add more Entries 
        switch (OSUtil.os) {
            case UNIX, LINUX, MACOS ->  { 
                return new HashMap<>(
                        Map.ofEntries(
                            SYMBOLIC.getEntry()
                        ));  
            } 
            case WINDOWS -> {
                return new HashMap<>(
                        Map.ofEntries(
                            FILE.getEntry(), 
                            DIR.getEntry()
                        ));
            }
            default -> { 
                return new HashMap<>();
            }
        }
    }

    private static String osSpecificCommand(){
        switch (OSUtil.os) {
            case LINUX, UNIX, MACOS ->  { return "ln"; }
            case WINDOWS -> { return "mklink"; }
            default -> { return ""; }
        }
    }

}
