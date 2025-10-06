package org.myenv.project.utils;

import java.io.*;

// TODO: Figure out how tie it to the abstract Command Class,
//  also need to triger the UAC
public class PersistentProcess {

    Process process;
    BufferedReader reader;
    BufferedWriter writer;
    Thread outputReader;
    public PersistentProcess(ProcessBuilder processBuilder) throws IOException {
        this.process = processBuilder.start();
        this.reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        this.writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        this.outputReader = getOutputReader(reader);
        this.outputReader.setDaemon(true);
        this.outputReader.start();
    }

    public void runCommand(String command) throws IOException {
        writer.write(command);
        writer.newLine(); // equivalent to pressing Enter
        writer.flush();
    }

    public void close() throws IOException, InterruptedException {
        Thread.sleep(2000L); // give time for output
        runCommand("exit");
        process.waitFor();
    }

    private Thread getOutputReader(BufferedReader reader) {
        return new Thread(() -> {
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    System.out.println("OUT> " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
