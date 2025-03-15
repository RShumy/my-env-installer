package org.myenv.project.model.commands;

import lombok.Getter;

import java.util.Arrays;

import static java.util.Objects.isNull;
import static sun.util.locale.LocaleUtils.isEmpty;

public abstract class CommandFlag {

    public String flag;
    public String description;
    private FlagType flagType;
    private String argument;
    private String[] arguments;
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

    CommandFlag withArguments(String... arguments) {
        this.arguments = arguments;
        return this;
    }

    String buildFinalFlag() {
        if (isEmpty(argument)) {
            this.finalFlag = appendFlag().toString();
        }
        return this.finalFlag;
    }

    String buildFinalFlag(String argument) {
        if (!isEmpty(argument))
            this.finalFlag = appendFlag()
                    .append(flagType.getValueAssigner())
                    .append(argument).toString();
        else this.finalFlag = buildFinalFlag();
        return this.finalFlag;
    }

    String buildFinalFlag(String... arguments) {
        this.finalFlag = appendFlag().append(Arrays.stream(arguments).reduce(" ", (arg1, arg2) -> arg1 + " " + arg2 + " ")).toString();
        return this.finalFlag;
    }

    private StringBuilder appendFlag() {
        return isNull(flag) ? flagPrefix().append(" ") : flagPrefix().append(this.flag);
    }

    private StringBuilder flagPrefix() {
        return new StringBuilder().append(flagType.getFlagPrefix());
    }

    public static class EmptyFlag extends CommandFlag {
        EmptyFlag() {
            super.withFlagType(FlagType.EMPTY).buildFinalFlag();
        }
    }

}
