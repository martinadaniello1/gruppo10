/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package automatehub.model_view;

/**
 *
 * @author mapic
 */
public interface RuleObserver {
    public void onRuleAdded(Rule rule);
    public void onRuleRemoved(Rule rule);
    public void onRuleEdited(Rule oldRule, Rule newRule);
    public void onRuleVerified(Rule rule);
    public void onActionExecuted(Rule rule);
}
