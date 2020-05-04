import java.util.ArrayList;

/**
 * Represents an town as a node of a graph. 
 * The Town class holds the name of the town and a list of adjacent towns, 
 * and other fields as desired, and the traditional methods (constructors, getters/setters, toString, etc.).
 * It will implement the Comparable interface These are the minimum methods that are needed.
 * Please feel free to add as many methods as you need.
 * 
 * @author IK Atupulazi
 *
 */
public class Town implements Comparable<Town>
{
	private String townName;
	private ArrayList<Town> list;
	
	/**
	 * Constructor that takes in the name of the town as the parameter
	 * @param name the name of the town
	 */
	public Town(String name)
	{
		townName = name;
	}
	
	/**
	 * Copy Constructor 
	 * @param t the town to be copied
	 */
	public Town(Town t)
	{
		townName = t.getName();
	}
	
	/**
	 * The hashcode for the name of the town
	 * @return the hashcode for the name of the town
	 */
	public int hashCode()
	{
		return townName.hashCode();
	}
	
	/**
	 * Returns the town's name
	 * @return town's name
	 */
	public String getName()
	{
		return townName;
	}
	
	/**
	 * The toString method inherited from Object 
	 * @return the town name
	 */
	public String toString()
	{
		return "Town Name: " + townName;
	}

	/**
	 * inherited from Object
	 * @param t the town object to be compared
	 * @return true if the town names are equal, false if not
	 */
	@Override
	public boolean equals(Object t)
	{
		if(t.getClass() != this.getClass())
		{
			return false;
		}
		
		
		if(((Town)t).getName().equals(townName))
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Inherited from Comparable
	 * @return 0 if names are equal, a positive or negative number if the names are not equal
	 */
	@Override
	public int compareTo(Town o) 
	{
		if(o.getName().equals(townName))
		{
			return 0;
		}
		return -1;
	}

}
