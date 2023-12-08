package automatehub.model_view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class ExecutorFileAction extends Action {

    private String filePath;
    private String[] commandLineArgs;
    private int exitCode;

    public ExecutorFileAction(String filePath, String[] commandLineArgs) {
        this.filePath = filePath;
        this.commandLineArgs = commandLineArgs;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.filePath);
        hash = 59 * hash + Arrays.deepHashCode(this.commandLineArgs);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ExecutorFileAction other = (ExecutorFileAction) obj;
        return Objects.equals(this.filePath, other.filePath)
                && Arrays.deepEquals(this.commandLineArgs, other.commandLineArgs);
    }

    @Override
    public String toString() {
        return "Running the " + this.getFilePath() + " file";
    }

    @Override
    public int execute() {
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

            setExitCode(process.waitFor());
            System.out.println("Exit code: " + getExitCode());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
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

    

    @Override
    public String getType() {
        return "Execute external programm";
    }

    @Override
    public String getParam1() {
        return this.getFilePath();
    }

    @Override
    public String getParam2() {
        return String.join("; ", Arrays.asList(commandLineArgs));
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String[] getCommandLineArgs() {
        return commandLineArgs;
    }

    public void setCommandLineArgs(String[] commandLineArgs) {
        this.commandLineArgs = commandLineArgs;
    }

    public int getExitCode() {
        return exitCode;
    }

    public void setExitCode(int exitCode) {
        this.exitCode = exitCode;
    }
    
    
}
