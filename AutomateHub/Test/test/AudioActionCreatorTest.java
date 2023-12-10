package test;

import automatehub.model_view.action.Action;
import automatehub.model_view.action.AudioAction;
import automatehub.model_view.action.creator.AudioActionCreator;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class AudioActionCreatorTest {

    private static String pathFile;
    private static AudioActionCreator ac;

    @BeforeClass
    public static void SetUpClass() {
        pathFile = "./Test/test/testFiles/file_example_WAV_1MG.wav";
        ac = new AudioActionCreator(pathFile);
    }

    @Test
    public void testCreate() {
        AudioAction a = new AudioAction(pathFile);
        Action acReturned = ac.create();
        assertTrue(a.equals(acReturned));

        AudioAction aFalse = new AudioAction("11:21");
        assertFalse(aFalse.equals(acReturned));
    }
}
