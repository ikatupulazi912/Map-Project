import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TownGraphTest_STUDENT
{
	TownGraph graph;
	Town[] t;
	@BeforeEach
	void setUp() throws Exception 
	{
		graph = new TownGraph();
		  t = new Town[8];
		  int x = 65;
		  for (int i = 0; i < 8; i++) 
		  {
			  t[i] = new Town("Town_" + (char)(i+x));
			  graph.addVertex(t[i]);
		  }
		  
		  graph.addEdge(t[0], t[2], 600, "Road_1");
		  graph.addEdge(t[0], t[6], 490, "Road_2");
		  graph.addEdge(t[1], t[3], 490, "Road_3");
		  graph.addEdge(t[1], t[4], 630, "Road_4");
		  graph.addEdge(t[1], t[5], 580, "Road_5");
		  graph.addEdge(t[2], t[4], 210, "Road_6");
		  graph.addEdge(t[5], t[3], 600, "Road_7");
		  graph.addEdge(t[5], t[4], 230, "Road_8");
		  graph.addEdge(t[6], t[7], 120, "Road_9");
		  graph.addEdge(t[0], t[4], 650, "Road_10");
		  graph.addEdge(t[0], t[5], 780, "Road_11");
	}

	@AfterEach
	void tearDown() throws Exception
	{
		t = null;
		graph = null;
	}

	@Test
	void testGetEdge() 
	{
		assertEquals(new Road(t[0], t[2], 600, "Road_1"), graph.getEdge(t[0], t[2]));
		assertEquals(new Road(t[1], t[4], 630, "Road_4"), graph.getEdge(t[1], t[4]));
	}

	@Test
	void testAddEdge() 
	{
		assertEquals(false, graph.containsEdge(t[3], t[6]));
		
		graph.addEdge(t[3], t[6], 1, "Road_100");
		
		assertEquals(true, graph.containsEdge(t[3], t[6]));
	}

	@Test
	void testAddVertex() 
	{
		Town t2 = new Town("I");
		
		assertEquals(false, graph.containsVertex(t2));
		
		graph.addVertex(t2);
		assertEquals(true, graph.containsVertex(t2));
	}

	@Test
	void testContainsEdge() 
	{
		assertEquals(true, graph.containsEdge(t[7], t[6]));
		assertEquals(false, graph.containsEdge(t[2], t[7]));
	}

	@Test
	void testContainsVertex() 
	{
		assertEquals(true, graph.containsVertex(new Town("Town_A")));
		assertEquals(false, graph.containsVertex(new Town("Town_T")));
	}

	@Test
	void testEdgeSet()
	{
		Set<Road> r = graph.edgeSet();
		ArrayList<String> list = new ArrayList<String>();
		
		for(Road r2 : r)
		{
			list.add(r2.getName());
		}
			
		Collections.sort(list);
		assertEquals("Road_1", list.get(0));
		
		assertEquals("Road_10", list.get(1));
		
		assertEquals("Road_11", list.get(2));
		assertEquals("Road_2", list.get(3));
		assertEquals("Road_3", list.get(4));
		assertEquals("Road_6", list.get(7));
		
	}

	@Test
	void testEdgesOf() 
	{
		Set<Road> r = graph.edgesOf(t[0]);
		
		ArrayList<String> list = new ArrayList<String>();
		
		for(Road road : r)
		{
			list.add(road.getName());
		}
			
		Collections.sort(list);
		
		assertEquals("Road_1", list.get(0));
		
		assertEquals("Road_10", list.get(1));
		assertEquals("Road_11", list.get(2));
		assertEquals("Road_2", list.get(3));
	}

	@Test
	void testRemoveEdge() 
	{
		assertEquals(true, graph.containsEdge(t[6], t[7]));
		graph.removeEdge(t[6], t[7], 620, "Road_9");
		assertEquals(false, graph.containsEdge(t[6], t[7]));
	}

	@Test
	void testRemoveVertex()
	{
		assertEquals(true, graph.containsVertex(t[7]));
		graph.removeVertex(t[7]);
		assertEquals(false, graph.containsVertex(t[7]));
	}

	@Test
	void testVertexSet() 
	{
		Set<Town> r = graph.vertexSet();
		assertEquals(true, r.contains(t[0]));
		
		assertEquals(true, r.contains(t[7]));
		
		assertEquals(true, r.contains(t[4]));
		
		assertEquals(true, r.contains(t[6]));
		assertEquals(true, r.contains(t[2]));
		
	}

	@Test
	void testShortestPathAndDijkstraShortestPath() 
	{
		//From Town E to Town D
		ArrayList<String> list;
		list = graph.shortestPath(t[4], t[3]);
		assertEquals("Town_E via Road_8 to Town_F 230 mi",list.get(0));
		  assertEquals("Town_F via Road_7 to Town_D 600 mi",list.get(1));
	}

}
