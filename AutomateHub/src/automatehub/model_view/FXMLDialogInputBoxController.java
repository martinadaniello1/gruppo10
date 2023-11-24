/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package automatehub.model_view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author adc01
 */
public class FXMLDialogInputBoxController implements Initializable {

    @FXML
    private DialogPane dialogBoxFX;
    @FXML
    private TextField ruleNameFX;
    @FXML
    private Button buttonFilePickerFX;
    @FXML
    private DatePicker datePickerFX;
    @FXML
    private TextField filePathFX;
    @FXML
    private TextField textMessageForDialogBoxFX;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void chooseFile(ActionEvent event) {
    }
    
}
