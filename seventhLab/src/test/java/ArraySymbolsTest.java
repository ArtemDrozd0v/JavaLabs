import org.example.ArraySymbols;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ArraySymbolsTest {
    @TempDir
    Path tempDir;

    @Test
    void testWriteAndReadSymbols() throws IOException {
        ArraySymbols arraySymbols = new ArraySymbols();
        int[] expected = {1, 2, 3, 4, -1};
        String filePath = tempDir.resolve("test.txt").toString();

        arraySymbols.writeArrayToSymbols(expected, filePath);
        int[] actual = arraySymbols.readArrayToSymbols(filePath);

        assertArrayEquals(expected, actual);
    }
}