import java.util.Scanner;

/******************************************************************************************************
 * CST8130_300 Xingyi Wu 040887028 Assignment 3 - BlockChain 2018-03-30
 * 
 * Reference: LINDA CRANE, 18W - Lab 4-5 Block Chain Solution, Lab4Main.java
 * 
 * Purpose: this class to drive the menu for Assignment 3 - BlockChain
 * 
 *****************************************************************************************************/
public class Assign3Main {

	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);
		College myBlockChain = new College("Algonquin");
		String menuChoice = "a";

		while (menuChoice.charAt(0) != '6') {
			System.out.println("\nEnter 1 to display the college course: ");
			System.out.println("2 to add a new course: ");
			System.out.println("3 to add a a block: ");
			System.out.println("4 to verify chains: ");
			System.out.println("5 to fix a chain:");
			System.out.println("6 to quit: ");
			menuChoice = keyboard.next();

			switch (menuChoice.charAt(0)) {
			case '1':
				myBlockChain.display();
				break;
			case '2':
				myBlockChain.addCourse(keyboard);
				break;
			case '3':
				myBlockChain.addBlock(keyboard);
				break;
			case '4':
				myBlockChain.verifyChains();
				break;
			case '5':
				myBlockChain.fixChains(keyboard);
				break;
			case '6':
				System.out.println("Goodbye");
				break;
			default:
				System.out.println("Invalid choice...");
			}
		}

	}

}
