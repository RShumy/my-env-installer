package org.myenv.project.model.commands;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.util.Arrays;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class CommandFlag {

    @Getter
    private String flag;
    private String description;
    private FlagType flagType;
    private String argument;
    private String[] arguments;
    @Getter
    private String finalFlag;

    public static CommandFlag flag() {
        return new CommandFlag();
    }

    public static CommandFlag flag(String flag) {
        return new CommandFlag().withFlag(flag);
    }

    public CommandFlag(){}

    public CommandFlag withFlag(String flag) {
        this.flag = flag;
        return this;
    }

    public CommandFlag withFlagType(FlagType flagType) {
        this.flagType = flagType;
        return this;
    }

    public CommandFlag withArgument(String argument) {
        this.argument = argument;
        this.arguments = isNull(this.arguments) ? new String[]{argument} :
                Stream.concat(Arrays.stream(this.arguments), Stream.of(argument)).toArray(String[]::new);
        return this;
    }

     public CommandFlag withDescription(String description) {
        this.description = description;
        return this;
    }

     public CommandFlag withArguments(String... arguments) {
        if (!isEmpty(arguments))
            this.arguments = arguments;
        return this;
    }

     public String buildFinalFlag() {
        if (isEmpty(argument))
            this.finalFlag = appendFlag().toString();
        else
            this.finalFlag = String.join(" ", appendFlag(), argument);
        return this.finalFlag;
    }

     public String buildFinalFlag(String argument) {
        if (!isEmpty(argument))
            this.finalFlag = appendFlag()
                    .append(flagType.getValueAssigner())
                    .append(argument).toString();
        else this.finalFlag = buildFinalFlag();
        return this.finalFlag;
    }

     public String buildFinalFlag(String... arguments) {
        if (!isEmpty(arguments))
            this.finalFlag = appendFlag().append(" ").append(String.join(" ", arguments)).toString();
        return this.finalFlag;
    }

    private StringBuilder appendFlag() {
        return isNull(flag) ? flagPrefix().append(" ") : flagPrefix().append(this.flag);
    }

    private StringBuilder flagPrefix() {
        return new StringBuilder().append(flagType.getFlagPrefix());
    }

    public static CommandFlag emptyFlag() {
            return new CommandFlag().withFlagType(FlagType.EMPTY);
    }

}
