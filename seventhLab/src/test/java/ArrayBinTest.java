import org.example.ArrayBin;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ArrayBinTest {
    @TempDir
    Path tempDir;

    @Test
    void testWriteAndReadBin() throws IOException {
        ArrayBin arrayBin = new ArrayBin();
        int[] expected = {1, 2, 3, 4, -1};
        String filePath = tempDir.resolve("test.bin").toString();

        arrayBin.writeArrayToBin(expected, filePath);
        int[] actual = arrayBin.readArrayToBin(filePath);

        assertArrayEquals(expected, actual);
    }
}
