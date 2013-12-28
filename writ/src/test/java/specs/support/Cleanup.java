package specs.support;

import org.junit.After;
import org.junit.Before;

import java.io.File;
public class Cleanup {

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
