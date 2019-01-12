import java.util.Scanner;

/************************************************************************************************
 * 
 * Xingyi Wu CST8130_300 040887028 Assignment 2-Inventory System 2018-03-09
 * Winter 2018 Purpose: This class is a subclass of the Item class Data members:
 * numItems: int itemsUsed: int[] - to hold itemCode values used Methods:
 * addItem(Scanner):boolean - fills valid values for the data members in this
 * class from the Scanner object toString(): String - sends contents of data
 * members to a String toFileString(): String - sends contents of data members
 * to a String when writing to a file
 *
 ***********************************************************************************************/

public class ManufacturedItem extends Item {

	private int[] itemsUsed = new int[10];
	private int numItemsUsed;

	public ManufacturedItem() {

		numItemsUsed = 0;
	}

	public ManufacturedItem(int code, String name, int amount, float price, int[] usedCode) {
		super(code, name, amount, price);

		for (int i = 0; i < usedCode.length; i++) {
			if (usedCode[i] == -1) {
				break;
			} else {
				itemsUsed[i] = usedCode[i];
				numItemsUsed++;
			}
		}
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
						} else {
							break;
						}
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

		String messageUsedCode = super.toString() + " " + "Code used: " + itemscode;

		return messageUsedCode;
	}

	@Override
	public String toFileString() {

		String itemscode = "";

		for (int i = 0; i < numItemsUsed; i++) {

			if (itemsUsed[i] != 0) {
				itemscode += Integer.toString(itemsUsed[i]) + " ";
			}
		}

		String mString = "m " + super.toFileString() + " " + itemscode + " -1";
		return mString;
	}

}
