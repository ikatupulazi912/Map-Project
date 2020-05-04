import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoadTest_STUDENT {

	Road r;
	Town t1;
	Town t2;
	@BeforeEach
	void setUp() throws Exception 
	{
		t1 = new Town("Farville");
		t2 = new Town("Closeville");
		
		r = new Road(t1, t2, 5, "I-270");
		
	}

	@AfterEach
	void tearDown() throws Exception {
		r = null;
	}

	@Test
	void testContains() {
		Town t3 = new Town("Middle");
		
		assertTrue(r.contains(t1));
		assertFalse(r.contains(t3));
	}

	@Test
	void testGetName()
	{
		assertEquals("I-270", r.getName());
	}

	@Test
	void testGetDestination() 
	{
		assertEquals(t2, r.getDestination());
		
	}

	@Test
	void testGetSource()
	{
		assertEquals(t1, r.getSource());
	}

	@Test
	void testGetWeight() 
	{
		assertEquals(5, r.getWeight());
		
	}

	@Test
	void testEqualsObject()
	{
		Road r2 = new Road(t2, t1, 5, "I-270" );
		assertTrue(r2.equals(r));
	}

	@Test
	void testToString()
	{
		assertEquals("Road Name: I-270\nSource: Farville\nDestination: Closeville\nDistance: 5\n", r.toString());
	}

	@Test
	void testCompareTo()
	{
		Road r2 = new Road(t2, t1, 5, "I-270" );
		assertTrue(r2.compareTo(r) == 0);
	}

}
