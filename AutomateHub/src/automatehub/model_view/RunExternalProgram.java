package automatehub.model_view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class to run an external program.
 * 
 */
public class RunExternalProgram {

    /**
     * Utility method that runs an external Python program.
     * @param filePath the file path to run, must be a .py file
     * @param commandLineArgs the specified command line arguments to pass.
     * @return 
     */
    public static int runProgram(String filePath, String[] commandLineArgs) {
        Integer actualExitCode = null;
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("python", filePath);
            
            if (commandLineArgs != null && commandLineArgs.length > 0) {
                processBuilder.command().addAll(Arrays.asList(commandLineArgs));
            }
            
            processBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT);
            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
            
            Process process = processBuilder.start();
            readOutput(process);
            actualExitCode = process.waitFor();
            System.out.println("Actual exit code:" + actualExitCode);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(RunExternalProgram.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return actualExitCode;
    }
    
     private static void readOutput(Process process) throws IOException {
        try (InputStream inputStream = process.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            System.out.println("Output del comando:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

}
