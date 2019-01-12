import java.util.Scanner;

/*
 * CST8130_300
 * Xingyi Wu
 * 040887028
 * Assignment 1-Inventory System
 * 2018-02-09
 * 
 * This is PurchasedItem class, sub class of Item class, to give the supplier name.
 * 
 * 1 private members
 * String supplierName is the name of purchased item supplier.
 * 
 * Methods:
 * Constructor is to initiate the members.
 * boolean addItem(Scanner) invoke super class, and read supplier name.
 * String toString invoke super class, and return with supplier name.
 * 
 */

public class PurchasedItem extends Item {

	private String supplierName;

	public PurchasedItem() {
		supplierName = "";
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
}
