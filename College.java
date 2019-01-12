import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/*************************************************************************************
 * CST8130_300 Xingyi Wu 040887028 Assignment 3 - BlockChain 2018-03-30
 * 
 * Purpose: this class holds the data structure for the college Data member:
 * -college: ArrayList<> of BlockChain -collegeName: String -course: Iterator of
 * BlockChain Method: -display(): void, to display all data in the arrayList
 * -addCourse(Scanner): void, to add new blockChain to arrayList with no
 * duplicate. -selectCourse(Scanner): int, to check valid input and return the
 * integer -addBlock(Scanner): void, to add block objects to the element of
 * arrayList -verifyChain(): void, to check if the BlockChain is broken
 * -fixChains(Scanner):void, to fix the broken blockChains
 * 
 * 
 *************************************************************************************/
public class College {

	private ArrayList<BlockChain> college;
	private String collegeName;
	private Iterator<BlockChain> course;

	public College(String collegeName) {
		this.collegeName = collegeName;
		college = new ArrayList<BlockChain>();
	}

	public void display() {

		System.out.println("For college: " + collegeName);

		for (BlockChain blocks : college) {
			blocks.printBlockChain();
		}
	}

	public void addCourse(Scanner keyboard) {

		String courseName;

		System.out.println("Enter name of course to add: ");
		courseName = keyboard.next();
		if (!college.isEmpty()) {

			for (int i = 0; i < college.size(); i++) {

				if (courseName.equals(college.get(i).toString())) {
					while (courseName.equals(college.get(i).toString())) {
						System.out.println("This course already exists.Please re-enter: ");
						courseName = keyboard.next();
					}
				}
			}

			college.add(new BlockChain(courseName));
		} else {
			college.add(new BlockChain(courseName));
		}

	}

	public int selectCourse(Scanner keyboard) {
		int option = 0;
		while (true) {
			if (!keyboard.hasNextInt()) {
				System.out.println("Please enter an integer(>=0): ");
				keyboard.nextLine();
			} else {
				option = keyboard.nextInt();
				if (option >= college.size() || option < 0) {
					System.out.println("Invalid entry, please re-enter: ");
					keyboard.nextLine();
				} else {
					break;
				}
			}
		}
		return option;

	}

	public void addBlock(Scanner keyboard) {

		course = college.iterator();

		System.out.println("Enter which course to add: ");

		for (int i = 0; i < college.size(); i++) {

			System.out.println(i + " " + course.next());
		}

		college.get(selectCourse(keyboard)).addBlock(keyboard);

	}// end of addBlock

	public void verifyChains() {

		if (!college.isEmpty()) {

			for (int i = 0; i < college.size(); i++) {

				if (college.get(i).verifyChain()) {
					System.out.println("Chain for " + college.get(i) + " is verified");
				} else {
					System.out.println("Chain for " + college.get(i) + " is not verified");
				}

			}
		} else {
			System.out.println("There is no chain to verify.");
		}

	}// end of verifyChains

	public void fixChains(Scanner keyboard) {

		course = college.iterator();

		if (!college.isEmpty()) {

			System.out.println("Enter which course to fix: ");
			for (int i = 0; i < college.size(); i++) {
				System.out.println(i + " " + course.next());
			}

			college.get(selectCourse(keyboard)).removeBadBlocks();
			System.out.println("Chain is fixed.");
		} else {
			System.out.println("There is no chain to fix.");
		}

	}// end of fixChains

}// end of calss
