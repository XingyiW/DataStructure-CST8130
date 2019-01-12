import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/************************************************************************************************
 * 
 * Xingyi Wu CST8130_300 040887028 Assignment 2-Inventory System 2018-03-09
 * Winter 2018 Purpose: This class holds the data structure for the Inventory
 * Data members: inventory: arrayList<> of Item objects numItems - current
 * number of items in the array Methods: addItem(Scanner):boolean - insert in
 * data members in an ascending order in this class from the Scanner object
 * toString(): String - sends contents of data members to a String from scanner
 * object toFileString: String - sends contents of data members to a String when
 * writing to a file - a file alreadyExists(Item): int - the search method to
 * see if itemCode in Item object exists in the array (returns index of where it
 * is found or -1 if not found) updateItem(Scanner): boolean - updates the
 * quantity of the Item object nameFile(FileWriter): void - name a file and call
 * the writeFile() save all inventory data to a file - writeFile (FileWriter):
 * void to fill in the data members from FileWriter object readFile(Scanner):
 * void - input a file and call scanFile(Scanner) to saving data to inventory
 * scanFile(Scanner): boolean - read data member from scanner object
 *
 ***********************************************************************************************/

public class Inventory {

	private ArrayList<Item> inventory;
	private int numItems;

	public Inventory(int size) {
		if (size < 0) {
			size = 0;
		}
		inventory = new ArrayList<Item>();
		numItems = 0;
	}

	public int newCodeIndex(Item obj) {

		int midIndex = 0;
		int firIndex = 0;
		int endIndex = inventory.size() - 1;

		if (inventory.isEmpty()) {
			return 0;
		}
		do {
			midIndex = (firIndex + endIndex) / 2;

			if (endIndex < firIndex) {
				return firIndex;
			}

			if (obj.orderCode(inventory.get(midIndex))) {
				endIndex = midIndex - 1;
			} else
				firIndex = midIndex + 1;

		} while (firIndex <= endIndex);

		return firIndex;

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

		if (alreadyExists(xitem) == -1) {
			inventory.add(newCodeIndex(xitem), xitem);
			numItems++;
			return true;
		} else {
			return false;
		}

	}

	public String toString() {

		String allItems = "";

		for (int i = 0; i < numItems; i++) {
			allItems += inventory.get(i).toString() + "\n";
		}
		return allItems;
	}

	public String toFileString() {

		String allItems = "";

		for (int i = 0; i < numItems; i++) {
			allItems += inventory.get(i).toFileString() + "\n";
		}
		return allItems;
	}

	public int alreadyExists(Item obj) {

		for (int i = 0; i < numItems; i++) {
			if (inventory.get(i).isEqual(obj)) {
				return i;
			}
		}
		return -1;
	}

	public boolean updateQuantity(Scanner input, boolean option) {

		Item product = new Item();
		product.inputCode(input);
		int tempCode = 0;
		int quantity = 0;

		tempCode = alreadyExists(product);

		if (tempCode == -1) {
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
			if (inventory.get(tempCode).updateItem(quantity)) {
				return true;
			} else {
				return false;
			}
		}

		if (!option) {
			if (inventory.get(tempCode).updateItem(-quantity)) {
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
			outFile.append(toFileString());
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
				if (inFile.hasNext()) {
					if (scanFile(inFile)) {
						System.out.println("File is successfully read.");
					} else {
						System.out.println("This line is unsuccessfully read.");
					}
				} else {
					System.out.println("File is empty, nothing to read.");
				}

			} else {
				throw new IOException();
			}
		} catch (IOException e) {
			System.out.println("Could not open file...." + fileName);
		}
	}

	public boolean scanFile(Scanner inFile) {

		int code = 0, amount = 0;
		float price = 0;
		int[] usedCode;
		String line = "", name = null, supplier;
		Item temp;

		while (inFile.hasNext()) {

			if (inFile.hasNext()) {
				line = inFile.next();
			}

			if (inFile.hasNextInt()) {
				code = inFile.nextInt();
			} else {
				System.out.print("Invalid item " + line + " in file\n");
				return false;
			}
			if (inFile.hasNext()) {
				name = inFile.next();
			} else {
				System.out.print("Invalid item " + line + " in file\n");
				return false;
			}
			if (inFile.hasNextInt()) {
				amount = inFile.nextInt();
			} else {
				System.out.print("Invalid item " + line + " in file\n");
				return false;
			}
			if (inFile.hasNextFloat()) {
				price = inFile.nextFloat();
			} else {
				System.out.print("Invalid item " + line + " in file\n");
				return false;
			}
			if ((line.startsWith("p") && inFile.hasNext()) || (line.startsWith("P") && inFile.hasNext())) {

				supplier = inFile.next();
				temp = new PurchasedItem(code, name, amount, price, supplier);

			} else {

				usedCode = new int[10];
				for (int i = 0; i < 10; i++) {

					if (inFile.hasNextInt()) {

						usedCode[i] = inFile.nextInt();

					} else {
						System.out.println("Invalid usedCode in file");
					}

					if (usedCode[i] == -1) {
						break;
					}
				}
				temp = new ManufacturedItem(code, name, amount, price, usedCode);
			}

			if (alreadyExists(temp) == -1) {
				inventory.add(newCodeIndex(temp), temp);
				numItems++;
			}
		}

		inFile.close();
		return true;
	}

}
