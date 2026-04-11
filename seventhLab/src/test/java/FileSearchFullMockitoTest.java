import org.example.FileSearchFull;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FileSearchFullMockitoTest {
    private File createMockFile(String name, String userPath, boolean isDirectory) {
        File file = Mockito.mock(File.class);

        when(file.getName()).thenReturn(name);
        when(file.getAbsolutePath()).thenReturn(userPath);
        when(file.isDirectory()).thenReturn(isDirectory);
        when(file.exists()).thenReturn(true);

        return file;
    }

    private File createMockDirectory(String name, String absPath, File[] internal) {
        File dir = Mockito.mock(File.class);

        when(dir.getName()).thenReturn(name);
        when(dir.getAbsolutePath()).thenReturn(absPath);
        when(dir.isDirectory()).thenReturn(true);
        when(dir.exists()).thenReturn(true);
        when(dir.listFiles()).thenReturn(internal);

        return dir;
    }

    @Test
    void testTask5() {
        FileSearchFull searcher = new FileSearchFull();

        File f1 = createMockFile("log_jan.txt", "/user/archive/log_jan.txt", false);
        File f2 = createMockFile("log_feb.txt", "/user/archive/log_feb.txt", false);
        File f3 = createMockFile("log_system.txt", "/user/current/log_system.txt", false);
        File f4 = createMockFile("logs.txt", "/user/logs.txt", false);
        File f5 = createMockFile("error.pdf", "/user/current/error.pdf", false);
        File f6 = createMockFile("config.xml", "/user/config.xml", false);
        File f7 = createMockFile("test.txt", "/user/archive/test.txt", false);

        File mockDir1 = createMockDirectory("archive", "/user/system_reports/archive", new File[]{f1, f2, f7});
        File mockDir2 = createMockDirectory("current", "/user/system_reports/current", new File[]{f3, f5});

        File mockRoot = createMockDirectory("system_reports", "/user/system_reports", new File[]{mockDir1, mockDir2, f4, f6});

        String pattern = "log.*\\.txt";

        List<String> results = searcher.recursiveSearcher(mockRoot, pattern);

        for (String path : results) {
            System.out.println(path);
        }

        assertEquals(4, results.size());
    }
}
