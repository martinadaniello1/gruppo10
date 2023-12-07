package automatehub.model_view;

public interface RuleObserver {

    public void onRuleAdded(Rule rule);

    public void onRuleRemoved(Rule rule);

    public void onRuleEdited(Rule oldRule, Rule newRule);

    public void onRuleVerified(Rule rule);

    public void onActionExecuted(Rule rule);
}
