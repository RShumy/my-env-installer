package org.myenv.project.model.commands;

import lombok.Getter;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static sun.util.locale.LocaleUtils.isEmpty;

public abstract class CommandFlag {

    public String flag;
    public String description;
    private FlagType flagType;
    private String argument;
    @Getter
    private String finalFlag;


    CommandFlag withFlag(String flag) {
        this.flag = flag;
        return this;
    }

    CommandFlag withFlagType(FlagType flagType) {
        this.flagType = flagType;
        return this;
    }

    CommandFlag withArgument(String argument) {
        this.argument = argument;
        return this;
    }

    CommandFlag withDescription(String description) {
        this.description = description;
        return this;
    }

    String buildFinalFlag() {
        StringBuilder flag = new StringBuilder();
        if (isEmpty(argument)) {
            this.finalFlag = flag.append(flagType.getFlagPrefix()).append(this.flag).toString();
        }
        return this.finalFlag;
    }

    String buildFinalFlag(String argument) {
        StringBuilder flag = new StringBuilder();
        if (!isEmpty(argument))
            this.finalFlag = flag.append(flagType.getFlagPrefix())
                    .append(this.flag)
                    .append(flagType.getValueAssigner())
                    .append(argument).toString();
        else this.finalFlag = "";
        return this.finalFlag;
    }

}
