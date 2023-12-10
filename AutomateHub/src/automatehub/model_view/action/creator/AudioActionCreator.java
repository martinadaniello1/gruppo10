package automatehub.model_view.action.creator;

import automatehub.model_view.action.AudioAction;
import automatehub.model_view.action.Action;

/**
 * Represents a ConcreteCreator of the Factory Method pattern for creating
 * instances of the {@link AudioAction} class.
 */
public class AudioActionCreator implements CreatorAction {

    private String pathFile;

    /**
     * Constructs an AudioActionCreator with the path file of the audio.
     *
     * @param pathFile the path of the audio file to be played.
     */
    public AudioActionCreator(String pathFile) {
        this.pathFile = pathFile;
    }

    /**
     * Creates an instance of the {@link AudioAction} class.
     *
     * @return a new instance of the AudioAction class with the specified audio
     * file path.
     */
    @Override
    public Action create() {
        return new AudioAction(pathFile);
    }

}
