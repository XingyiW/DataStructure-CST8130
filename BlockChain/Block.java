import java.util.Scanner;

/******************************************************************************************************
 * CST8130_300 Xingyi Wu 040887028 Assignment 3 - BlockChain 2018-03-30
 * 
 * Reference: LINDA CRANE, 18W - Lab 4-5 Block Chain Solution, Block.java
 * 
 * Purpose: this class holds the data structure for the Block Data member:
 * -date: int -studentName: int -grade: int Block -previousHash: float
 * -currentHash: float Method: -calculateHash: float, to return the hashValue -
 * toString(): String to return the string of Block objects -getCurrentHash():
 * float, return currentHash -isEqual(Block):boolean, to check if the previous
 * block's hashValue is equal to current block's hashValue
 * -updatePreviousHash(float):void, set the current block's previouse hash is
 * equal to the currnt hash of the one before when remove blocks
 * -blockInfo(Scanner): int to check valid input and return the integer
 * -addInfoToBlock(Scanner, float): boolean,to add the valid input and call
 * calculateHash for previousHash
 * 
 *****************************************************************************************************/
public class Block {

	private int date; // part of data - in month day year format (eg) 2152018
	private int studentNumber; // part of data
	private int grade; // part of data
	private float previousHash;
	private float currentHash;

	public Block() {
		// create the Genesis block
		date = 2152018;
		studentNumber = 0;
		grade = 100;
		previousHash = 0f;
		currentHash = calculateHash();

	}

	public float calculateHash() {
		return (date + studentNumber + grade) / 88;
	}

	public String toString() {
		return "" + studentNumber + " " + grade + " " + date + " current: " + currentHash + " previous: "
				+ previousHash;
	}

	public float getCurrentHash() {
		return currentHash;
	}

	public boolean isEqual(Block temp) {
		return (previousHash == temp.currentHash);
	}

	public void updatePreviousHash(float currHash) {
		previousHash = currHash;
	}

	public int blockInfo(Scanner keyboard) {
		int option = 0;
		while (true) {
			if (!keyboard.hasNextInt()) {
				System.out.println("Please enter an integer: ");
				keyboard.nextLine();
			} else {
				option = keyboard.nextInt();
				if (option < 0) {
					System.out.println("Invalid entry, please re-enter an integer(>0): ");
					keyboard.nextLine();
				} else {
					break;
				}
			}
		}
		return option;
	}

	public boolean addInfoToBlock(Scanner keyboard, float previousHash) {
		System.out.print("Enter date: ");
		date = blockInfo(keyboard);

		System.out.print("Enter student number: ");
		studentNumber = blockInfo(keyboard);

		System.out.println("Enter grade: ");
		grade = blockInfo(keyboard);

		currentHash = calculateHash();
		this.previousHash = previousHash;
		return true;
	}

}
