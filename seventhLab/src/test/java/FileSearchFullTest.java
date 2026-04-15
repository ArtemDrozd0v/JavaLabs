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

        File rootDir = new File("test_package");

        if (!rootDir.exists()) {
            rootDir.mkdir();
        }

        File dir1 = new File(rootDir, "dir1");
        File dir2 = new File(rootDir, "dir2");
        dir1.mkdir();
        dir2.mkdir();

        File f1 = new File(dir1, "2012_school.png");
        File f2 = new File(dir1, "2012_university.png");
        File f3 = new File(dir2, "2012_me.png");
        File f4 = new File(rootDir, "2012.png");

        File f5 = new File(dir2, "family.png");
        File f6 = new File(rootDir, "passwords.xml");
        File f7 = new File(dir1, "2012.txt");

        f1.createNewFile();
        f2.createNewFile();
        f3.createNewFile();
        f4.createNewFile();
        f5.createNewFile();
        f6.createNewFile();
        f7.createNewFile();

        String pattern = "2012.*\\.png";

        List<String> results = searcher.recursiveSearcher(rootDir, pattern);

        for (String path : results) {
            System.out.println(path);
        }

        assertEquals(4, results.size());
    }
}
