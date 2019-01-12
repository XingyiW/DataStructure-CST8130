import java.util.Scanner;

/*
 * CST8130_300
 * Xingyi Wu
 * 040887028
 * Assignment 1-Inventory System
 * 2018-02-09
 * 
 * This is Item class, super class, to be used as polymorphism, to add and update items.
 * 
 * 4 private members:
 * itemCode integer is the number of each item code
 * itemName String is the name of each item
 * itemQuantityInStock integer is the amount of the quantity of stored items
 * itemPrice float is the price of each item
 * 
 * Methods:
 * boolean addItem (Scanner) is to read the data members check if it is valid and corresponding to the sub classes.
 * String toString is to return the individual item information corresponding to the sub classes.
 * boolean updateItem(int amount) is to update the quantity field by amount
 * boolean isEqual(Item) is to check if one itemCode of the object is the same code as item code. 
 * boolean inputCode(Scanner) is to check if Scanner can read successfully.
 * 
 */

public class Item {

	private int itemCode;
	private String itemName;
	private int itemQuantityInStock;
	private float itemPrice;

	public Item() {
		itemCode = 0;
		itemName = "";
		itemQuantityInStock = 0;
		itemPrice = 0;
	}

	public boolean addItem(Scanner input) {

		while (true) {
			System.out.print("Enter the code for the item: ");
			if (!input.hasNextInt()) {
				System.out.print("Invalid code...please enter integer greater than 0: ");
				input.nextLine();
			} else {
				itemCode = input.nextInt();
				if (itemCode > 0) {
					break;
				} else {
					System.out.print("Invalid code...please enter integer greater than 0: ");
					input.nextLine();
				}
			}
		}
		input.nextLine();
		System.out.print("Enter the name for the item: ");
		itemName = input.next();

		while (true) {
			System.out.print("Enter the quantity for the item: ");
			if (!input.hasNextInt()) {
				System.out.print("Invalid quantity...please enter integer greater than 0: ");
				input.nextLine();
			} else {
				itemQuantityInStock = input.nextInt();
				if (itemQuantityInStock > 0) {
					break;
				} else {
					System.out.print("Invalid quantity...please enter integer greater than 0: ");
					input.nextLine();
				}
			}
		}

		input.nextLine();

		while (true) {
			System.out.print("Enter the price of the item: ");
			if (!input.hasNextFloat()) {
				System.out.print("Invalid price...please enter float greater than 0: ");
				input.nextLine();
			} else {
				itemPrice = input.nextFloat();
				if (itemPrice > 0) {
					break;
				} else {
					System.out.print("Invalid price...please enter float greater than 0: ");
					input.nextLine();
				}
			}

		}

		return true;

	}

	public String toString() {

		String message = "Item: " + itemCode + " " + itemName + " " + itemQuantityInStock + " " + "price: $"
				+ itemPrice;

		return message;
	}

	public boolean updateItem(int amount) {

		if (amount < 0) {

			if (Math.abs(amount) <= itemQuantityInStock) {
				itemQuantityInStock += amount;

				return true;
			} else {
				return false;
			}

		} else {
			itemQuantityInStock += amount;
			return true;
		}

	}

	public boolean isEqual(Item ob) {

		if (this.itemCode == ob.itemCode) {

			return true;

		} else {

			return false;
		}
	}

	public boolean inputCode(Scanner input) {

		while (true) {
			System.out.print("Enter valid item code: ");
			if (!input.hasNextInt()) {
				System.out.print("Invalid code...please enter integer greater than 0: ");
				input.nextLine();
			} else {
				itemCode = input.nextInt();
				if (itemCode > 0) {
					break;
				} else {
					System.out.print("Invalid code...please enter integer greater than 0: ");
					input.nextLine();
				}
			}

		}

		return true;
	}

}
