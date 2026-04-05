import org.example.FileSearch;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileSearchTest {
    @TempDir
    Path tempDir;

    @Test
    void testFindFilesByExtension() throws IOException {
        FileSearch fileSearch = new FileSearch();
        String path = tempDir.toString();

        Files.createFile(tempDir.resolve("1.java"));
        Files.createFile(tempDir.resolve("2.cpp"));
        Files.createFile(tempDir.resolve("3.java"));
        Files.createFile(tempDir.resolve("4.kt"));

        String[] expected = {"1.java", "3.java"};

        File[] foundFiles = fileSearch.findFilesByExtension(path, ".java");

        String[] actual = new String[foundFiles.length];
        for (int i = 0; i < foundFiles.length; i++) {
            actual[i] = foundFiles[i].getName();
        }

        assertArrayEquals(expected, actual);
    }

    @Test
    void testInvalidDirectory() {
        FileSearch fileSearch = new FileSearch();
        File[] result = fileSearch.findFilesByExtension("path", ".txt");
        assertEquals(0, result.length);
    }
}