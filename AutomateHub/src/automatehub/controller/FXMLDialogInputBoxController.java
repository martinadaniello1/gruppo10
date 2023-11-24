/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package automatehub.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author adc01
 */
public class FXMLDialogInputBoxController implements Initializable {

    @FXML
    private DialogPane rulesDialogPane;
    @FXML
    private Label actionNameLabel;
    @FXML
    private TextField actionNameTextField;
    @FXML
    private Label actionLabel;
    @FXML
    private TextField actionTextField;
    @FXML
    private Label triggerNameLabel;
    @FXML
    private TextField triggerNameTextField;
    @FXML
    private Label triggerLabel;
    @FXML
    private TextField triggerTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
