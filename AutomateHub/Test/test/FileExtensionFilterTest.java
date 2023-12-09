package test;

import automatehub.model_view.FileExtensionFilter;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileExtensionFilterTest {

    @Test
    public void getExtensionShouldReturnCorrectValues() {
        assertEquals("*.wav", FileExtensionFilter.WAV.getExtension());
        assertEquals("*.txt", FileExtensionFilter.TEXT.getExtension());
        assertEquals("*.*", FileExtensionFilter.ALL.getExtension());
        assertEquals("*.directory", FileExtensionFilter.DIRECTORY.getExtension());
        assertEquals("*.py", FileExtensionFilter.PYTHON.getExtension() );
    }

    @Test
    public void getDescriptionShouldReturnCorrectValues() {
        assertEquals("Audio Files (*.wav)", FileExtensionFilter.WAV.getDescription());
        assertEquals("Text Files (*.txt)", FileExtensionFilter.TEXT.getDescription());
        assertEquals("All Files (*.*)", FileExtensionFilter.ALL.getDescription());
        assertEquals("Directory", FileExtensionFilter.DIRECTORY.getDescription());
        assertEquals("Python file (*.py)", FileExtensionFilter.PYTHON.getDescription());
    }

    @Test
    public void enumValuesShouldNotBeNull() {
        assertNotNull(FileExtensionFilter.values());
    }

    @Test
    public void enumValuesShouldHaveCorrectLength() {
        assertEquals(5, FileExtensionFilter.values().length);
    }

    @Test
    public void enumValuesShouldBeInExpectedOrder() {
        FileExtensionFilter[] expectedOrder = { FileExtensionFilter.WAV, FileExtensionFilter.TEXT, FileExtensionFilter.PYTHON, FileExtensionFilter.ALL, FileExtensionFilter.DIRECTORY };
        assertArrayEquals(expectedOrder, FileExtensionFilter.values());
    }
}
