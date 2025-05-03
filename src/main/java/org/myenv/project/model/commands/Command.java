package org.myenv.project.model.commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public abstract class Command {

    private final String mainCommand;
    private final HashMap<String, CommandAction> actions;
    private String finalCommand;

    public Command(String mainCommand) {
        this.mainCommand = mainCommand;
        this.actions = new HashMap<>(Map.of("empty", CommandAction.emptyAction()));
    }

    public Command(String mainCommand, HashMap<String, CommandAction> actions) {
        this(mainCommand);
        this.actions.putAll(actions);
    }

    public <T extends Command> T action(CommandAction action) {
        this.finalCommand = String.join(" ",mainCommand , action.getFinalAction().trim());
        return (T) this;
    }

    public String execute() {
        StringBuilder output = new StringBuilder();
        int exitCode = 0;
        try {
            // Start the process (Example: Running 'bash' or 'cmd' for interactive mode)
            ProcessBuilder builder = new ProcessBuilder("bash", "-c", finalCommand);
            builder.redirectErrorStream(true);

            Process process = builder.start();

            // Streams to interact with the process
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // Background thread to handle input reading
            String line;

            while ((line = reader.readLine()) != null) {
               output.append(line);
                System.out.println(line);// Print process output
            }

            // Wait for process to complete
            exitCode = process.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (exitCode == 1)
            return "ERROR while running Command: " + finalCommand;
        else return output.toString();
    }

    public String get() {
        return this.finalCommand;
    }



}
