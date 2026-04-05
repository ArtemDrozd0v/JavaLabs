import org.example.ArrayRandomAccessFile;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ArrayRandomAccessFileTest {
    @TempDir
    Path tempDir;

    @Test
    void testReadFromPosition() throws IOException {
        String filePath = tempDir.resolve("test.bin").toString();

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filePath))) {
            for (int i = 0; i < 5; i++) {
                dos.writeInt(i);
            }
        }

        int[] expected = {2, 3};
        int[] actual = ArrayRandomAccessFile.readFromPosition(filePath, 8, 2);

        assertArrayEquals(expected, actual);
    }
}