import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * The root interface in the graph hierarchy. A mathematical graph-theory graph
 * object G(V,E) contains a set V of vertices and a set
 * E of edges. Each edge e=(v1,v2) in E connects vertex v1 to vertex v2.
 *
 * Through generics, a graph can be typed to specific classes for vertices
 * V and edges E(T). Such a graph can contain
 * vertices of type V and all sub-types and Edges of type
 * E and all sub-types.
 * 
 * @author IK Atupulazi
 *
 */
public class TownGraph implements GraphInterface<Town, Road>
{
	private ArrayList<Town> towns;
	private ArrayList<LinkedList<Road>> roads;
	
	private ArrayList<Road> temp = new ArrayList<>();
	
	private Map<Town, Integer> map = new HashMap<>();
	private ArrayList<LinkedList<Road>> path;
	
	

	/**
	 * Default Constructor
	 */
	public TownGraph()
	{
		towns = new ArrayList<>();
		roads = new ArrayList<>();
	}
	
	/**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) 
	{
		if(!(towns.contains(sourceVertex) && towns.contains(destinationVertex)))
		{
			return null;
		}
		
		Road dummy = new Road(sourceVertex, destinationVertex, "dummy");
		int index = getIndex(sourceVertex);
		
		Iterator<Road> i = roads.get(index).iterator();
		while(i.hasNext())
		{
			Road dummy2 = i.next();
			if(dummy.equals(dummy2))
			{
				return dummy2;
			}
		}
		
		return null;
	}

	/**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * 
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) 
	{
		if(sourceVertex == null || destinationVertex == null)
		{
			throw new NullPointerException();
		}
		else if(!(towns.contains(sourceVertex) && towns.contains(destinationVertex)))
		{
			throw new IllegalArgumentException();
		}
		
		Road edgeS = new Road(sourceVertex, destinationVertex, weight, description);
		Road edgeD = new Road(destinationVertex, sourceVertex , weight, description);
		
		int indexS = getIndex(sourceVertex);
		int indexD = getIndex(destinationVertex);
		
		if(!roads.get(indexS).contains(edgeS))
		{
			roads.get(indexS).add(edgeS);
			roads.get(indexD).add(edgeD);
			temp.add(edgeS);
			return edgeS;
		}
		return null;
	}
	
	/**
	 * this method finds the index of the vertice
	 * @param vertex the vertex to find index of
	 * @return the index
	 */
	private int getIndex(Town vertex)
	{
		int index = -1;
		for(int x = 0; x < towns.size(); x++)
		{
			if(towns.get(x).equals(vertex))
			{
				index = x;
				break;
			}
			
		}
		return index;
	}

