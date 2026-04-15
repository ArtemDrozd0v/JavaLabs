import org.example.FileSearchFull;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FileSearchFullMockitoTest {
    private File createMockFile(String name, String userPath) {
        File file = Mockito.mock(File.class);

        when(file.getName()).thenReturn(name);
        when(file.getAbsolutePath()).thenReturn(userPath);
        when(file.isDirectory()).thenReturn(false);
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

        File f1 = createMockFile("2012.txt", "/user/dir1/2012.txt");
        File f2 = createMockFile("2012_school.png", "/user/dir1/2012_school.png");
        File f3 = createMockFile("2012_university.png", "/user/dir2/2012_university.png");
        File f4 = createMockFile("logs.txt", "/user/2012.png");
        File f5 = createMockFile("2012.png", "/user/dir2/2012_me.png");
        File f6 = createMockFile("password.xml", "/user/password.xml");
        File f7 = createMockFile("family.png", "/user/dir2/family.png");

        File mockDir1 = createMockDirectory("dir1", "/user/test_package/dir1", new File[]{f1, f2, f7});
        File mockDir2 = createMockDirectory("dir2", "/user/test_package/dir2", new File[]{f3, f5});

        File mockRoot = createMockDirectory("test_package", "/user/test_package", new File[]{mockDir1, mockDir2, f4, f6});

        String pattern = "2012.*\\.png";

        List<String> results = searcher.recursiveSearcher(mockRoot, pattern);

        for (String path : results) {
            System.out.println(path);
        }

        assertEquals(3, results.size());
    }
}
