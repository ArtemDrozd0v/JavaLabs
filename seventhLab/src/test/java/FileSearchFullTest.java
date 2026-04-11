import org.example.FileSearchFull;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileSearchFullTest {
    @Test
    void testRecursiveSearchWithNewNames() throws IOException {
        FileSearchFull searcher = new FileSearchFull();

        File rootDir = new File("system_reports");

        if (!rootDir.exists()) {
            rootDir.mkdir();
        }

        File dir1 = new File(rootDir, "archive");
        File dir2 = new File(rootDir, "current");
        dir1.mkdir();
        dir2.mkdir();

        File f1 = new File(dir1, "log_jan.txt");
        File f2 = new File(dir1, "log_feb.txt");
        File f3 = new File(dir2, "log_system.txt");
        File f4 = new File(rootDir, "logs.txt");

        File f5 = new File(dir2, "error.pdf");
        File f6 = new File(rootDir, "config.xml");
        File f7 = new File(dir1, "test.txt");

        f1.createNewFile();
        f2.createNewFile();
        f3.createNewFile();
        f4.createNewFile();
        f5.createNewFile();
        f6.createNewFile();
        f7.createNewFile();

        String pattern = "log.*\\.txt";

        List<String> results = searcher.recursiveSearcher(rootDir, pattern);

        for (String path : results) {
            System.out.println(path);
        }

        assertEquals(4, results.size());
    }
}
