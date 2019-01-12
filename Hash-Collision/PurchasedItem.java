import java.util.Scanner;

/************************************************************************************************
 * 
 * Xingyi Wu CST8130_300 040887028 Assignment 4-Inventory System_Hash Algorithm
 * and Collision 2018-04-20 Winter 2018 Purpose: This class is a subclass of the
 * Item class Data members: supplierName: String Methods:
 * addItem(Scanner):boolean - fills valid values for the data members in this
 * class from the Scanner object toString(): String - sends contents of data
 * members to a String toFileString(): String - sends contents of data members
 * to a String when writing to a file
 *
 ***********************************************************************************************/
public class PurchasedItem extends Item {
	private String supplierName = new String();

	public boolean addItemFromFile(Scanner inFile) {
		boolean isOk = super.addItemFromFile(inFile);

		if (isOk) {
			supplierName = inFile.next();
		}
		return isOk;
	}

	@Override
	public boolean addItem(Scanner input) {
		super.addItem(input);
		input.nextLine();
		System.out.print("Enter the name for the supplier: ");
		supplierName = input.nextLine();
		return true;
	}

	@Override
	public String toString() {

		return super.toString() + " Supplier: " + supplierName;

	}

	@Override
	public String toFileString() {

		return "p " + super.toFileString() + " " + supplierName;

	}

}
