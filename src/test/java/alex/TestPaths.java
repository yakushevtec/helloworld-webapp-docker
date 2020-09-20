package alex;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.junit4.*;
import org.springframework.beans.factory.annotation.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPaths
{
    @Autowired
    private Paths paths;

	@Test
	public void testConnectivities()
	{
		assert(paths.connected("Boston", "Newark"));
		assert(paths.connected("Boston", "Philadelphia"));
		assert(!paths.connected("Philadelphia", "Albany"));
	}
}
