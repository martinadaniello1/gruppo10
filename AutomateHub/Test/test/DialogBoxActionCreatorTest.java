package test;

import automatehub.model_view.action.Action;
import automatehub.model_view.action.DialogBoxAction;
import automatehub.model_view.action.creator.DialogBoxActionCreator;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

public class DialogBoxActionCreatorTest {

    private static String message;
    private static DialogBoxActionCreator action;

    @BeforeClass
    public static void SetUpClass() {
        message = "TEST TEST TEST TEST TEST";
        action = new DialogBoxActionCreator(message);
    }

    @Test
    public void createTest() {
        DialogBoxAction a = new DialogBoxAction(message);
        Action acReturned = action.create();
        assertTrue(a.equals(acReturned));
    }
}
