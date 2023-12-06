package automatehub.model_view;


public enum TriggerMenuText {
    TIME("When the clock hits ..."),
    DAYMONTH("When it is this day of the month ...");

    private final String menuText;

    TriggerMenuText(String menuText){
        this.menuText=menuText;
    }

    public String getMenuText(){
        return menuText;
    }

    @Override
    public String toString() {
        return this.getMenuText();
    }
    
}