	/**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
	@Override
	public boolean addVertex(Town v) 
	{
		if(v == null)
		{
			throw new NullPointerException();
		}
		else if(!containsVertex(v))
		{
			
			towns.add(v);
			LinkedList<Road> e = new LinkedList<>();
			roads.add(e);
			return true;
		}
		
		
		return false;
	}

	/**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) 
	{
		if(sourceVertex == null || destinationVertex == null)
		{
			return false;
		}
		else if(!(containsVertex(sourceVertex) && containsVertex(destinationVertex)))
		{
			return false;
		}
		
		int index = getIndex(sourceVertex);
		Road dummy = new Road(sourceVertex, destinationVertex, "dummy");
		if(!roads.get(index).contains(dummy))
		{
			return false;
		}
		return true;
	}

	/**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
	@Override
	public boolean containsVertex(Town v) 
	{
		return towns.contains(v);
	}

	/**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
	@Override
	public Set<Road> edgeSet()
	{
		Set<Road> r = new HashSet<>();
		r.addAll(temp);
//		for(int x = 0; x < roads.size(); x++)
//		{
//			r.addAll(roads.get(x));
//		}
		
		return r;
	}

	/**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
	@Override
	public Set<Road> edgesOf(Town vertex)
	{
		if(vertex == null)
		{
			throw new NullPointerException();
		}
		if(!containsVertex(vertex))
		{
			throw new IllegalArgumentException();
		}
		
		int index = getIndex(vertex);
		Set<Road> r = new HashSet<>();
		r.addAll(roads.get(index));
		return r;
	}

	/**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight is greater than -1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) 
	{
		if(!(containsVertex(sourceVertex) && containsVertex(destinationVertex)))
		{
			return null;
		}
		
		int indexS = getIndex(sourceVertex);
		int indexD = getIndex(destinationVertex);
		
		Road r = new Road(sourceVertex, destinationVertex, weight, description);
		if(roads.get(indexS).remove(r) && roads.get(indexD).remove(r))
		{
			temp.remove(r);
		
			return r;
		}
		return null;
	}

	/**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
	@Override
	public boolean removeVertex(Town v) 
	{
		if(v == null)
		{
			return false;
		}
		if(!containsVertex(v))
		{
			return false;
		}
		
		int index = getIndex(v);
		towns.remove(index);
		
		for(int x = 0; x < roads.get(index).size(); x++)
		{
			Road dummy = roads.get(index).get(x);
			for(int y = 0; y < roads.size(); y++)
			{
				if(y == index)
				{
					continue;
				}
				roads.get(y).remove(dummy);
		
			}
			temp.remove(dummy);
			
		}
		
		roads.remove(index);
		return true;
	}

	/**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
	@Override
	public Set<Town> vertexSet()
	{
		Set<Town> t = new HashSet<>();
		t.addAll(towns);
		return t;
	}

	/**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList(String)
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */   
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) 
	{
		ArrayList<String> list = new ArrayList<>();
		dijkstraShortestPath(sourceVertex);

		int index = getIndex(destinationVertex);
		for(int x = 0; x < path.get(index).size(); x++)
		{
			list.add(path.get(index).get(x).getSource().getName() + " via " + path.get(index).get(x).getName() + " to " + path.get(index).get(x).getDestination().getName() + " " + path.get(index).get(x).getWeight() + " mi");
			
		}
			
		path.clear();
		map.clear();
		return list;
	}

	/**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
	@Override
	public void dijkstraShortestPath(Town sourceVertex)
	{
		path = new ArrayList<>();
		for(int i = 0; i < towns.size(); i++)
		{
			path.add(new LinkedList<Road>());
		}
		
		map.put(sourceVertex, 0);
		Priority p = new Priority();
		PriorityQueue<Road> p2 = new PriorityQueue<>(20, p);
		int index = getIndex(sourceVertex);
		
		for(int x = 0; x < roads.get(index).size(); x++)
		{
			p2.add(roads.get(index).get(x));
			map.put(roads.get(index).get(x).getDestination(), roads.get(index).get(x).getWeight());
			path.get(getIndex(roads.get(index).get(x).getDestination())).add(roads.get(index).get(x));
		}
		
		ArrayList<Town> vNodes = new ArrayList<>();
		vNodes.add(sourceVertex);
		
		while(p2.size() != 0)
		{
			Road tempR = p2.poll(); 
			Town dest = tempR.getDestination();
			if(vNodes.contains(dest))
			{
				continue;
			}
			
			index = getIndex(dest);
			
			for(int x = 0; x < roads.get(index).size(); x++)
			{
				
				Road r = roads.get(index).get(x);
				Town dest2 = r.getDestination();
				
				
				if(dest2 == tempR.getSource())
				{
					continue;
				}
				
				
				int bestD = r.getWeight() + map.get(r.getSource());
				
				if(map.get(dest2) == null)
				{
					map.put(dest2, bestD);
					
					Road br = new Road(sourceVertex, dest2, bestD, "dummy");
					p2.add(br);
					
					LinkedList<Road> j = path.get(getIndex(r.getSource()));
				
					path.get(getIndex(dest2)).addAll(j);
					path.get(getIndex(dest2)).add(r);
				}
				else if(map.get(dest2) > bestD)
				{
					map.put(dest2, bestD);
					
					Road br = new Road(sourceVertex, dest2, bestD, "dummy");
					p2.add(br);
					
					LinkedList<Road> j = path.get(getIndex(r.getSource()));
					path.get(getIndex(dest2)).clear();
					path.get(getIndex(dest2)).addAll(j);
					path.get(getIndex(dest2)).add(r);
				}
				else
				{
					continue;
				}
				
				
			}
			
			vNodes.add(dest);
		}
		
	}

}


