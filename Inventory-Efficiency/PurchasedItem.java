import java.util.Scanner;

/************************************************************************************************
 * 
 * Xingyi Wu CST8130_300 040887028 Assignment 2-Inventory System 2018-03-09
 * Winter 2018 Purpose: This class is a subclass of the Item class Data members:
 * supplierName: String Methods: addItem(Scanner):boolean - fills valid values
 * for the data members in this class from the Scanner object toString(): String
 * - sends contents of data members to a String toFileString(): String - sends
 * contents of data members to a String when writing to a file
 *
 ***********************************************************************************************/
public class PurchasedItem extends Item {
	private String supplierName;

	public PurchasedItem() {
		supplierName = "";
	}

	public PurchasedItem(int code, String name, int amount, float price, String supplierName) {

		super(code, name, amount, price);
		this.supplierName = supplierName;

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

		String messageSupplier = super.toString() + " Supplier: " + supplierName;

		return messageSupplier;
	}

	@Override
	public String toFileString() {

		String pString = "p " + super.toFileString() + " " + supplierName;
		return pString;
	}

}
