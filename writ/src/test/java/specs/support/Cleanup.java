package specs.support;

import org.junit.After;
import org.junit.Before;

import java.io.File;
public class Cleanup {

    @Before
    @After
    public void eraseAllCreatedFiles() {
        File folder = new File("/home/pro/Desktop/glassfish4/temporaryxmlstorage/terms/");
        if(folder.exists()) {
        for(File current : folder.listFiles())
            if(current.isFile())
                current.delete();
        }
    }
}
