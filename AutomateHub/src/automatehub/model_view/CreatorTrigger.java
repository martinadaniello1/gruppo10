package automatehub.model_view;

/**
 * The interface separate the client who is istantiating the trigger from how
 * the trigger will be create, through a factory method.
 *
 */
public interface CreatorTrigger {

    public Trigger create();

}
