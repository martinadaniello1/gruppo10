package test;

import automatehub.model_view.FileExtensionFilter;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileExtensionFilterTest {

    @Test
    public void getExtensionShouldReturnCorrectValues() {
        assertEquals("*.wav", FileExtensionFilter.WAV.getExtension());
        assertEquals("*.text", FileExtensionFilter.TEXT.getExtension());
        assertEquals("*.*", FileExtensionFilter.ALL.getExtension());
    }

    @Test
    public void getDescriptionShouldReturnCorrectValues() {
        assertEquals("Audio Files (*.wav)", FileExtensionFilter.WAV.getDescription());
        assertEquals("Text Files (*.text)", FileExtensionFilter.TEXT.getDescription());
        assertEquals("All Files (*.*)", FileExtensionFilter.ALL.getDescription());
    }

    @Test
    public void enumValuesShouldNotBeNull() {
        assertNotNull(FileExtensionFilter.values());
    }

    @Test
    public void enumValuesShouldHaveCorrectLength() {
        assertEquals(3, FileExtensionFilter.values().length);
    }

    @Test
    public void enumValuesShouldBeInExpectedOrder() {
        FileExtensionFilter[] expectedOrder = { FileExtensionFilter.WAV, FileExtensionFilter.TEXT, FileExtensionFilter.ALL };
        assertArrayEquals(expectedOrder, FileExtensionFilter.values());
    }
}
