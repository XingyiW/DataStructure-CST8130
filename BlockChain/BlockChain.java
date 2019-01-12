import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/*************************************************************************************
 * CST8130_300 Xingyi Wu 040887028 Assignment 3 - BlockChain 2018-03-30
 * 
 * Reference: LINDA CRANE, 18W - Lab 4-5 Block Chain Solution, BlockChain.java
 * 
 * Purpose: this class holds the data structure for the BlockChain Data member:
 * -blockChain: LinkedList of Blocks -courseName: String -oneBlock: Iterator of
 * Block Method: -toString(): String to return the string of BlockChains objects
 * -printBlockChain(): void, to print the data of each block in BlockChains
 * -addBlock(Scanner): void, to add new Block into good BlockChains.
 * -addBadBlock(Scanner):void, to add new Block into a broken BlockChains
 * -verifyChain(): void, iterator to check if the Blocks are connected via
 * hashCode -removeBadBlocks():void, to remove the broken blocks blockChains
 * 
 *************************************************************************************/

public class BlockChain {

	private LinkedList<Block> blockChain; // start the chain with the Genesis block
	private String courseName = "NotEntered";
	private Iterator<Block> oneBlock;

	public BlockChain(String courseName) {
		this.courseName = new String(courseName);
		blockChain = new LinkedList<Block>();
		blockChain.add(new Block());
	}

	public String toString() {
		return courseName;
	}

	public void printBlockChain() {
		oneBlock = blockChain.iterator();
		System.out.print("\nFor course: " + courseName + "\n[");

		for (int i = 0; i < blockChain.size(); i++) {
			System.out.print("\n" + oneBlock.next());
		}

		System.out.print("]\n");

	}

	public void addBlock(Scanner keyboard) {
		Block newOne = new Block();

		System.out.println("Add good block or bad? (g for good, anything else for bad): ");
		char good = keyboard.next().charAt(0);

		if (good == 'g' || good == 'G') {
			if (newOne.addInfoToBlock(keyboard, blockChain.getLast().getCurrentHash())) {
				blockChain.add(newOne);
			}
		} else {
			addBadBlock(keyboard);
		}

	}

	public void addBadBlock(Scanner keyboard) {
		Random random = new Random();
		Block newOne = new Block();
		if (newOne.addInfoToBlock(keyboard, random.nextFloat())) {

			blockChain.add(newOne);
		}

	}

	public boolean verifyChain() {
		oneBlock = blockChain.iterator();
		Block previousBlock = oneBlock.next();
		Block currentBlock = null;

		while (oneBlock.hasNext()) {
			currentBlock = oneBlock.next();

			if (currentBlock.isEqual(previousBlock)) {
				previousBlock = currentBlock;
			} else {
				return false;
			}

		}
		return true;
	}

	public void removeBadBlocks() {
		oneBlock = blockChain.iterator();
		Block previousBlock = oneBlock.next();
		Block currentBlock = null;

		while (oneBlock.hasNext()) {
			currentBlock = oneBlock.next();

			if (!currentBlock.isEqual(previousBlock)) {

				oneBlock.remove();

				if (oneBlock.hasNext()) {
					currentBlock = oneBlock.next();
					currentBlock.updatePreviousHash(previousBlock.getCurrentHash());
					previousBlock = currentBlock;
				}

			} else {
				previousBlock = currentBlock;

			}

		}

	}

}
