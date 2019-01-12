import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/************************************************************************************************
 * 
 * Xingyi Wu CST8130_300 040887028 Assignment 4-Inventory System_Hash Algorithm
 * and Collision 2018-04-20 Winter 2018 Purpose: This class holds the data
 * structure for the Inventory Data members: inventory:
 * arrayList<LinkedList<Item>> of Item objects numItems - current number of
 * items in the array Methods: addItem(Scanner):boolean - insert in data members
 * via hash algorithm and deal with collision via LinkedList in this class from
 * the Scanner object toString(): String - sends contents of data members to a
 * String from scanner object toFileString: String - sends contents of data
 * members to a String when writing to a file - a file alreadyExists(Item): int
 * - the search method to see if itemCode in Item object exists in the arrayList
 * (returns that item of where it is found or null if not found) search(): void
 * - search an item via itemCode from the arrayList<LinkedList<>> and print out
 * the index of arrayList and index in LinkedList updateItem(Scanner): boolean -
 * updates the quantity of the Item object nameFile(FileWriter): void - name a
 * file and call the writeFile() save all inventory data to a file - writeFile
 * (FileWriter): void to fill in the data members from FileWriter object
 * readFile(Scanner): void - input a file and call scanFile(Scanner) to saving
 * data to inventory scanFile(Scanner): boolean - read data member from scanner
 * object display
 *
 ***********************************************************************************************/

public class Inventory {

	private ArrayList<LinkedList<Item>> inventory = new ArrayList<LinkedList<Item>>(100);
	private Iterator<Item> iterator;

	public Inventory(int size) {
		if (size < 0) {
			size = 0;
		}
		inventory = new ArrayList<LinkedList<Item>>(size);

		for (int i = 0; i < size; i++) {
			inventory.add(i, new LinkedList<Item>());
		}
	}

	public boolean addItem(Scanner input) {

		System.out.print("Do you wish to add a purchased item (enter P/p) or manufactured (enter anything else)? ");

		char option = input.next().charAt(0);

		Item xitem = new Item();

		if (option == 'P' || option == 'p') {
			xitem = new PurchasedItem();
		} else {
			xitem = new ManufacturedItem();
		}

		input.nextLine();
		xitem.addItem(input);

		if (alreadyExists(xitem) == null) {
			inventory.get(xitem.hashIndex()).add(xitem);

			return true;
		} else {
			return false;
		}
	}

	public boolean addItemFromFile(Scanner file) {

		Item newItem;
		String type = "";

		if (file.hasNext())
			type = file.next();
		else
			return false;

		if (type.charAt(0) == 'P' || type.charAt(0) == 'p')
			newItem = new PurchasedItem();
		else
			newItem = new ManufacturedItem();

		if (newItem.addItemFromFile(file)) {
			if (alreadyExists(newItem) == null) {
				inventory.get(newItem.hashIndex()).add(newItem);
				return true;
			} else {
				System.out.println("Item already in inventory");
				return true;
			}
		} else
			return false;
	}

	public String toString() {

		String allItems = "";

		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.size() != 0)
				for (int k = 0; k < inventory.get(i).size(); k++) {
					allItems += inventory.get(i).get(k).toString() + "\n";
				}
		}
		return allItems;
	}

	public String FileString() {

		String allItems = "";

		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.size() != 0)
				for (int k = 0; k < inventory.get(i).size(); k++) {
					allItems += inventory.get(i).get(k).toFileString() + "\n";
				}
		}
		return allItems;

	}

	public Item alreadyExists(Item obj) {

		int index = obj.hashIndex();

		if (inventory.get(index).size() == 0) {

			return null;
		} else {
			iterator = inventory.get(index).iterator();

			Item tempItem;
			while (iterator.hasNext()) {
				tempItem = iterator.next();
				if (tempItem.isEqual(obj))
					return tempItem;
			}

		}

		return null;

	}

	public void search() {
		Item item = new Item();
		Scanner input = new Scanner(System.in);

		if (item.inputCode(input)) {

			int arrayIndex = item.hashIndex();

			item = alreadyExists(item);
			if (item == null) {
				System.out.println("Item cannot be found.");
			} else {
				if (alreadyExists(item) != null) {

					for (int i = 0; i < inventory.get(arrayIndex).size(); i++) {

						if (inventory.get(arrayIndex).get(i).isEqual(item))
							System.out.println(item.toString() + ": at index of arrayList " + arrayIndex
									+ ", at index of linkedlist " + i);
					}
				} else {
					System.out.println("Item cannot be found.");
				}
			}
		}

	}

	public boolean updateQuantity(Scanner input, boolean option) {

		Item temp = new Item();

		int quantity = 0;

		temp.inputCode(input);
		temp = alreadyExists(temp);

		if (temp == null) {
			System.out.print("Code not found in inventory...\n");
			return false;
		}

		input.nextLine();
		while (true) {
			System.out.println("Enter valid quantity: ");
			if (!input.hasNextInt()) {
				System.out.print("Invalid quantity...please enter integer greater than 0: ");
				input.nextLine();
			} else {
				quantity = input.nextInt();
				if (quantity > 0) {
					break;
				} else {
					System.out.print("Invalid quantity...please enter integer greater than 0: ");
					input.nextLine();
				}
			}
		}

		if (option) {
			if (temp.updateItem(quantity)) {
				return true;
			} else {
				return false;
			}
		}

		if (!option) {
			if (temp.updateItem(-quantity)) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	public void nameFile(FileWriter outFile) {

		Scanner keyboard = new Scanner(System.in);
		String fileName = new String();

		char option = 0;

		System.out.print("\n\nEnter name of file to write to(.txt): ");
		fileName = keyboard.next();
		File writtenFile = new File(fileName);

		try {
			if (writtenFile.exists()) {

				System.out.print("This file is already existing, would you like it to be overwritten?(Y/N) ");
				option = keyboard.next().charAt(0);

				if (option != 'y' && option != 'Y') {

					do {
						System.out.println("Please enter a new file name: ");

						fileName = keyboard.next();
						writtenFile = new File(fileName);

					} while (writtenFile.exists());
				}

			}
			outFile = new FileWriter(fileName);
			writeFile(outFile);
			System.out.println(fileName + " is successfully created.");

		} catch (IOException e) {
			System.out.println("Could not open file...." + fileName + "exiting");
		}

	}

	public void writeFile(FileWriter outFile) {

		try {
			for (int i = 0; i < inventory.size(); i++) {
				for (int k = 0; k < inventory.get(i).size(); k++) {
					outFile.append(inventory.get(i).get(k).toFileString() + "\n");
				}
			}
			outFile.close();
		} catch (IOException e) {
			System.out.println("Error writing to file");
		}
	}

	public void readFile(Scanner inputFile) {

		String fileName = new String();
		Scanner inFile = null;

		System.out.print("\n\nEnter name of file to process: ");
		fileName = inputFile.next();

		File file = new File(fileName);
		try {
			if (file.exists()) {
				inFile = new Scanner(file);
				if (!inFile.hasNext()) {
					System.out.println("File is empty, nothing to read.");
				} else {
					while (addItemFromFile(inFile)) {

					}
					System.out.println("File is successfully read.");
				}

			} else {
				throw new IOException();
			}
		} catch (IOException e) {
			System.out.println("Could not open file...." + fileName);
		}
	}

}
