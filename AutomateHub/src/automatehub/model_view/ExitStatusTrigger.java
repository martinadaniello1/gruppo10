package automatehub.model_view;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class ExitStatusTrigger extends Trigger {

    private String filePath;
    private Integer exitCodeDesired;
    private String[] commandLineArgs;

    public ExitStatusTrigger(String filePath, String[] commandLineArgs, Integer exitCode) {
        this.filePath = filePath;
        this.commandLineArgs = commandLineArgs;
        this.exitCodeDesired = exitCode;
    }

    public String[] getCommandLineArgs() {
        return commandLineArgs;
    }

    public void setCommandLineArgs(String[] commandLineArgs) {
        this.commandLineArgs = commandLineArgs;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getExitCodeDesired() {
        return exitCodeDesired;
    }

    public void setExitCodeDesired(Integer exitCodeDesired) {
        this.exitCodeDesired = exitCodeDesired;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExitStatusTrigger other = (ExitStatusTrigger) obj;
        if (!Objects.equals(this.filePath, other.filePath)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean check() {
        File f = new File(filePath);
        if (!f.exists()) {
            return false;
        } else {
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
                Integer actualExitCode = process.waitFor();
                System.out.println("Actual exit code:" + actualExitCode);
                if (actualExitCode.equals(exitCodeDesired)) {
                    return true;
                } else {
                    return false;
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public String getType() {
        return "When the program returns...";
    }

    @Override
    public String getParam1() {
        return this.getFilePath();
    }

    @Override
    public String getParam2() {
        return String.join(";", this.getCommandLineArgs());
    }

    @Override
    public String getParam3() {
        return this.getExitCodeDesired().toString();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Exit status desired: ");
        s.append(getExitCodeDesired().toString());
        s.append(" returned from program :");
        s.append(getFilePath());
        s.append(" with params: ");
        s.append(Arrays.toString(getCommandLineArgs()));
        return s.toString();
    }

}
