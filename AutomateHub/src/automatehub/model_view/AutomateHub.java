package automatehub.model_view;

import automatehub.controller.FXMLDocumentController;
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
        stage.show();  
        
        controller.startAction();
             
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);        
    }
    
}
