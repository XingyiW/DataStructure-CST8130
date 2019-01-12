import java.util.Scanner;

/*
 * CST8130_300
 * Xingyi Wu
 * 040887028
 * Assignment 1-Inventory System
 * 2018-02-09
 * 
 * This is AssignMain class, to run the menu.
 * 
 * Methods:
 * main is to used to simulate a polymorphic inventory system.
 * 
 */

public class Assign1Main {

	public static void main(String[] args) {
		// write the code here to implement the menu as specified in Lab 1
		Scanner input = new Scanner(System.in);
		Inventory storage = new Inventory();

		int option = 0;
		while (option != 5) {
			System.out.printf(
					"%n1 to add an item to inventory,%n2 to display current inventory,%n3 buy some of an item,%n4 sell some of an item, %n5 to quit,%n");

			while (true) {
				if (!input.hasNextInt()) {
					System.out.print("Entry must be an integer: ");
					input.nextLine();
				} else {
					option = input.nextInt();

					if (option > 0 && option < 6) {

						break;

					} else {

						System.out.println("Invalid entry, valid number is 1-5");
						System.out.println("Reenter valid value: ");
						input.nextLine();

					}

				}
			}

			input.nextLine();
			if (option == 1) {
				storage.addItem(input);
			}
			if (option == 2) {
				System.out.print("Inventory:\n" + storage.toString());
			}
			if (option == 3) {
				if (!storage.updateQuantity(input, true)) {
					System.out.println("Error...could not buy item");
				}

			}
			if (option == 4) {

				if (!storage.updateQuantity(input, false)) {
					System.out.println("Error...could not sell item");
				}

			}
			if (option == 5) {

			}

		}
	}

}
