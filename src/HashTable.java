/**
 * This class is the implementation of the
 * hash table for Project5.java. It uses
 * separate chaining with double ended linked
 * lists. It can display, insert, and delete
 * country Nodes from the list.
 * <p>
 * It can also find a specified country when
 * given a name, and can print the number
 * of empty spaces and collisions in the
 * table to the console.
 * 
 * @author Hailey Francis
 * @version December 3, 2019
 */
public class HashTable {
	
	private DoubleEndedList[] hashArr = new DoubleEndedList[311]; //double ended linked list with size specified from rubric
	
	/**
	 * HashTable Constructor
	 */
	public HashTable() {
		for(int i = 0; i < 311; i++) {
			hashArr[i] = new DoubleEndedList(); //fills array with new DoubleEndedLists
		}
	}
	
	/**
	 * Inserts country Node into proper list in hash table
	 * array, based on getIndex method. It inserts to the end
	 * of its list using the insertLast method in DoubleEndedList.
	 * 
	 * @param country -- name of country to be inserted
	 * @param gdpPerCapita -- gdp per capita of country to be inserted
	 */
	public void insert(String country, double gdpPerCapita) {
		Node newNode = new Node(country, gdpPerCapita);
		int index = getIndex(country);
		hashArr[index].insertLast(newNode); //assigns country to correct index
	}
	
	/**
	 * Retrieves the index that will be used when Node with
	 * given country is inserted into hash table array. It is
	 * the sum of all the character's unicode in the name string
	 * modulus 311.
	 * 
	 * @param name -- name of country
	 * @return -- index for hash table array
	 */
	public int getIndex(String name) {
		int unicode = 0;
		for(int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			unicode = unicode + c; //adds together unicode values of chars in country name
		}
		return unicode % 311; 
	}
	
	/**
	 * Finds and returns the country given in the argument
	 * by finding its index using getIndex method, and then
	 * it goes to the DoubleEndedList at that index in the hash
	 * table array using the find method in the DoubleEndedList.
	 * 
	 * @param country -- name of country to be found
	 * @return the GDP per capita of the country found, or -1 if not found
	 */
	public double find(String country) {
		int index = getIndex(country);
		Node temp = hashArr[index].find(country); //finds country in correct list
		if(temp != null) { //if found
			return temp.gdpPerCapita;
		}else { //if not found
			return -1.0;
		}
	}
	
	/**
	 * Finds the index of the country using the getIndex method,
	 * then goes to the DoubleEndedList at that index in the hash
	 * table array and deletes Node with that country using the
	 * delete method in DoubleEndedList. The method will check whether
	 * or not returned Node is null or not, and will print out
	 * whether or not the node was successfully deleted.
	 * 
	 * @param country -- name of country to be deleted
	 */
	public void delete(String country) {
		int index = getIndex(country);
		Node del = hashArr[index].delete(country);
		if(del != null) { //if node found and deleted
			System.out.println(country + " has been deleted from the hash table.");
		}else {
			System.out.println("Nothing to delete");
		}
	}
	
	/**
	 * Cycles through the hash table array and displays
	 * whether or not the DoubleEndedList at each index,
	 * and if it's filled it will use the displayList method
	 * in the DoubleEndedList.
	 */
	public void display() {
		System.out.println("Hash Table Contents:\n");
		for(int i = 0; i < 311; i++) {
			System.out.print(i + ".  "); //index
			if(hashArr[i].isEmpty()) {
				System.out.println("Empty"); //if list empty
			}else {
				hashArr[i].displayList(); //displays list at index
			}
		}
		System.out.println();
	}
	
