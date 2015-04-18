import static org.junit.Assert.*;

import org.junit.Test;


public class MoveToFrontListTests {

	@Test
	public void testConstruction() {
		MoveToFrontList l = new MoveToFrontList();
		assertNotNull(l);
	}
	@Test
	public void testFindOnEmptyList() {
		MoveToFrontList l = new MoveToFrontList();
		assertNull("I should't find anything in an empty list!", l.find(""));
	}
	@Test
	public void testSizeOnEmptyList() {
		MoveToFrontList l = new MoveToFrontList();
		assertEquals("Size of empty list should be 0", l.size(), 0);
	}
	@Test
	public void testRankWithNoItems() {
		MoveToFrontList l = new MoveToFrontList();
		assertEquals("Size of empty list should be 0", l.size(), 0);
		assertEquals("Check the rank for an empty list...", 
				l.rank("Hi"), 0);
	}
	@Test
	public void testAddMultipleItems() {
		MoveToFrontList l = new MoveToFrontList();
		l.incrementCount("Hi");
		assertEquals("Adding to the list with incrementCount is broken, or size() returns a bad value", 
				l.size(), 1);
		l.incrementCount("Second Hi");
		assertEquals("Adding the second item to the list is broken or size() returns the wrong value after the first case",
				l.size(), 2);
		l.incrementCount("Third Hi");
		assertEquals("Adding the third item to the list is broken or size() returns the wrong value after the second case",
				l.size(), 3);
		l.incrementCount("Fourth and last Hi");
		assertEquals("Adding the fourth item to the list is broken or size() returns the wrong value after the third case",
				l.size(), 4);
		
	}
	
	
	@Test
	public void testRankWithOneItem() {
		MoveToFrontList l = new MoveToFrontList();
		l.incrementCount("Hi");
		
		// test rank with one item
		assertEquals("Adding to the list with incrementCount is broken, or size() returns a bad value", 
				l.size(), 1);
		assertEquals("The rank of a your first item should be 0", 
				l.rank("Hi"), 0);
		
		// test rank of item not in list
		assertEquals("The rank of an item not in list should return the size of the list", 
				l.rank("Hip"), 1);	
	}
	
	@Test
	public void testRankWithMultipleItems() {
		MoveToFrontList l = new MoveToFrontList();
		
		// one item
		l.incrementCount("First");
		
		assertEquals("Adding to the list with incrementCount is broken or size() returns a bad value",
				l.size(), 1);
		assertEquals("The rank of your first item should be 0",
				l.rank("First"), 0);
		
		// test rank of something not in list
		assertEquals("The rank of an item not in the list should return the size of the list",
				l.rank("Not in list"), 1);
		
		// two items
		l.incrementCount("Second");
		
		assertEquals("size() of a list with 2 items should return 2",
				l.size(), 2);
		assertEquals("The rank of your second item should be 1",
				l.rank("Second"), 1);
		
		// three items
		l.incrementCount("Third");
		
		assertEquals("size() of a list with 3 items should return 3",
				l.size(), 3);
		assertEquals("The rank of your third item should be 2",
				l.rank("Third"), 2);
		
	}
	
	@Test
	public void testReinsertMultipleItems(){
		MoveToFrontList l = new MoveToFrontList();
		
		l.incrementCount("First");
		assertEquals("Adding to the list with incrementCount is broken or size() returns a bad value",
				l.size(), 1);		
		
		
		// test increment count duplicate string here
		l.incrementCount("First");
		assertEquals("Using incrementCount on an item already in list should remove that item and reinsert itself", 
				l.size(), 1);
		
		// add three more elements
		l.incrementCount("Second");
		assertEquals("Adding to the list with incrementCount is broken or size() returns a bad value",
				l.size(), 2);
		
		l.incrementCount("Third");
		assertEquals("Adding to the list with incrementCount is broken or size() returns a bad value",
				l.size(), 3);
		
		l.incrementCount("Fourth");
		assertEquals("Adding to the list with incrementCount is broken or size() returns a bad value",
				l.size(), 4);
		
		
		// test duplicate increment counts here
		l.incrementCount("Second");
		assertEquals("Using incrementCount on an item already in the list should remove that items and reinsert itself",
				l.size(), 4);
		
		l.incrementCount("Third");
		assertEquals("Using incrementCount on an item already in the list should remove that items and reinsert itself",
				l.size(), 4);
		
		l.incrementCount("Fourth");
		assertEquals("Using incrementCount on an item already in the list should remove that items and reinsert itself",
				l.size(), 4);
	}
}