package org.myenv.project.model.commands;

import lombok.Getter;


public enum FlagType {

    SHORT("-"),
    DOUBLE("--"),
    SLASH("/");

    /** A string with the characters that with determine the type of flag
     * "--" -> long descriptive flags
     * "/" -> typical for Windows CMD terminal commands
     * "-" -> short one char flag
     */
    @Getter
    private String flagPrefix;

    /** A string that will describe the character used to assign the value
     * "=" or ":" or " " -> space is considered default
     */
    @Getter
    private String valueAssigner = " ";

    @Getter
    // Typical for one char flags:
    private boolean canBeJoined;

    FlagType(String flagPrefix) {
        this.flagPrefix = flagPrefix;
    }

    FlagType withValueAssigner(String valueAssigner) {
        this.valueAssigner = valueAssigner;
        return this;
    }

    FlagType cannotBeJoined() {
        this.canBeJoined = false;
        return this;
    }

    FlagType canBeJoined() {
        this.canBeJoined = true;
        return this;
    }
}
