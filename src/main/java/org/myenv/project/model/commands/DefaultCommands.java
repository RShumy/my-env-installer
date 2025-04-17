package org.myenv.project.model.commands;

import org.myenv.project.utils.os.OSUtil;

import java.util.Objects;

import static org.myenv.project.model.OS.WINDOWS;

public final class DefaultCommands {

    public static String getElevated() {
        if (Objects.requireNonNull(OSUtil.os) == WINDOWS) {
            if (OSUtil.os.getShell().equals("powershell"))
                return "start-process -filepath powershell.exe -argumentlist @('-command','Set-ExecutionPolicy Bypass') -verb runas";
            return "runas /profile /user:Administrator cmd.exe";
        }
        return "sudo su";
    }

    public static String getCurrentPath(){
        if (Objects.requireNonNull(OSUtil.os.getShell()).equals("cmd")) {
                return "echo %cd%";
        }
        return "pwd";
    }
}
