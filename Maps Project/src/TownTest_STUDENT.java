import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TownTest_STUDENT 
{
	Town t1;
	@BeforeEach
	void setUp() throws Exception 
	{
		t1 = new Town("Lagos");
	}

	@AfterEach
	void tearDown() throws Exception 
	{
		t1 = null;
	}

	@Test
	void testHashCode()
	{
		assertEquals("Lagos".hashCode(), t1.hashCode());
	}

	@Test
	void testGetName()
	{
		assertEquals("Lagos", t1.getName());
	}

	@Test
	void testToString() 
	{
		assertEquals("Town Name: Lagos", t1.toString());
	}

	@Test
	void testEqualsObject()
	{
		Town t2 = new Town("Lagos");
		Town t3 = new Town("Warri");
		assertTrue(t1.equals(t2));
		assertFalse(t1.equals(t3));
	}

	@Test
	void testCompareTo() 
	{
		Town t2 = new Town("Lagos");
		Town t3 = new Town("Warri");
		assertEquals(0, t1.compareTo(t2));
		assertEquals(-1, t1.compareTo(t3));
	}

}
