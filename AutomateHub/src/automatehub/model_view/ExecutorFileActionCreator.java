/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automatehub.model_view;

/**
 *
 * @author mapic
 */
public class ExecutorFileActionCreator implements CreatorAction{
    private String filePath;
    private String[] arguments;

    public ExecutorFileActionCreator(String filePath, String[] arguments) {
        this.filePath = filePath;
        this.arguments =arguments;
    }

    @Override
    public Action create() {
        return new ExecutorFileAction(filePath, arguments);
    }
    
}
