import java.util.Scanner;

/************************************************************************************************
 * 
 * Xingyi Wu CST8130_300 040887028 Assignment 2-Inventory System_Hash Algorithm
 * and Collision 2018-04-20 Winter 2018 Purpose: This class is the superclass
 * for the inventory array elements Data members: itemCode: int - holds a
 * numeric code for the item itemName: String itemQuantityInStock: int -
 * itemPrice: float Methods: addItem(Scanner):boolean - fills valid values for
 * the data members in this class from the Scanner object toString(): String -
 * sends contents of data members to a String toFileString: String - write the
 * valid values from the data members in the class from FileWriter object to a
 * String update(int):boolean - updates the quantityInStock by the amount
 * (quantity cannot be <0 so does not do update if this would result)
 * isEqual(Item): boolean - returns true/false based on itemCode in parameter
 * object and object being acted on being equal inputCode (Scanner): boolean -
 * fills object itemCode from Scanner hashIndex(): int - return the result of
 * hash algorithm
 *
 ***********************************************************************************************/

public class Item {
	private int itemCode;
	private String itemName = new String();
	private int itemQuantityInStock;
	private float itemPrice;

	public Item() {

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

	public boolean addItemFromFile(Scanner inFile) {

		if (inFile.hasNextInt()) {
			itemCode = inFile.nextInt();
		} else {
			System.out.print("Invalid item in file\n");
			return false;
		}
		if (inFile.hasNext()) {
			itemName = inFile.next();
		} else {
			System.out.print("Invalid item in file\n");
			return false;
		}
		if (inFile.hasNextInt()) {
			itemQuantityInStock = inFile.nextInt();
		} else {
			System.out.print("Invalid item in file\n");
			return false;
		}
		if (inFile.hasNextFloat()) {
			itemPrice = inFile.nextFloat();
		} else {
			System.out.print("Invalid item in file\n");
			return false;
		}

		return true;

	}

	public String toString() {

		return "Item: " + itemCode + " " + itemName + " " + itemQuantityInStock + " " + "price: $" + itemPrice;

	}

	public String toFileString() {

		return itemCode + " " + itemName + " " + itemQuantityInStock + " " + itemPrice;

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
		return (itemCode == ob.itemCode);
	}

	public boolean inputCode(Scanner input) {

		while (true) {
			System.out.print("Enter an item code: ");
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

	public int hashIndex() {
		return itemCode % 100;
	}

}
