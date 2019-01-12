import java.util.Scanner;

/************************************************************************************************
 * 
 * Xingyi Wu CST8130_300 040887028 Assignment 2-Inventory System 2018-03-09
 * Winter 2018 Purpose: This class is the superclass for the inventory array
 * elements Data members: itemCode: int - holds a numeric code for the item
 * itemName: String itemQuantityInStock: int - itemPrice: float Methods:
 * addItem(Scanner):boolean - fills valid values for the data members in this
 * class from the Scanner object toString(): String - sends contents of data
 * members to a String toFileString: String - write the valid values from the
 * data members in the class from FileWriter object to a String
 * update(int):boolean - updates the quantityInStock by the amount (quantity
 * cannot be <0 so does not do update if this would result) isEqual(Item):
 * boolean - returns true/false based on itemCode in parameter object and object
 * being acted on being equal inputCode (Scanner): boolean - fills object
 * itemCode from Scanner
 *
 ***********************************************************************************************/

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

	public Item(int code, String name, int amount, float price) {
		itemCode = code;
		itemName = name;
		itemQuantityInStock = amount;
		itemPrice = price;
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

	public boolean orderCode(Item obj) {

		if (this.itemCode < obj.itemCode) {
			return true;
		} else
			return false;
	}

	public String toString() {

		String message = "Item: " + itemCode + " " + itemName + " " + itemQuantityInStock + " " + "price: $"
				+ itemPrice;

		return message;
	}

	public String toFileString() {

		String message = itemCode + " " + itemName + " " + itemQuantityInStock + " " + itemPrice;
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
