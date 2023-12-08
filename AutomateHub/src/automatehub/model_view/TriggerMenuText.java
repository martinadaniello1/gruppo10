package automatehub.model_view;


public enum TriggerMenuText {
    TIME("When the clock hits..."),
    DAYMONTH("When it is this day of the month..."),
    CURRENTDAY("When it is this day..."),
    FILESIZE("When this file's size is bigger than this value..."),
    EXIT("When the program returns...");
    
    private final String menuText;

    TriggerMenuText(String menuText){
        this.menuText=menuText;
    }

    public String getMenuText(){
        return menuText;
    }

     public static TriggerMenuText getByMenuText(String menuText) {
        for (TriggerMenuText enumType : values()) {
            if (enumType.getMenuText().equalsIgnoreCase(menuText)) {
                return enumType;
            }
        }
        throw new IllegalArgumentException("No enum constant with menu text: " + menuText);
    }
    
}
