package org.myenv.project.utils;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.myenv.project.model.OS;
import org.myenv.project.utils.os.OSUtil;

import java.io.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import static org.myenv.project.utils.os.OSUtil.os;

// TODO: Figure out how tie it to the abstract Command Class,
//  also need to triger the UAC
public class PersistentProcess {

    @Getter
    private static final PersistentProcess INSTANCE;

    private static final String FINISH_CUE_STRING = "_COMMAND_FINISHED_WRITING_TO_TERMINAL_";
    private static final String FINISH_CUE_PATTERN ="^" + FINISH_CUE_STRING + "$";
    private static final String COMMAND_FINISH_CUE = " & echo " + FINISH_CUE_STRING;

    static {
        try {
            OS os = OSUtil.getGeneralOS();
            INSTANCE = new PersistentProcess(new ProcessBuilder(os.getShell(), getShellCommandFlag()));
        } catch (IOException e) {
            System.out.println("Error while trying to instantiate terminal process");
            throw new RuntimeException(e);
        }
    }

    final Process process;
    final BufferedReader reader;
    final BufferedReader errorReader;
    final BufferedWriter writer;

    private PersistentProcess(ProcessBuilder processBuilder) throws IOException {
        this.process = processBuilder.start();
        this.errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        this.reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        this.writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
    }

    public CompletableFuture<String> runCommand(String command) {
        return CompletableFuture.supplyAsync(new ReaderStringProducer(command));
    }

    public void close() throws InterruptedException {
        Thread.sleep(2000L); // give time for output
        runCommand("exit");
        process.waitFor();
    }

    // these flags start a bare shell -> no info output at startup
    private static String getShellCommandFlag() {
        return OSUtil.isWindows() ? "/k" : "--norc --noprofile";
    }

    // starting a process with this flag, runs just a single command then the shell process exits
    private static String getSingleCommandFlag() {
        return os.isWindows() ? "/c" : "-c";
    }


    //TODO : implement observer/callback pattern, to avoid blocking calls and run a separate thread when calling a command
    private class ReaderStringProducer implements Runnable, Supplier<String> {

        @Setter
        @Getter
        private String output;

        private final String command;

        ReaderStringProducer(String command) {
            this.command = command;
        }

        @Override
        public void run() {
            runWithCommand(command);
        }

        private void runWithCommand(String command) {
            StringBuilder output = new StringBuilder();
            try {
                System.out.println("Running command: " + command);
                writer.write(command + COMMAND_FINISH_CUE);
                writer.newLine(); // equivalent to pressing Enter
                writer.flush();

                String line;
                String errorLine;
                while ((line = reader.readLine()) != null) {
                    if (line.matches(FINISH_CUE_PATTERN)) {
                        break;
                    }
                    if ((errorLine = errorReader.readLine()) != null) {
                        System.out.println(">" + errorLine);
                        break;
                    }
                    if (line.contains(command + COMMAND_FINISH_CUE) || line.equalsIgnoreCase("")) {
                        continue;
                    }
                    else {
                        output.append(line);
                        System.out.println("> " + line);
                    }
                }
            }
            catch (IOException e) {
                System.out.println("Error while trying to run command process");
                e.printStackTrace();
            }
            this.output = output.toString();
        }

        @Override
        public String get() {
            runWithCommand(command);
            return output;
        }
    }



}
