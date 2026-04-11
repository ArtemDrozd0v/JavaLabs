import org.example.FileSearch;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class FileSearchTest {
    @Test
    void testFindFilesByExtension() throws IOException {
        FileSearch fileSearch = new FileSearch();

        File testDir = new File("test_files");

        if (!testDir.exists()) {
            testDir.mkdir();
        }

        File f1 = new File(testDir, "1.java");
        File f2 = new File(testDir, "2.py");
        File f3 = new File(testDir, "3.java");
        File f4 = new File(testDir, "4.kt");
        File f5 = new File(testDir, "5.java");

        f1.createNewFile();
        f2.createNewFile();
        f3.createNewFile();
        f4.createNewFile();
        f5.createNewFile();

        String extension = ".java";
        File[] foundFiles = fileSearch.findFilesByExtension(testDir, extension);

        File[] expected = {f1, f3, f5};

        assertArrayEquals(expected, foundFiles);
    }
}