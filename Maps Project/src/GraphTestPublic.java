

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class GraphTestPublic {
	private GraphInterface<Town,Road> graph;
	private Town[] town;

	@Before
	public void setUp() throws Exception {
		 graph = new TownGraph();
		  town = new Town[3];
		  
		  for (int i = 0; i < 3; i++) {
			  town[i] = new Town("Town_" + i);
			  graph.addVertex(town[i]);
		  }
		  
		  graph.addEdge(town[0], town[1], 2, "Road_1");
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testGetEdge() {
		assertEquals(new Road(town[1], town[0],2, "Road_1"), graph.getEdge(town[1], town[0]));
	}

	@Test
	public void testAddEdge() {
		assertEquals(false, graph.containsEdge(town[2], town[1]));
	}

}