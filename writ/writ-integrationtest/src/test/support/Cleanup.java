package test.support;

import org.junit.After;
import org.junit.Before;

import java.io.File;
public class Cleanup {

    public static final File PATH_TO_FILE_REPOSITORY = new File("/home/pro/Desktop/glassfish4/temporaryxmlstorage/terms/");

    @Before
    @After
    public void eraseAllCreatedFiles() {
        String [] pathsToCleanUp = {
                "/home/pro/Desktop/glassfish4/temporaryxmlstorage/terms/",
                "/home/pro/Desktop/glassfish4/temporaryxmlstorage/users/"
        };
        for(String currentPath : pathsToCleanUp) {
            clean(currentPath);
        }
    }

    private void clean(String path) {
        File folder = new File(path);
        if(folder.exists()) {
            for(File current : folder.listFiles())
                if(current.isFile())
                    current.delete();
        }
    }
}