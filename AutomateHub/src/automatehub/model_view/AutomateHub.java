package automatehub.model_view;

import automatehub.controller.FXMLDocumentController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AutomateHub extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {  
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        FXMLDocumentController controller = new FXMLDocumentController();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setOnShowing(event -> {
            try {
                controller.handleOpenRequest(event);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AutomateHub.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        stage.show();  
 
        controller.startAction();
        
        stage.setOnCloseRequest(event -> controller.handleCloseRequest(event));
             
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);        
    }
    
}
