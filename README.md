# DataStructures_Project5_Fall2019
COP 3538: Project 5 -- Hash Table
 * @author Hailey Francis (n01402670@unf.edu)
 * @version December 3, 2019

Project5.java
 * This class uses HashTable.java to create
   a hash table using data from the Countries5.csv file,
   or any Countries.csv file that is given by the user.
 * It displays the hash table, deletes Austalia, Tunisia,
   and Norway from the Tree.
 * It then searches for Sri Lanka, North Cyprus, and
   Tunisia, returning the GDP per capita of those
   countries if they are found.
 * Then it deletes Germany, Ireland, Bolivia, Malawi
   and Greece from the table, and displays the table after.
 * Finally, it will print the number of empty spaces and
   collisions in the tree.
   
HashTable.java
 * This class is the implementation of the
   hash table for Project5.java. It uses
   separate chaining with double ended linked
   lists. It can display, insert, and delete
   country Nodes from the list.
 * It can also find a specified country when
   given a name, and can print the number
   of empty spaces and collisions in the
   table to the console.
