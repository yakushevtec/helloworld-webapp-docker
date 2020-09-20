package alex;

import java.util.*;

import org.springframework.core.io.*;

import alex.common.IOHelper;

public class Paths
{
	Set<String> cities = new HashSet<String>();
	Map<String, Set<String>> connectionMap = new HashMap<String, Set<String>>();

	Paths()
	{
		String fileName = "city.txt";
		try
		{
			List<String> list = null;
			try
			{
				ClassPathResource cpr = new ClassPathResource("classpath:"+fileName);
				list = IOHelper.fileToList(cpr.getInputStream());
			}
			catch(Exception e)
			{
				ClassPathResource cpr = new ClassPathResource(fileName);
				list = IOHelper.fileToList(cpr.getInputStream());
			}

			for(int i=1; i<list.size(); i++)
			{
				String line = list.get(i);
				if(line.trim().startsWith("#")) continue;

				String[] st = line.split("\\s*,\\s*");
				int k = 0;
				String city1 = st[k++].trim().toLowerCase();
				String city2 = st[k++].trim().toLowerCase();

				cities.add(city1);
				Set<String> sitySet = connectionMap.get(city1);
				if(sitySet==null)
				{
					sitySet = new HashSet<String>();
					connectionMap.put(city1, sitySet);
				}
				sitySet.add(city2);

				cities.add(city2);
				sitySet = connectionMap.get(city2);
				if(sitySet==null)
				{
					sitySet = new HashSet<String>();
					connectionMap.put(city2, sitySet);
				}
				sitySet.add(city1);
			}
			System.out.println("Found Cities: "+cities.size());
		}
		catch(Exception e){e.printStackTrace();}
	}

	public boolean connected(String city1, String city2)
	{
		city1 = city1.trim().toLowerCase();
		city2 = city2.trim().toLowerCase();

		Set<String> connSet1 = new HashSet<String>();
		Set<String> connSet2 = new HashSet<String>();

		Set<String> currentSet1 = new HashSet<String>();
		currentSet1.add(city1);

		Set<String> currentSet2 = new HashSet<String>();
		currentSet2.add(city2);

		do
		{
			currentSet1 = testConnections(connSet1, currentSet1);
			currentSet2 = testConnections(connSet2, currentSet2);

			Set<String> intersection = new HashSet<String>(connSet1);
			intersection.retainAll(connSet2);
			if(intersection.size()>0)
				return true;
		}
		while(currentSet1.size()>0 || currentSet2.size()>0);
		return false;
	}

	Set<String> testConnections(Set<String> found, Set<String> current)
	{
		Set<String> res = new HashSet<String>();
		for(String curSet:current)
		{
			Set<String> citySet = connectionMap.get(curSet);
			if(citySet==null) continue;
			for(String s:citySet)
			{
				if(found.contains(s)) continue;
				res.add(s);
				found.add(s);
			}
		}
		return res;
	}
}
