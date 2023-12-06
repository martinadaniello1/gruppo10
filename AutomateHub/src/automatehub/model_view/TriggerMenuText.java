package automatehub.model_view;


public enum TriggerMenuText {
    TIME("When the clock hits ..."),
    EXIT("When the program returns...");
    
    private final String menuText;

    TriggerMenuText(String menuText){
        this.menuText=menuText;
    }

    public String getMenuText(){
        return menuText;
    }
    
}
