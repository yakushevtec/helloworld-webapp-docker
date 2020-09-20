package alex;

import org.springframework.context.annotation.*;

@Configuration
public class PathsDAO
{
	@Bean
	public Paths getPaths()
	{
		return new Paths();
	}
}
