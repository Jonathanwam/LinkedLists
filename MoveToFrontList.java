public class MoveToFrontList {

	private StringCountElement head; // the head reference
	private StringCountElement current; // the current reference
	private int size; // the size of the list (number of valid items)

	/**
	 * _Part 1: Implement this constructor._
	 * 
	 * Creates a new, initially empty MoveToFontList. This list should be a
	 * linked data structure.
	 */
	public MoveToFrontList() {
		this.head = new StringCountElement();
		this.current = new StringCountElement();
		this.size = 0;

	}

	/**
	 * This method increments the count associated with the specified string
	 * key. If no corresponding key currently exists in the list, a new list
	 * element is created for that key with the count of 1. When this method
	 * returns, the key will have rank 0 (i.e., the list element associated with
	 * the key will be at the front of the list)
	 * 
	 * @param key
	 *            the string whose count should be incremented
	 * @return the new count associated with the key
	 */
	public int incrementCount(String key) {
		StringCountElement s = find(key);
		if (s != null) {
			System.out.println("Found the key, " + key + " which the s.key is, " + s.key + " and s is, " + s);
			// found the key, splice it out and increment the count
			spliceOut(s);
			s.count++;
		} else {
			// need to create a new element
			s = new StringCountElement();
			s.key = key;
			s.count = 1;
		}
		// move it to the front
		spliceIn(s, 0);
		return s.count;
	}

	/**
	 * 
	 * @return the number of items in the list
	 */
	public int size() {
		return size;
	}

	/**
	 * _Part 2: Implement this method._
	 * 
	 * Find the list element associated with the specified string. That is, find
	 * the StringCountElement that a key equal to the one specified
	 * 
	 * @param key
	 *            the key to look for
	 * @return a StringCountElement in the list with the specified key or null
	 *         if no such element exists.
	 */
	public StringCountElement find(String key) {
		current = head;
		
		while (current != null && current.key != null){
			if (current.key.equals(key)){
				return current;
			}
			
			current = current.next;
		}
		
		return null;
	}

	/**
	 * _Part 3: Implement this method._
	 * 
	 * Compute the rank of the specified key. Rank is similar to position, so
	 * the first element in the list will have rank 0, the second element will
	 * have rank 1 and so on. However, an item that does not exist in the list
	 * also has a well defined rank, which is equal to the size of the list. So,
	 * the rank of any item in an empty list is 0.
	 * 
	 * @param key
	 *            the key to look for
	 * @return the rank of that item in the rank 0...size() inclusive.
	 */
	public int rank(String key) {
		int rank = 0;
		current = head;
		
		// if empty list, return 0
		if (size == 0){
			return 0;
		}
		
		while (rank < size){
			if (current.key.equals(key)) return rank;
			System.out.println("I got here. Incrementing rank while testing key: " + key);
			rank++;
			current = current.next;
		}
		
		// Did not find key in list
		return size;
		
	}

	/**
	 * _Part 4: Implement this method._
	 * 
	 * Splice an element into the list at a position such that it will obtain
	 * the desired rank. The element should either be new, or have been spliced
	 * out of the list prior to being spliced in. That is, it should be the case
	 * that: s.next == null && s.prev == null
	 * 
	 * @param s
	 *            the element to be spliced in to the list
	 * @param desiredRank
	 *            the desired rank of the element
	 */
	public void spliceIn(StringCountElement s, int desiredRank) {
		current = head;
		StringCountElement tmp = null; // holds onto previous currents
		
		// First item to add to list manually done here
		if (desiredRank == 0 && head == null){
			head.key = s.key;
			size++;
		}
		
		// if item wants to be at beginning but the list is not empty...
		if (desiredRank == 0 && head != null){

			head = s;
			head.key = s.key;
			head.next = current;
			current.prev = head;

			size++;
			return; // placed new element at head. We're done here!
		}
		
		// place 'current' at the desiredRank
		for (int i = 0; i < desiredRank; i++){
			tmp = current;
			current = current.next;
		}
		
		// add element before 'current' element
		tmp.next = s;
		current.prev = s;
		s.next = current;
		s.prev = tmp;
		size++;
	}

	/**
	 * _Part 5: Implement this method._
	 * 
	 * Splice an element out of the list. When the element is spliced out, its
	 * next and prev references should be set to null so that it can safely be
	 * splicedIn later. Splicing an element out of the list should simply remove
	 * that element while maintaining the integrity of the list.
	 * 
	 * @param s
	 *            the element to be spliced out of the list
	 */
	public void spliceOut(StringCountElement s) {
		System.out.println("Size = " + size);
		if ( head.key.equals(s.key)){
			head = head.next;
			size--;
			System.out.println(size);
			return;
		}
		
		current = head;
		StringCountElement tmp = null; // holds onto previous currents
		
		while (current != null && !current.key.equals(s.key)){
			tmp = current;
			current = current.next;
		}
		
		// delete 'current' by having previous element skip it
		tmp.next = current.next;
		size--;
		System.out.println(size);
	}
	
	
	// Was used for testing the above code
	public void printList(){
		current = head;
		int rank = 0;
		while (current != null){
			System.out.println("At rank " + rank + ": " + current.key);
			rank++;
			current = current.next;
		}
	}
}