package automatehub.model_view;

public enum ActionMenuText {
    PLAY("Play an audio file"),
    MEX("Show a message"),
    COPY("Copy a file to a directory"),
    MOVE("Move a file from a directory"),
    APPEND("Append a string at the end of a text file"),
    REMOVE("Remove a file from a directory");
    
    private final String menuText;
    
    ActionMenuText(String menuText){
        this.menuText = menuText;
    }
    
    public String getMenuText(){
        return menuText;
    }

    public static ActionMenuText getByMenuText(String menuText) {
        for (ActionMenuText enumType : values()) {
            if (enumType.getMenuText().equalsIgnoreCase(menuText)) {
                return enumType;
            }
        }
        throw new IllegalArgumentException("No enum constant with menu text: " + menuText);
    }

}
