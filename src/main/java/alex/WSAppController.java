package alex;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.*;

import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;

@RestController
@RequestMapping("")
public class WSAppController 
{
	Paths paths = new Paths();
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// z");
	private static final String version = "0.0.1";
	private long startTime = System.currentTimeMillis();
	
	@RequestMapping(value = "/connected/{origin}/{destination}", method = RequestMethod.GET)
	public String connected
	(
		@PathVariable("origin") String origin
		, @PathVariable("destination") String destination
	) 
	{
		boolean connected = paths.connected(origin, destination);
		return connected ? "yes" : "no";
	}

	@RequestMapping(value = "/connected", method = RequestMethod.GET)
	public String connectedRequestParameter
	(
		@RequestParam(value = "origin", required = true) String origin
		, @RequestParam(value = "destination", required = true) String destination
	) 
	{
		boolean connected = paths.connected(origin, destination);
		return connected ? "yes" : "no";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String helloWorld() 
	{
		return "Hello World!\n";
	}

	@RequestMapping(value = "/health", method = RequestMethod.GET)
	public String health() 
	{
		OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
		
		long uptime = System.currentTimeMillis() - startTime;
		float cpuUtilization = (float)osBean.getProcessCpuLoad();
		long memoryUtilization = osBean.getCommittedVirtualMemorySize();
		
		String resp = "{\n"+
				  "  \"status\": \"OK\",\n"+
				  "  \"version\": \""+version+"\",\n"+
				  "  \"uptime\": \"up since "+DATE_FORMAT.format(new Date())+"\"\n"+
				  "  \"cpu:utilization\": \""+cpuUtilization+"\"\n"+
				  "  \"memory:utilization\": \""+memoryUtilization+"\"\n"+
				"}\n";

		return resp;
	}
}