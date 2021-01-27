import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class uses HashTable.java to create
 * a hash table using data from the Countries5.csv file,
 * or any Countries.csv file that is given by the user.
 * <p>
 * It displays the hash table, deletes Austalia, Tunisia,
 * and Norway from the Tree.
 * <p>
 * It then searches for Sri Lanka, North Cyprus, and
 * Tunisia, returning the GDP per capita of those
 * countries if they are found.
 * <p>
 * Then it deletes Germany, Ireland, Bolivia, Malawi
 * and Greece from the table, and displays the table after.
 * <p>
 * Finally, it will print the number of empty spaces and
 * collisions in the tree.
 * 
 * @author Hailey Francis
 * @version December 3, 2019
 */
public class Project5 {

	/**
	 * MAIN METHOD
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("COP3538 Project 5 -- Hash Tables");
		System.out.println("Instructor: Xudong Liu\n");
		
		//input from user
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the file name: ");
		String fileName = input.next();
		input.close();
		System.out.println();
		
		HashTable hashTable = new HashTable(); //new hash table
		
		//insert into hash table
		readFile(fileName, hashTable);
		
		//display hash table
		hashTable.display();
		
		//delete Australia, Tunisia, and Norway
		hashTable.delete("Australia");
		hashTable.delete("Tunisia");
		hashTable.delete("Norway");
		
		System.out.println();
		
		//search for Sri Lanka, North Cyprus, and Tunisia
		findCountry(hashTable, "Sri Lanka");
		findCountry(hashTable, "North Cyprus");
		findCountry(hashTable, "Tunisia");
		
		System.out.println();
		
		//delete Malawi, Germany, Ireland, Greece, and Bolivia
		hashTable.delete("Malawi");
		hashTable.delete("Germany");
		hashTable.delete("Ireland");
		hashTable.delete("Greece");
		hashTable.delete("Bolivia");
		
		System.out.println();
		
		//display again
		hashTable.display();
		
		//print number of empty cells and number of cells with collisions
		hashTable.printFreeAndCollisions();

	}
	
	/**
	 * This method uses the find method in HashTable.java, and then prints out
	 * the results of whether or not the specified country was found to the console.
	 * If it was found, it also prints its GDP per capita.
	 * 
	 * @param -- hashTable HashTable to search
	 * @param name -- name of country to be searched for
	 */
	public static void findCountry(HashTable hashTable, String name) {
		double gdpPerCapita = hashTable.find(name);
		if(gdpPerCapita != -1.0) {
			System.out.println(name + " has been found with a GDP per capita of " + gdpPerCapita);
		}else {
			System.out.println(name + " was not found.");
		}
	}

	/**
	 * Reads data from file Countries5.csv, creating Nodes from the data
	 * and inserting them into the hash table accordingly.
	 * 
	 * @param fileName -- name of file to be read
	 * @param binSearchTreeTemp -- Binary Search Tree to have data inserted
	 */
	public static void readFile(String fileName, HashTable hashTable) {
		int numRead = 0;
		String name, code, capital; 
		int population, rank;
		double GDP; // doubles will all be read as exponentials
		Scanner r;
		// OPENING FILE //
		try {
			r = new Scanner(new File(fileName));
			r.useDelimiter("\\,|\r\n|\n");
		    // READING FILE //
			for(int i = 0; i < 6; i++) { //meant to cycle through first line of file, which presumably is labels such as in Countries1.csv and Countries2.csv
				r.next();
			}
			while(r.hasNext()) { //assigns values from file to variables 
				// only name, GDP, and population are ultimately used, but all variables are assigned because of format of csv file. //
				name = r.next();
				code = r.next();
				capital = r.next();
				population = r.nextInt();
				GDP = r.nextDouble();
				rank = r.nextInt();
				
				double gdpPerCapita = GDP / (double) population;
				
				hashTable.insert(name, gdpPerCapita);
				numRead++;
			}
			System.out.println("There were " + numRead + " country records read into hash table.\n");
		}catch(InputMismatchException i) {
			System.out.println("Input Mismatch Exception. Program likely tried assigning value to wrong variable due to format of file.");
		}catch(NumberFormatException j) {
			System.out.println("Number format exception. Program likely tried assigning value to wrong variable due to format of file.");
		}catch(FileNotFoundException e) {
			System.out.println("File not found. "
				+ "Please make sure you entered the name of the file correctly "
				+ "and that the file is accessable the next time you run this program.\n"
				+ "(This project was submitted with a file called Countries4.csv.) [This error was thrown by the readFile method]");
		}catch(NullPointerException n) {
			System.out.println("Null pointer exception in readFile");
		}
	}

}
