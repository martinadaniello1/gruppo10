package automatehub.model_view.trigger;

/**
 * Enumeration representing different types of triggers with corresponding menu
 * text. Each enum value has an associated menu text describing the trigger.
 */
public enum TriggerMenuText {

    TIME("When the clock hits..."),
    DAYMONTH("When it is this day of the month..."),
    CURRENTDAY("When it is this day..."),
    FILESIZE("When this file's size is bigger than this value..."),
    EXIT("When the program returns..."),
    DAYWEEK("When it is this day of the week..."),
    FINDFILE("Found a file in a directory...");

    private final String menuText;

    TriggerMenuText(String menuText) {
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
    public static TriggerMenuText getByMenuText(String menuText) {
        for (TriggerMenuText enumType : values()) {
            if (enumType.getMenuText().equalsIgnoreCase(menuText)) {
                return enumType;
            }
        }
        throw new IllegalArgumentException("No enum constant with menu text: " + menuText);
    }

}
