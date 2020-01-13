import java.util.ArrayList;
import java.util.LinkedList;




public class hashTable {
	
	int capacity = 10;
	double loadFactorThreshold = 0.75;
	double loadFactor;
	int numAccounts;
	ArrayList<LinkedList<Account>> table;
	
	public hashTable() {
		numAccounts = 0;
		loadFactor = 0;
		table = new ArrayList<LinkedList<Account>>(capacity);
		for (int i = 0; i < capacity; i++) {
			table.add(null);
		}
	}
	
	public void insert(String website, String username, String password) {
		numAccounts++;
				// new load factor with new number of keys
		loadFactor = ((double) numAccounts / capacity);
				// index of array to put key in
		int hashIndex = hashIndex(website);
				// New node to insert into hashTable
		Account a = new Account(website, username, password);
				// if index is null add a reference to a linked list to it
		if (table.get(hashIndex) == null) {
			table.set(hashIndex, new LinkedList<Account>());
		}
				// add node to linkedList referenced in the array index
		table.get(hashIndex).add(a);

		if (loadFactor >= loadFactorThreshold) {
			capacity = (capacity * 2) + 1;
			rehash();
		}
		
	}
	
	private void rehash() {
		// arrayList holder for new array
		ArrayList<LinkedList<Account>> newTable = new ArrayList<LinkedList<Account>>(capacity);
		// add null reference in indexes of arrayList
		for (int i = 0; i < capacity; i++) {
			newTable.add(null);
		}
		// iterate through arrayList
		for (LinkedList<Account> i : table) {
			// if index in array has at least one key in it
			if (i != null) {
				// iterate through linked list
				for (Account j: i) {
					String currentWebsite = j.website;
					String currentUsername = j.getUsername();
					String currentPassword = j.getPassword();
					// Node to be copied over to new table
					Account a = new Account(currentWebsite, currentUsername, currentPassword);
					// new hash index for the key
					int newHashIndex = hashIndex(currentWebsite);
					// if the index is null add a reference to a linked list
					if (newTable.get(newHashIndex) == null) {
						newTable.set(newHashIndex, new LinkedList<Account>());
					}
					// add node to linked list
					newTable.get(newHashIndex).add(a);

				}
			}
		}

		// change the hash table to the new one created
		table = newTable;

		// calculate the new loadFactor with the bigger capacity
		loadFactor = ((double) numAccounts / capacity);
	}
	
	private int hashIndex(String key) {
		return Math.abs(key.hashCode()) % capacity;
	}
	
	public boolean remove(String website)  {
		
		// get index in the array where the key is
		int hashIndex = hashIndex(website);
		// if the index is null the key is not in the hashTable so false
		if (table.get(hashIndex) == null) {
			return false;
		}
		// iterate through the linked list at the index in the array
		for (Account a : table.get(hashIndex)) {
			// if the key is found in the linked list delete the node and return true
			if (a.website.equals(website)) {
				table.get(hashIndex).remove(a);
				// if there are no keys in the list set the reference to null
				if (table.get(hashIndex).size() == 0) {
					table.set(hashIndex, null);
				}
				// decrease number of keys
				numAccounts--;
				return true;
			}
		}
		// if the key is not found in the linked list return false
		return false;
	}

	public Account get(String website)  {

		
		// find the index in the array where the key is
		int hashIndex = hashIndex(website);
		// if the index in the array where the key would be is null throw
		
		// iterate through the linked list at the index in the array
		for (Account a : table.get(hashIndex)) {
			// if the key is found in the linked list return it's book
			if (a.website.equals(website)) {
				return a;
			}
		}
		// if the key is not found in the linked list throw keyNotFoundException
		return null;
	}
	
	public int numAccounts() {
		// returns the number of key, value pairs in the data structure
		return numAccounts;
	}

}
