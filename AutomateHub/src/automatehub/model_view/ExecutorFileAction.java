/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automatehub.model_view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author mapic
 */
public class ExecutorFileAction implements  Action {
    
    private String filePath;
    private String[] commandLineArgs;

    public ExecutorFileAction(String filePath, String[] commandLineArgs) {
        this.filePath = filePath;
        this.commandLineArgs = new String[commandLineArgs.length + 1];
        this.commandLineArgs[0] = filePath;
        System.arraycopy(commandLineArgs, 0, this.commandLineArgs, 1, commandLineArgs.length);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String[] getCmdCommand() {
        return commandLineArgs;
    }
    /*
    public void setCmdCommand(String commandLineArgs) {
        this.commandLineArgs.add(commandLineArgs);
    }*/

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.filePath);
        hash = 59 * hash + Objects.hashCode(this.commandLineArgs);
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
        final ExecutorFileAction other = (ExecutorFileAction) obj;
        if (!Objects.equals(this.filePath, other.filePath)) {
            return false;
        }
        if (!Objects.equals(this.commandLineArgs, other.commandLineArgs)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Running the" + this.getFilePath() + " file by " + this.getCmdCommand();
    }
    
    
    
    @Override
    public int execute() {
        
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            String fileExtension = getFileExtension(filePath);
            switch (fileExtension.toLowerCase()) {
                case "java":
                    processBuilder.command("java", "-jar", filePath);
                    break;
                case "c":
                    processBuilder.command("gcc", filePath, "-o", "output");
                    processBuilder.command("./output"); // Esegui il programma compilato
                    break;
                case "py":
                    processBuilder.command("python", filePath);
                    break;
                case "sh":
                    processBuilder.command("bash", filePath);
                default:
                    System.out.println("Linguaggio non supportato");
                    return -1;
            }
        
            //System.out.println(processBuilder.command().toString());
            
            // Usa la classe Runtime per eseguire il comando
            if(commandLineArgs.length!=0){
                processBuilder.command().addAll(Arrays.asList(commandLineArgs));
            }
            
            processBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT);
            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
            Process process = processBuilder.start();
            // Leggi l'output del processo
            readOutput(process);

            // Attendi che il processo termini
            int exitCode = process.waitFor();

            // Stampa il codice di uscita del processo
            System.out.println("Exit code: " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(ExecutorFileAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    private static void readOutput(Process process) throws IOException {
        // Leggi l'output dello stream di input del processo
        try (InputStream inputStream = process.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            System.out.println("Output del comando:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
    
    // Restituisci l'estensione del file
    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex == -1) {
            return ""; // Nessuna estensione trovata
        }
        return fileName.substring(lastDotIndex + 1);
    }

    @Override
    public String getType() {
        return "Execute file";
    }

    @Override
    public String getParam1() {
        return this.getFilePath();    
    }

    @Override
    public String getParam2() {
        return this.getCmdCommand().toString();
    }
    
}

