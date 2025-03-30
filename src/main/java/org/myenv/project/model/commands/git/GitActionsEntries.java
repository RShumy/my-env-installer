package org.myenv.project.model.commands.git;

import lombok.Getter;
import org.myenv.project.model.commands.CommandAction;

import java.util.Map;

public enum GitActionsEntries {

    ADD("add"),
    BRANCH("branch"),
    CLONE("clone"),
    COMMIT("commit"),
    CHECKOUT("checkout"),
    PUSH("push"),
    SWITCH("switch");

    @Getter
    private final Map.Entry<String, CommandAction> entry;

    private final CommandAction action;

    GitActionsEntries(String action) {
        this.action = new CommandAction(action);
        this.entry = Map.entry(action, this.action);
    }

    public CommandAction get() {
        return action;
    }

    public String action() {
        return entry.getKey();
    }

}
