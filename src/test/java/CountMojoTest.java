import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;


/**
 * @author Zhaofeng Zhou
 * @date 7/10/2021 下午4:53
 */
public class CountMojoTest extends TestCase {

    private CountMojo countMojo;


    @BeforeEach
    public void setUp() {
        countMojo = new CountMojo();
    }

    /**
     * @throws Exception
     */
    public void testMojoGoal() throws Exception {
        countMojo.execute();
    }

}