package automatehub.model_view;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents the action of executing an external program with specific command
 * line arguments. It is defined by the file path of the program and the command
 * line arguments.
 */
public class ExecutorFileAction extends Action {

    private String filePath;
    private String[] commandLineArgs;
    private int exitCode;

    public ExecutorFileAction(String filePath, String[] commandLineArgs) {
        this.filePath = filePath;
        this.commandLineArgs = commandLineArgs;
    }

    /**
     * Executes the external program with the specified command line arguments.
     */
    @Override
    public int execute() {
        setExitCode(RunExternalProgram.runProgram(filePath, commandLineArgs));
        System.out.println("Exit code: " + getExitCode());
        return 0;
    }

    @Override
    public String getType() {
        return "Execute external program";
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

}
