import org.example.ExtensionFilter;
import org.example.FileSearch;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FileSearchMockitoTest {

    @Test
    void testTask4() {
        FileSearch fileSearch = new FileSearch();

        File mockDir = Mockito.mock(File.class);

        File mockF1 = Mockito.mock(File.class);
        File mockF2 = Mockito.mock(File.class);
        File mockF3 = Mockito.mock(File.class);
        File mockF4 = Mockito.mock(File.class);

        when(mockDir.exists()).thenReturn(true);
        when(mockDir.isDirectory()).thenReturn(true);

        when(mockF1.getName()).thenReturn("1.java");
        when(mockF2.getName()).thenReturn("2.cpp");
        when(mockF3.getName()).thenReturn("3.java");
        when(mockF4.getName()).thenReturn("4.kt");

        File[] mockFiles1 = {mockF1, mockF3};
        File[] mockFiles2 = {mockF1, mockF2, mockF3, mockF4};

        when(mockDir.listFiles(any(ExtensionFilter.class))).thenReturn(mockFiles1);
        when(mockDir.listFiles()).thenReturn(mockFiles2);

        File[] expected = {mockF1, mockF3};
        File[] foundFiles = fileSearch.findFilesByExtension(mockDir, ".java");

        assertArrayEquals(expected, foundFiles);

    }
}
