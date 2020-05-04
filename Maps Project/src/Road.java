/**
 * The class Road that can represent the edges of a Graph of Towns. The class must implement Comparable. 
 * The class stores references to the two vertices(Town endpoints),
 * the distance between vertices, and a name, and the traditional methods (constructors, getters/setters, toString, etc.), 
 * and a compareTo, which compares two Road objects.
 * Since this is a undirected graph, an edge from A to B is equal to an edge from B to A.
 * 
 * @author IK Atupulazi
 *
 */
public class Road implements Comparable<Road>
{
	private String roadName;
	private Town to;
	private Town from;
	private int length;
	
	/**
	 * Constructor sets all the fields
	 * @param from One town on the road2
	 * @param to Another town on the road
	 * @param length Weight of the edge, i.e., distance from one town to the other
	 * @param roadName Name of the road
	 */
	public Road(Town from, Town to, int length, String roadName)
	{
		this.roadName = roadName;
		this.to = to;
		this.from = from;
		this.length = length;
	}
	
	/**
	 * Constructor with weight preset at 1
	 * @param from One town on the road
	 * @param to Another town on the road
	 * @param roadName Name of the road
	 */
	public Road(Town from, Town to, String roadName)
	{
		this.roadName = roadName;
		this.to = to;
		this.from = from;
		length = 1;
	}
	
	/**
	 * Returns true only if the edge contains the given town
	 * @param town a vertex of the graph
	 * @return true only if the edge is connected to the given vertex
	 */
	public boolean contains(Town town)
	{
		if((town.compareTo(to) == 0) || (town.compareTo(from) == 0))
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Returns the road name
	 * @return the name of road
	 */
	public String getName()
	{
		return roadName;
	}
	
	/**
	 * Returns the second town on the road
	 * @return A town on the road
	 */
	public Town getDestination()
	{
		return to;
	}
	
	/**
	 * Returns the first town on the road
	 * @return A town on the road
	 */
	public Town getSource()
	{
		return from;
	}
	
	/**
	 * Returns the distance of the road
	 * @return the distance of the road
	 */
	public int getWeight()
	{
		return length;
	}
	
	/**
	 * Returns true if each of the ends of the road r is the same as the ends of this road. Remember that a road that goes from point A to point B is the same as a road that goes from point B to point A.
	 * @param r - road object to compare it to
	 * @return true if equal
	 */
	@Override
	public boolean equals(Object r)
	{
		if(r.getClass() != this.getClass())
		{
			return false;
		}
		
		if((to.compareTo(((Road) r).getDestination()) == 0 && from.compareTo(((Road) r).getSource()) == 0) || (to.compareTo(((Road) r).getSource()) == 0 && from.compareTo(((Road) r).getDestination()) == 0))
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * To string method.
	 * @return a string representation of the fields
	 */
	public String toString()
	{
		return "Road Name: " + roadName + "\n" +
				"Source: " + from.getName() + "\n" +
				"Destination: " + to.getName() + "\n" +
				"Distance: " + length + "\n";
	}
	
	/**
	 * Inherited from Comparator
	 * @return 0 if the road names are the same, a positive or negative number if the road names are not the same
	 */
	@Override
	public int compareTo(Road r)
	{
		if(r.getName().equals(roadName))
		{
			return 0;
		}
		
		return -1;
	}
	
	
}
