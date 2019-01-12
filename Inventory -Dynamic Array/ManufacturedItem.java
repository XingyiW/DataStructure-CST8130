import java.util.Scanner;

/*
 * CST8130_300
 * Xingyi Wu
 * 040887028
 * Assignment 1-Inventory System
 * 2018-02-09
 * 
 * This is ManufacturedItem class, sub class of Item class, to store used item.
 * 
 * 2 private members
 * itemsUsed integer array is to store used item codes
 * numItemsUsed is to count the number of used item in the array
 * 
 * Methods:
 * Constructor is to initiate the members.
 * boolean addItem(Scanner) invoke super class, and control a certain amount of codes to be scanned, return true if scan successfully otherwise return false.
 * String toString invoke super class, and return with itemsUsed code.
 * 
 */

public class ManufacturedItem extends Item {

	private int[] itemsUsed;
	private int numItemsUsed;

	public ManufacturedItem() {

		itemsUsed = new int[10];
		numItemsUsed = 0;
	}

	@Override
	public boolean addItem(Scanner input) {

		int inputmCode = 0;

		super.addItem(input);

		System.out.print("Enter up to 10 codes used in this item(-1 to quit): ");

		while (inputmCode != -1 && numItemsUsed < 10) {
			input.nextLine();
			while (true) {

				if (!input.hasNextInt()) {
					System.out.println("Invalid code...please enter integer : ");
					input.nextLine();
				} else {
					inputmCode = input.nextInt();

					if (inputmCode == -1 || inputmCode > 0) {
						if (inputmCode != -1) {
							itemsUsed[numItemsUsed] = inputmCode;
						}
						break;
					} else {
						System.out.println("Invalid code...please enter integer : ");
						input.nextLine();
					}
				}

			}

			numItemsUsed++;
		}

		return true;

	}

	@Override
	public String toString() {

		String itemscode = "";

		for (int i = 0; i < numItemsUsed; i++) {

			if (itemsUsed[i] != 0) {
				itemscode += Integer.toString(itemsUsed[i]) + ", ";
			}
		}

		String messageUsedCode = super.toString() + "\n" + "Code used: " + itemscode;

		return messageUsedCode;
	}

}
