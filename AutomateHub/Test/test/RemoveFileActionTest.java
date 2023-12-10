package test;

import automatehub.model_view.action.RemoveFileAction;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import org.junit.*;


public class RemoveFileActionTest {
    private String filePath;
    private RemoveFileAction removeFileAction;
    
    @Before
    public void setUp(){
        filePath="./Test/test/testFiles/file_to_remove.txt";
        removeFileAction = new RemoveFileAction(filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();  // Crea le directory padre se non esistono
            }
            try {
                file.createNewFile();  // Crea il file se non esiste
            } catch (IOException ex) {
                Logger.getLogger(RemoveFileActionTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Test
    public void testExecuteShouldRemoveFile(){
        int result = removeFileAction.execute();
        
        assertEquals(0,result);
        //Verify the presente of the file into the directory
        File f = new File(filePath);
        assertEquals(false, f.exists());
    }
    
    @Test
    public void testEcecuteShouldReturnErrorNegativeValueIfFileDoesNotExist(){
        removeFileAction.setFilePath("non/esistente");
        int result = removeFileAction.execute();
        assertEquals(-1,result);
    }

    @Test
    public void testGetFilePath(){
        String provaFile = filePath;
        assertEquals(removeFileAction.getFilePath(),provaFile);
    }
    
    @Test
    public void testSetFilePath(){
        String filePath2 = "filePath/prova/prova.txt";
        removeFileAction.setFilePath(filePath2);
        assertEquals(filePath2, removeFileAction.getFilePath());
    }
    
    @Test
    public void testGetParam1(){
        assertEquals(removeFileAction.getParam1(), removeFileAction.getFilePath());
    }
    
    @Test
    public void testGetParam2(){
        assertEquals(removeFileAction.getParam2(), "");
    }
    
    @After
    public void recreateFileRemoved(){
        File file = new File(filePath);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();  // Crea le directory padre se non esistono
            }
            try {
                file.createNewFile();  // Crea il file se non esiste
            } catch (IOException ex) {
                Logger.getLogger(RemoveFileActionTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
