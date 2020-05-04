import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TownGraphManagerTest_STUDENT
{
	private TownGraphManager graph;
	private String [] t;
	
	@BeforeEach
	void setUp() throws Exception 
	{
		graph = new TownGraphManager();
		  t = new String[8];
		  int x = 65;
		  for (int i = 0; i < 8; i++) 
		  {
			  t[i] = ("Town_" + (char)(i+x));
			  graph.addTown(t[i]);
		  }
		  
		  graph.addRoad(t[0], t[2], 600, "Road_1");
		  graph.addRoad(t[0], t[6], 490, "Road_2");
		  graph.addRoad(t[1], t[3], 490, "Road_3");
		  graph.addRoad(t[1], t[4], 630, "Road_4");
		  graph.addRoad(t[1], t[5], 580, "Road_5");
		  graph.addRoad(t[2], t[4], 210, "Road_6");
		  graph.addRoad(t[5], t[3], 600, "Road_7");
		  graph.addRoad(t[5], t[4], 230, "Road_8");
		  graph.addRoad(t[6], t[7], 120, "Road_9");
		  graph.addRoad(t[0], t[4], 650, "Road_10");
		  graph.addRoad(t[0], t[5], 780, "Road_11");
	}

	@AfterEach
	void tearDown() throws Exception 
	{
		graph = null;
		t = null;
	}

	@Test
	void testAddRoad() 
	{
		ArrayList<String> roads = graph.allRoads();
		Road r =  new Road(new Town("Town_A"), new Town("Town_C"), 600, "Road_1");
		assertTrue(roads.contains(r.getName()));
	}

	@Test
	void testGetRoad()
	{
		Road r =  new Road(new Town("Town_A"), new Town("Town_C"), 600, "Road_1");
		assertEquals(r.getName(), graph.getRoad("Town_C", "Town_A"));
	}

	@Test
	void testAddTown() 
	{
		ArrayList<String> towns = graph.allTowns();
		assertTrue(towns.contains("Town_F"));
	}

	@Test
	void testGetTown() 
	{
		Town t3 = new Town("Town_H");
		assertEquals(t3, graph.getTown("Town_H"));
	}

	@Test
	void testContainsTown() 
	{
		assertEquals(true, graph.containsTown("Town_A"));
		assertEquals(false, graph.containsTown("Town_Z"));
	}

	@Test
	void testContainsRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(t[0], t[2]));
		assertEquals(false, graph.containsRoadConnection(t[0], t[1]));
	}

	@Test
	void testAllRoads() 
	{
		ArrayList<String> roads = graph.allRoads();
		Collections.sort(roads);
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_10", roads.get(1));
		assertEquals("Road_11", roads.get(2));
		assertEquals("Road_2", roads.get(3));
		assertEquals("Road_3", roads.get(4));
	}

	@Test
	void testDeleteRoadConnection()
	{
		assertEquals(true, graph.containsRoadConnection(t[6], t[7]));
		graph.deleteRoadConnection(t[6], t[7], "Road_9");
		assertEquals(false, graph.containsRoadConnection(t[6], t[7]));
	
	}

	@Test
	void testDeleteTown() 
	{
		assertEquals(true, graph.containsTown("Town_E"));
		graph.deleteTown(t[4]);
		assertEquals(false, graph.containsTown("Town_E"));
		assertEquals(false, graph.containsRoadConnection(t[1], t[4]));
	}

	@Test
	void testAllTowns()
	{
		ArrayList<String> towns = graph.allTowns();
		Collections.sort(towns);
		assertEquals("Town_A", towns.get(0));
		assertEquals("Town_B", towns.get(1));
		assertEquals("Town_C", towns.get(2));
		assertEquals("Town_D", towns.get(3));
		assertEquals("Town_E", towns.get(4));
		assertEquals("Town_F", towns.get(5));
		assertEquals("Town_G", towns.get(6));
		assertEquals("Town_H", towns.get(7));
	}

	@Test
	void testGetPath()
	{
		ArrayList<String> path = graph.getPath(t[5],t[2]);
		  assertEquals("Town_F via Road_8 to Town_E 230 mi",path.get(0).trim());
		  assertEquals("Town_E via Road_6 to Town_C 210 mi",path.get(1).trim());
	}

}
