package automatehub.model_view;

/**
 * The {@code RuleObserver} interface defines methods for observing rule-related
 * events.
 */
public interface RuleObserver {

    /**
     * Called when a new rule is added.
     *
     * @param rule The added rule.
     */
    void onRuleAdded(Rule rule);

    /**
     * Called when a rule is removed.
     *
     * @param rule The removed rule.
     */
    void onRuleRemoved(Rule rule);

    /**
     * Called when a rule is edited.
     *
     * @param oldRule The old version of the rule.
     * @param newRule The new version of the rule.
     */
    void onRuleEdited(Rule oldRule, Rule newRule);

    /**
     * Called when a rule is verified.
     *
     * @param rule The verified rule.
     */
    void onRuleVerified(Rule rule);

    /**
     * Called when an action associated with a rule is executed.
     *
     * @param rule The rule for which the action is executed.
     */
    void onActionExecuted(Rule rule);

    /**
     * Called when a rule is not executed during program shutdown.
     *
     * @param rule The verified rule.
     */
    void onRuleNotExecuted(Rule rule);
}
