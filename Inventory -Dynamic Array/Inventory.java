import java.util.Scanner;

/*
 * CST8130_300
 * Xingyi Wu
 * 040887028
 * Assignment 1-Inventory System
 * 2018-02-09
 * 
 * This is Inventory class, to store items and update quantity.
 * 
 * 2 private members
 * inventory array: the reference to the array of item objects
 * numItems integer: to count the items stored in the array
 * 
 * Methods:
 * Constructor is to initiate the members.
 * boolean addItem(Scanner) is to define if the item is purchased or manufactured.
 * String toString is to return all inventory value.
 * alreadyExist(Item) integer is to check if item is already in the inventory, if true, return the index of array or return -1.
 * boolean updateQuantity(Scanner, boolean), Scanner is to read the itemCode to update quantity, boolean is to define if it is to buy or sell.
 */

public class Inventory {

	private Item[] inventory;
	private int numItems;

	public Inventory() {
		inventory = new Item[1000];
		numItems = 0;
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
			inventory[numItems] = xitem;
			numItems++;
			return true;
		} else {
			return false;
		}

	}

	public String toString() {

		String allItems = "";

		for (int i = 0; i < numItems; i++) {
			allItems += inventory[i].toString() + "\n";
		}
		return allItems;
	}

	public int alreadyExists(Item obj) {

		for (int i = 0; i < numItems; i++) {
			if (inventory[i].isEqual(obj)) {
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
			if (inventory[tempCode].updateItem(quantity)) {
				return true;
			}

			else {
				return false;
			}
		}

		if (!option) {
			if (inventory[tempCode].updateItem(-quantity)) {
				return true;
			}

			else {
				return false;
			}

		}

		return true;
	}
}
