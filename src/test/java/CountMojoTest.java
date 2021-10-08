import org.apache.maven.plugin.testing.AbstractMojoTestCase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


/**
 * @author Zhaofeng Zhou
 * @date 7/10/2021 下午4:53
 */
public class CountMojoTest extends AbstractMojoTestCase {

    private static final String PLUGIN_CONFIG = "src/test/java/count-test-plugin-config.xml";

    /**
     * @throws Exception
     */
    public void testMojoGoal() throws Exception {
        File testPom = getPom();
        CountMojo mojo = (CountMojo) lookupMojo("countCode", testPom);

        assertNotNull(mojo);


        // generateTestFile()
        // mojo.execute();
    }


    private File getPom() {
        return new File(getBasedir(), PLUGIN_CONFIG);
    }

    private void generateTestFile(File directory, int num, String type) throws IOException {
        for (int i = 0; i < num; i++) {
            Files.createTempFile(directory.toPath(), "testFile" + i, type);
        }
    }

}