package automatehub.model_view;

/**
 * Enumeration representing different types of actions with corresponding menu
 * text. Each enum value has an associated menu text describing the action.
 */
public enum ActionMenuText {
    PLAY("Play an audio file"),
    MEX("Show a message"),
    COPY("Copy a file to a directory"),
    MOVE("Move a file from a directory"),
    APPEND("Append a string at the end of a text file"),
    REMOVE("Remove a file from a directory"),
    EXECUTE("Execute external program");

    private final String menuText;

    ActionMenuText(String menuText) {
        this.menuText = menuText;
    }

    /**
     * Gets the menu text associated with the enum value.
     *
     * @return a string representing the menu text.
     */
    public String getMenuText() {
        return menuText;
    }

    /**
     * Gets the enum constant based on the provided menu text.
     *
     * @param menuText the menu text to search for.
     * @return the corresponding enum constant.
     * @throws IllegalArgumentException if no enum constant is found with the
     * given menu text.
     */
    public static ActionMenuText getByMenuText(String menuText) {
        for (ActionMenuText enumType : values()) {
            if (enumType.getMenuText().equalsIgnoreCase(menuText)) {
                return enumType;
            }
        }
        throw new IllegalArgumentException("No enum constant with menu text: " + menuText);
    }

}
