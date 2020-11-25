package alex;

import java.io.*;
import java.util.*;

public class Graph
{
	Node parent = null;

	void loadTree(String fileName)
	{
		Map<Integer, Node> map = new HashMap<Integer, Node>();
		try
		{
			List<String> list = fileToList(fileName);
			for(String line:list)
			{
				Node n = new Node(line);
				map.put(n.id, n);
			}
			for(Node n:map.values())
			{
				n.parent = map.get(n.parentID);
				if(n.parent==null)
					parent = n;
				else
					n.parent.children.add(n);
//				System.out.println(n);
			}
		}
		catch(Exception e){e.printStackTrace();}
	}

	List<String> fileToList(String fileName) throws IOException
	{
		List<String> res=new ArrayList<String>();
		FileInputStream fis = new FileInputStream(fileName);
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String line;
		while ((line = br.readLine()) != null)
			res.add(line);
		return res;
	}

	public static void main(String[] args)
	{
		Graph graph = new Graph();
		String path = System.getProperty("java.class.path");
		graph.loadTree(path+"/com/data.csv");
		graph.parent.showTree();
	}
}

class Node
{
	Node parent = null;
	int parentID = -1;
	int id = -1;
	String name;
	List<Node> children = new ArrayList<Node>();

	Node(String line)
	{
		String[] st = line.split(",");
		if(!st[0].equals("null"))
			parentID = Integer.parseInt(st[0]);
		id = Integer.parseInt(st[1]);
		name = st[2];
	}

	int getLevel()
	{
		int res = 0;
		Node n = parent;
		while(n!=null)
		{
			res++;
			n = n.parent;
		}
		return res;
	}

	void showTree()
	{
		System.out.println(this);
		for(Node n:children)
			n.showTree();
	}

	public String toString()
	{
		String space = "";
		for(int i=0; i<getLevel(); i++)
			space += "\t";
		return space+"node("+id+", "+parentID+", \""+name+"\")";
	}
}
