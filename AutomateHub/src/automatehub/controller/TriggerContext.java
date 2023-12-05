/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automatehub.controller;

/**
 *
 * @author mapic
 */
public class TriggerContext {
    private TriggerState currentState;

    public void changeState(TriggerState triggerState) {
        this.currentState = triggerState;
    }
    
    public void setupUI() {
        currentState.setupUI(this);
    }
}
