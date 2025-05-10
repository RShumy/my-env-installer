package org.myenv.project.utils.os;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinuxInfo {

    // only in case of Linux
    private final List<String> distroTypes = List.of("suse", "debian", "arch", "fedora", "centos", "rhel", "opensuse");

    private final static HashMap<String, String> distroPackageManager = new HashMap<>(Map.ofEntries(
            Map.entry("debian", "apt"),
            Map.entry("suse", "zypper"),
            Map.entry( "rhel", "yum"),
            Map.entry( "fedora", "yum")
    ));

    public static String resolveDistroType(){
        String distroType = "";
        if (System.getProperty("os.name").toLowerCase().contains("linux"))
            try (BufferedReader br = new BufferedReader(new FileReader("/etc/os-release"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.matches("^ID_LIKE")) {
                        distroType = line.split("=")[1].trim();
                        System.out.println(line);
                    }
                    runWhichYum();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return distroType;
    }

    public static void runWhichYum() {
        try {
            // Start the process (Example: Running 'bash' or 'cmd' for interactive mode)
            ProcessBuilder builder = new ProcessBuilder("which", "yum");
            Process process = builder.start();

            // Streams to interact with the process
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // Background thread to handle input reading
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("OUTPUT: " + line); // Print process output

                // Detect when the process asks for input (customize based on expected output)
            }

            // Wait for process to complete
            int exitCode = process.waitFor();
            System.out.println("Exited with process code: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
