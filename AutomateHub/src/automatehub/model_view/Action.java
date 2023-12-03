package automatehub.model_view;

public interface Action {

    public int execute();
    public String getType();
    public String getParam1();
    public String getParam2();
}
