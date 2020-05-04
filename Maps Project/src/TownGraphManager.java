import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * This class manages the graph
 * @author IK Atupulazi
 */
public class TownGraphManager implements TownGraphManagerInterface
{
	TownGraph graph;
	
	
	/**
	 * Constructor
	 */
	public TownGraphManager()
	{
		graph = new TownGraph();
	}
	/**
	 * Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) 
	{
		Town t = new Town(town1);
		Town t2 = new Town(town2);
		if(graph.addEdge(t, t2, weight, roadName) == null)
		{
			return false;
		}
		return true;
	}

	/**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	@Override
	public String getRoad(String town1, String town2) 
	{
		Town t = new Town(town1);
		Town t2 = new Town(town2);
		return graph.getEdge(t, t2).getName();
	}

	/**
	 * Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	@Override
	public boolean addTown(String v) 
	{
		Town t = new Town(v);
		if(graph.addVertex(t))
		{
			return true;
		}
		return false;
	}

	/**
	 * Gets a town with a given name
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */
	@Override
	public Town getTown(String name) 
	{
		Town t = new Town(name);
		Town temp;
		if(graph.containsVertex(t))
		{
			
			Set<Town> towns = graph.vertexSet();
			Iterator<Town> i = towns.iterator();
			while(i.hasNext())
			{
				temp = i.next();
				if(temp.equals(t))
				{
					return temp;
				}
			}
			
		}
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Determines if a town is already in the graph
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	@Override
	public boolean containsTown(String v) 
	{
		Town t = new Town(v);
		if(graph.containsVertex(t))
		{
			return true;
		}
		
		return false;
	}

	/**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2)
	{
		Town t = new Town(town1);
		Town t2 = new Town(town2);
		return graph.containsEdge(t, t2);
	}

	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	@Override
	public ArrayList<String> allRoads() 
	{
		Set<Road> r = graph.edgeSet();
		ArrayList<String> list = new ArrayList<String>();
		for(Road r2 : r)
		{
			list.add(r2.getName());
		}
		Collections.sort(list);
		return list;
	}

	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param road the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) 
	{
		Town t = new Town(town1);
		Town t2 = new Town(town2);
		
		if(graph.removeEdge(t, t2, 1, road) == null)
		{
			return false;
		}
		return true;
	}

	/**
	 * Deletes a town from the graph
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	@Override
	public boolean deleteTown(String v)
	{
		Town t = new Town(v);
		
		return graph.removeVertex(t);
	}

	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	@Override
	public ArrayList<String> allTowns() 
	{
		Set<Town> towns = graph.vertexSet();
		ArrayList<String> list = new ArrayList<String>();
		for(Town t2 : towns)
		{
			list.add(t2.getName());
		}
		Collections.sort(list);
		return list;
		
	}

	/**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2)
	{
		Town t = new Town(town1);
		Town t2 = new Town(town2);
		
		ArrayList<String> list = graph.shortestPath(t, t2);
		return list;
	}
	
	
	/**
	 * Populates graph based upon the file the user chooses
	 * @param selectedFile the file selected to read from
	 * @throws FileNotFoundException thrown if file is not found
	 */
	public void populateTownGraph(File selectedFile) throws FileNotFoundException 
	{
		Scanner scan = new Scanner(selectedFile);
		ArrayList<String> list = new ArrayList<>();
		while(scan.hasNext())
		{
			list.add(scan.nextLine());
		}
		
		// Populating Graph
		for(int x = 0; x < list.size(); x++)
		{
			String[] semicolon = list.get(x).split(";");
			
			addTown(semicolon[1]);
			addTown(semicolon[2]); 
			
			String[] comma = semicolon[0].split(",");
			addRoad(semicolon[1], semicolon[2], Integer.parseInt(comma[1]), comma[0]);
		}
		
	}

}
