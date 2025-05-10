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
                    if (line.contains("ID_LIKE")) {
                        distroType = line.split("=")[1].trim();
                        System.out.println(line);
                    }
                }
                runWhichYum();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return distroType;
    }

    public static void runWhichYum() {
        try {
            // Start the process (Example: Running 'bash' or 'cmd' for interactive mode)
            ProcessBuilder builder = new ProcessBuilder("bash", "-c", "which yum");
            builder.redirectErrorStream(true);

            Process process = builder.start();

            // Streams to interact with the process
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // Background thread to handle input reading
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("OUTPUT: " + line); // Print process output
            }

            // Wait for process to complete
            int exitCode = process.waitFor();
            if (exitCode == 1)
                System.out.println("YUM not found !");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
