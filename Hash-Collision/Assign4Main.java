import java.io.FileWriter;
import java.util.Scanner;

/************************************************************************************************
 * 
 * Xingyi Wu CST8130_300 040887028 Assignment 2-Inventory System_Hash Algorithm
 * and Collision 2018-04-20 Winter 2018 Purpose: This class drives the menu for
 * Assignment 4 - Inventory system
 *
 ***********************************************************************************************/
public class Assign4Main {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		Inventory storage = new Inventory(1000);

		int option = 0;
		while (option != 9) {
			System.out.printf(
					"%n1 to add an item to inventory,%n2 to display current inventory,%n3 buy some of an item,%n4 sell some of an item, %n5 to create a file,%n6 to read a file,%n7 to search, %n8 to quit %n");

			while (true) {
				if (!input.hasNextInt()) {
					System.out.print("Entry must be an integer: ");
					input.nextLine();
				} else {
					option = input.nextInt();
					if (option > 0 && option < 9) {
						break;
					} else {
						System.out.println("Invalid entry, valid number is 1-9");
						System.out.println("Reenter valid value: ");
						input.nextLine();
					}
				}
			}

			input.nextLine();
			if (option == 1) {
				storage.addItem(input);
			} else if (option == 2) {
				System.out.print("Inventory:\n" + storage.toString());
			} else if (option == 3) {
				if (!storage.updateQuantity(input, true)) {
					System.out.println("Error...could not buy item");
				}
			} else if (option == 4) {
				if (!storage.updateQuantity(input, false)) {
					System.out.println("Error...could not sell item");
				}
			} else if (option == 5) {
				FileWriter outFile = null;
				storage.nameFile(outFile);
			} else if (option == 6) {
				storage.readFile(input);
			} else if (option == 7) {
				storage.search();
			} else if (option == 8) {

			}

		} // end of while
	} // end of main

} // end of class