	/**
	 * Cycles through the hash table array and checks
	 * whether or not the DoubleEndedLists are empty or
	 * have any collisions, and adds up the number of
	 * empty spaces and collisions. It then prints the
	 * amount of empty spaces and collisions to the bottom.
	 */
	public void printFreeAndCollisions() {
		int empty = 0;
		int collisions = 0;
		for(int i = 0; i < 311; i++) {
			if(hashArr[i].isEmpty()) { //if empty
				empty++; //adds to empty amount
			}
			if(!hashArr[i].isEmpty() && hashArr[i].getFirst().nextNode != null) { //if the list isn't empty and the next node after first isn't null
				collisions++; //add to collisions
			}
		}
		System.out.println("There are " + empty + " empty spaces and " + collisions + " collisions."); //prints
	}
	
}

/**
 * Template Node class from rubric. Would not let me use
 * "private" modifier.
 * 
 * @author Hailey Francis
 * @version November 25, 2019
 *
 */
class Node {
	String name;
	double gdpPerCapita;
	Node nextNode;
	
	public Node(String name, double gdpPerCapita) {
		this.name = name;
		this.gdpPerCapita = gdpPerCapita;
	} 
	public void printNode() {
		System.out.printf("%-25s%,-20.2f\n", name, gdpPerCapita);
	}
}

/**
 * This class is the implementation of the double ended 
 * linked list that is used for the hash table array in
 * HashTable.java.
 * <p>
 * It has references to the first and last Node in the list,
 * an insertLast method that inserts a Node to the end of the
 * list. There is also a delete method that deletes a node
 * with the given country name if it is found.
 * 
 * 
 * @author Hailey Francis
 *
 */
class DoubleEndedList {
	private Node first; //ref to first link
	private Node last; //ref to last link
	
	/**
	 * Constructor
	 */
	public DoubleEndedList() {
		first = null;
		last = null;
	}
	
	/**
	 * Returns the first Node in the list
	 * 
	 * @return first node in list
	 */
	public Node getFirst() {
		return first;
	}
	
	/**
	 * Returns true if the list is empty
	 * by checking if first is null.
	 * 
	 * @return true if first is null, false otherwise
	 */
	public boolean isEmpty() {
		return first == null;
	}
	
	/**
	 * Inserts node at the end of the list.
	 * 
	 * @param newNode -- node to be inserted into list
	 */
	public void insertLast(Node newNode) {
		if(isEmpty()) {
			first = newNode; //only item in list
		}else {
			last.nextNode = newNode; //changes current last's nextNode (null) into newNode
		}
		last = newNode; //changes last to the newNode
	}
	
	/**
	 * Deletes Node based on key.
	 * 
	 * @return Node to be deleted
	 */
	public Node delete(String name) {
		Node prev = null; //previous node
		Node curr = first; //current node
		while(curr != null && !(name.equals(curr.name))){ //cycles through list until country is found
			prev = curr; //previous node = current
			curr = curr.nextNode; //current node = next node
		}
		if(prev == null) { //if first item in list
			first = first.nextNode;
		}else { //(I've tried doing an else if for if the item curr != null and curr.nextNode == null and setting last = previous, but when I did this it did not delete Malawi from the tree. Written as is, Malawi is deleted from the tree.)
			prev.nextNode = curr.nextNode;
		}
		return curr;
	}
	
	/**
	 * Finds and returns Node if the country
	 * name matches.
	 * 
	 * @param name -- name of country being found
	 * @return country node if found, null if not
	 */
	public Node find(String name) {
		Node curr = first;
		while(curr != null && !curr.name.equals(name)) { //cycles through until it finds node with name or until null
			curr = curr.nextNode;
		}
		return curr; //null or found Node
	}
	
	/**
	 * Displays the contents of the DoublyLinkedList,
	 * including all nodes that are in the List, whether
	 * or not there are collisions.
	 */
	public void displayList() {
		Node curr = first;
		while(curr != null) { //cycles through, printing Nodes
			curr.printNode();
			if(curr.nextNode != null) {
				System.out.print("      "); //trying to get tab separation like in rubric. Off by a space or two when indexes get more digits
			}
			curr = curr.nextNode;
		}
	}
}