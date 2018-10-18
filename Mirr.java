import java.util.ArrayList;
import java.util.Scanner;

public class Mirr {
	ArrayList<Node> map, totalList, xList, oList, open;
	String val, enemyVal;
	int numSearched;
	public Mirr(String val, String enemyVal) {
		this.val = val;
		this.enemyVal = enemyVal;
	}
	
	public void updateArrays(ArrayList<Node> map, ArrayList<Node> totalUsed, ArrayList<Node> xArr, ArrayList<Node> oArr) {
		this.map = map;
		this.totalList = totalUsed;
		this.xList = xArr;
		this.oList = oArr;
		
//		for(int i = 0;)
	}
	
	public ArrayList<Node> findPossible(ArrayList<Node> newOpen, ArrayList<Node> totalList, ArrayList<Node> map) {
		newOpen = new ArrayList<Node>();
		int upMax, downMax, leftMax, rightMax, row, col;
//		for(int i = 0; i < totalList.size(); i++) {
//			totalList.get(i).printNode();
//		}
		for(int i = 0; i < totalList.size(); i++) {
			upMax = 2;
			downMax = 2;
			leftMax = 2;
			rightMax = 2;
			
			//Set limits for function to check for potential open spaces. Goal: Only up to 2 adjacent spaces from currently entered indices.
			if(totalList.get(i).getRow() == 1)
				upMax = 0;
			if(totalList.get(i).getRow() == 2)
				upMax = 1;
			if(totalList.get(i).getRow() == 8)
				downMax = 0;
			if(totalList.get(i).getRow() == 7)
				downMax = 1;
			if(totalList.get(i).getCol() == 1)
				leftMax = 0;
			if(totalList.get(i).getCol() == 2)
				leftMax = 1;
			if(totalList.get(i).getCol() == 8)
				rightMax = 0;
			if(totalList.get(i).getCol() == 7)
				rightMax = 1;
			
			for(int j = 1; j <= leftMax; j++) {						//Checking left values
//				System.out.println("In left forloop");
				row = totalList.get(i).getRow();
				col = totalList.get(i).getCol();
				
				if(j == 2) {										//Checking space left 2
					col -= 2;
					if(!(contains(newOpen, row, col))
					   && !(contains(totalList, row, col))) {
						newOpen.add(findNode(map, row, col));
						continue;
					}
				}
				else {
					col -= 1;
					if(upMax > 0) {									//Checking space above and left 1
						row -= 1;
						if(!(contains(newOpen, row, col))
						   && !(contains(totalList, row, col))) {
							newOpen.add(findNode(map, row, col));
						}
						row += 1;
					}
					
					if(!(contains(newOpen, row, col))
					   && !(contains(totalList, row, col))) {		//Checking space left 1
						newOpen.add(findNode(map, row, col));
					}
					
					if(downMax > 0) {
						row += 1;
						if(!(contains(newOpen, row, col))
						   && !(contains(totalList, row, col))) {	//Checking space down and left 1
							newOpen.add(findNode(map, row, col));
						}
						row -= 1;
					}		
				}
			}
			
			for(int j = 1; j <= rightMax; j++) {						//Checking left values
				row = totalList.get(i).getRow();
				col = totalList.get(i).getCol();
				
				if(j == 2) {										//Checking space left 2
					col += 2;
					if(!(contains(newOpen, row, col))
					   && !(contains(totalList, row, col))) {
						newOpen.add(findNode(map, row, col));
						continue;
					}
				}
				else {
					col += 1;
					if(upMax > 0) {									//Checking space above and left 1
						row -= 1;
						if(!(contains(newOpen, row, col))
						   && !(contains(totalList, row, col))) {
							newOpen.add(findNode(map, row, col));
						}
						row += 1;
					}
					
					if(!(contains(newOpen, row, col))
					   && !(contains(totalList, row, col))) {		//Checking space left 1
						newOpen.add(findNode(map, row, col));
					}
					
					if(downMax > 0) {
						row += 1;
						if(!(contains(newOpen, row, col))
						   && !(contains(totalList, row, col))) {	//Checking space down and left 1
							newOpen.add(findNode(map, row, col));
						}
						row -= 1;
					}		
				}
			}
			
			for(int j = 1; j <= upMax; j++) {
				row = totalList.get(i).getRow();
				col = totalList.get(i).getCol();
				
				if(j == 2) {
					row -= 2;
					if(!(contains(newOpen, row, col))
					   && !(contains(totalList, row, col))) {	//Checking space down and left 1
						newOpen.add(findNode(map, row, col));
						continue;
					}
					row += 2;
				}
				if(j == 1) {
					row -= 1;
					if(!(contains(newOpen, row, col))
					   && !(contains(totalList, row, col))) {	//Checking space down and left 1
						newOpen.add(findNode(map, row, col));
						continue;
					}
					row += 1;
				}
			}
			
			for(int j = 1; j <= downMax; j++) {
				row = totalList.get(i).getRow();
				col = totalList.get(i).getCol();
				
				if(j == 2) {
					row += 2;
					if(!(contains(newOpen, row, col))
					   && !(contains(totalList, row, col))) {	//Checking space down and left 1
						newOpen.add(findNode(map, row, col));
						continue;
					}
					row -= 2;
				}
				if(j == 1) {
					row += 1;
					if(!(contains(newOpen, row, col))
					   && !(contains(totalList, row, col))) {	//Checking space down and left 1
						newOpen.add(findNode(map, row, col));
						continue;
					}
					row -= 1;
				}
			}
		}
//		printArrList(open);
		return newOpen;
	}

	public ArrayList<Node> findBestChoice(ArrayList<Node> open, String myValue, String enemyValue) {
		int upMax, downMax, leftMax, rightMax, row, col, totalScore, numDash, numEnemy, numMine;
		String compareVal;
		
		for(int i = 0; i < open.size(); i++) {
			totalScore = 0;
			row = open.get(i).getRow();
			col = open.get(i).getCol();
			
			upMax = setUpMax(row);
			downMax = setDownMax(row);
			leftMax = setLeftMax(col);
			rightMax = setRightMax(col);
			
			for(int j = col - leftMax; j <= col + rightMax - 3; j++) {		//Iterates from the far left, in sequences of 4, to the far right
				//Used to count types of patterns found
				numDash = 0;
				numEnemy = 0;
				numMine = 0;
				
				for(int k = 0; k < 4; k++) {
//					System.out.println("Open, row: " + row + "j + k: " + j + ", " + k);
					compareVal = findNode(map, row, j + k).getVal();
					if(compareVal == "-")
						numDash++;
					if(compareVal == myValue)
						numMine++;
					if(compareVal == enemyValue)
						numEnemy++;
				}
				
				totalScore += findValue(numDash, numMine, numEnemy);
				
			}
			
			for(int j = row - upMax; j <= row + downMax - 3; j++) {		//Iterates from the top, in sequences of 4, to the bot
				//Used to count types of patterns found
				numDash = 0;
				numEnemy = 0;
				numMine = 0;
				
				for(int k = 0; k < 4; k++) {
					compareVal = findNode(map, j + k, col).getVal();
					if(compareVal == "-")
						numDash++;
					if(compareVal == myValue)
						numMine++;
					if(compareVal == enemyValue)
						numEnemy++;
				}
				
				totalScore += findValue(numDash, numMine, numEnemy);
				
				
			}
//			System.out.println("Row: " + row + ", Col: " + col + ", Score: " + totalScore);
			open.get(i).setScore(totalScore);
			
		}
		
		
		
		open = sortArrayByValue(open);
//		System.out.println("Second arr List print");
//		printArrList(open);
		
//		System.out.println("Printing in find best: ");
//		printArrList(open);
		
		return open;
		
//		return totalValue
		
	}
	
	private ArrayList<Node> sortArrayByValue(ArrayList<Node> arr) {
		ArrayList<Node> holder = new ArrayList<Node>();
		int nodeIndex;
		int scoreHolder;
		for(int i = arr.size() - 1; i >= 0; i--) {
			scoreHolder = arr.get(i).getScore();
			nodeIndex = i;
			for(int j = arr.size() - 2; j >= 0; j--) {
				if(arr.get(j).getScore() > scoreHolder) {
					scoreHolder = arr.get(j).getScore();
					nodeIndex = j;
				}
			}
			holder.add(arr.get(nodeIndex));
			arr.remove(nodeIndex);
		}
		
//		for(int i = 0; i < holder.size(); i++) {
//			holder.get(i).printNode();
//		}
//		
//		System.out.println("In sortArray");
//		printArrList(holder);
		
		return holder;
		
	}
	
	public Node minMax(ArrayList<Node> open, String myValue, String enemyValue) {
		ArrayList<Node> copyOpen, copyMap, copyXList, copyOList, copyTotalList;
		Node returnNode = null, minNode;
		double timeStart = System.nanoTime();
		double timeElapsed = 0.0;
		double timeNow;
		int cullValue = 0, maxDepth = 7, curDepth = 1, scoreDif = -1;
		numSearched = 0;
		
		open = findBestChoice(open, myValue, enemyValue);
		cullValue = open.get(0).getScore();
		
//		System.out.println("Printing before: ");
//		printArrList(open);
		
		for(int i = open.size() - 1; i > 0; i--) {			//Culls values less than .7 of the best choice
			if(open.get(i).getScore() < cullValue * .7)
				open.remove(i);
		}
		
		returnNode = open.get(0);
//		System.out.println("Printing after: ");
//		printArrList(open);
		
		
		int i = 0;
		while(i < open.size() && timeElapsed < 10.0) {
			numSearched++;
			copyOpen = copyArrList(open);
			copyMap = copyArrList(map);
			copyXList = copyArrList(xList);
			copyOList = copyArrList(oList);
			copyTotalList = copyArrList(totalList);
			minNode = min(copyOpen, copyMap, copyXList, copyOList, copyTotalList, myValue, enemyValue, open.get(i), maxDepth, curDepth);
			
			if(scoreDif == -1)												//Initialize score difference
				scoreDif = returnNode.getScore() - minNode.getScore();
			else {
				if(open.get(i).getScore() - minNode.getScore() > scoreDif) { //Update if new pair grants a better score difference
					returnNode = open.get(i);
					scoreDif = returnNode.getScore() - minNode.getScore();
				}
			}
			
			i++;
			timeNow = System.nanoTime();
			timeElapsed += (timeNow - timeStart) / 1000000000; //Increment total time so far
			timeStart = timeNow;				//Reset timeStart
		}
		System.out.println("\nElapsed time: " + timeElapsed + " num searched: " + numSearched);
		
		
		return returnNode;
	}
	
	private Node min(ArrayList<Node> copyOpen, ArrayList<Node> copyMap, ArrayList<Node> copyXList, ArrayList<Node> copyOList, 
					 ArrayList<Node> copyTotalList, String enemyValue, String myValue, Node input, int maxDepth, int curDepth) {
		numSearched++;
//		System.out.println2("In min, depth: " + curDepth);
		curDepth++;
		Node returnNode = null, maxNode;
		ArrayList<Node> copyOpen2, copyMap2, copyXList2, copyOList2, copyTotalList2;
		int cullValue, scoreDif = -1;
		
		copyOpen = findPossible(copyOpen, copyTotalList, copyMap);
		
		copyOpen = findBestChoice(copyOpen, myValue, enemyValue);
		cullValue = copyOpen.get(0).getScore();
		
		for(int i = copyOpen.size() - 1; i > 0; i--) {			//Culls values less than .7 of the best choice
			if(copyOpen.get(i).getScore() < cullValue * .7)
				copyOpen.remove(i);
		}
		
		returnNode = copyOpen.get(0);
		
		for(int i = 0; i < copyOpen.size(); i++) {
			copyOpen2 = copyArrList(copyOpen);
			copyMap2 = copyArrList(copyMap);
			copyXList2 = copyArrList(copyXList);
			copyOList2 = copyArrList(copyOList);
			copyTotalList2 = copyArrList(copyTotalList);
			
			if(curDepth < maxDepth) {
				maxNode = max(copyOpen2, copyMap2, copyXList2, copyOList2, copyTotalList2, myValue, enemyValue, copyOpen.get(i), maxDepth, curDepth);
				if(scoreDif == -1)												//Initialize score difference
					scoreDif = returnNode.getScore() - maxNode.getScore();
				else {
					if(copyOpen.get(i).getScore() - maxNode.getScore() > scoreDif) { //Update if new pair grants a better score difference
						returnNode = copyOpen.get(i);
						scoreDif = returnNode.getScore() - maxNode.getScore();
					}
				}
			}
		}
				
		return returnNode;
	}
	
	private Node max(ArrayList<Node> copyOpen, ArrayList<Node> copyMap, ArrayList<Node> copyXList, ArrayList<Node> copyOList, 
					 ArrayList<Node> copyTotalList, String enemyValue, String myValue, Node input, int maxDepth, int curDepth) {
		numSearched++;
		curDepth++;
		Node returnNode = null, minNode;
		ArrayList<Node> copyOpen2, copyMap2, copyXList2, copyOList2, copyTotalList2;
		int cullValue, scoreDif = -1;
		
		copyOpen = findPossible(copyOpen, copyTotalList, copyMap);
		
		copyOpen = findBestChoice(copyOpen, myValue, enemyValue);
		cullValue = copyOpen.get(0).getScore();
		
		for(int i = copyOpen.size() - 1; i > 0; i--) {			//Culls values less than .7 of the best choice
			if(copyOpen.get(i).getScore() < cullValue * .7)
				copyOpen.remove(i);
		}
		
		returnNode = copyOpen.get(0);
		
		for(int i = 0; i < copyOpen.size(); i++) {
			copyOpen2 = copyArrList(copyOpen);
			copyMap2 = copyArrList(copyMap);
			copyXList2 = copyArrList(copyXList);
			copyOList2 = copyArrList(copyOList);
			copyTotalList2 = copyArrList(copyTotalList);
			
			if(curDepth < maxDepth) {
				minNode = min(copyOpen2, copyMap2, copyXList2, copyOList2, copyTotalList2, myValue, enemyValue, copyOpen.get(i), maxDepth, curDepth);
				if(scoreDif == -1)												//Initialize score difference
					scoreDif = returnNode.getScore() - minNode.getScore();
				else {
					if(copyOpen.get(i).getScore() - minNode.getScore() > scoreDif) { //Update if new pair grants a better score difference
						returnNode = copyOpen.get(i);
						scoreDif = returnNode.getScore() - minNode.getScore();
					}
				}
			}
		}
		
		return returnNode;
	}
	
	public static void editMap(String val, String autoInput, 
							   ArrayList<Node> mapCopy, ArrayList<Node> copyTotalList, ArrayList<Node> xListCopy, ArrayList<Node> oListCopy) {

		boolean exit = false;
		char inputChar;
		int inputCol, index, inputRow = -1;
		
		while(exit == false) {
			inputChar = autoInput.charAt(0);
			inputCol = Character.getNumericValue(autoInput.charAt(1));
			
			if(inputChar == 'a')
				inputRow = 1;
			else if(inputChar == 'b')
				inputRow = 2;
			else if(inputChar == 'c')
				inputRow = 3;
			else if(inputChar == 'd')
				inputRow = 4;
			else if(inputChar == 'e')
				inputRow = 5;
			else if(inputChar == 'f')
				inputRow = 6;
			else if(inputChar == 'g')
				inputRow = 7;
			else if(inputChar == 'h')
				inputRow = 8;
		
			index = (8 * (inputRow - 1)) + inputCol - 1;
			if(mapCopy.get(index).getVal() == "-") {
				mapCopy.get(index).setVal(val);
				copyTotalList.add(mapCopy.get(index));
				if(val == "O") {
					oListCopy.add(mapCopy.get(index));
				}
				else {
					xListCopy.add(mapCopy.get(index));
				}
			}
			exit = true;
		}
	}
	
	private int findValue(int numDash, int numMine, int numEnemy) { //THIS DETERMINES POINT ASSIGNMENTS AND PRIORITIES OF THE EQUATION
		int total = 0;
		if(numMine == 3)
			return 99999;	//This means a winning position is found, end everything and win the game.
		if(numMine == 2 && numDash == 2)		//2 mine, 2 dash
			total += 128;
		if(numMine == 2 && numDash == 1)		//2 mine, 1 enemy, 1 dash
			total += 8;
		if(numMine == 1)						//1 mine alone;
			total += 4;
		
		if(numEnemy == 3)
			return 8888;
		if(numEnemy == 2 && numDash == 2)
			total += 64;
		if(numEnemy == 2 && numDash == 1)
			total += 32;
		if(numEnemy == 1)
			total += 4;
		
		total += numDash;
		
		return total;
	}
	
	private int setUpMax(int row) {
		if(row == 1)
			return 0;
		if(row == 2)
			return 1;
		if(row == 3)
			return 2;
		return 3;
	}
	
	private int setDownMax(int row) {
		if(row == 8)
			return 0;
		if(row == 7)
			return 1;
		if(row == 6)
			return 2;
		return 3;
	}
	
	private int setLeftMax(int col) {
		if(col == 1)
			return 0;
		if(col == 2)
			return 1;
		if(col == 3)
			return 2;
		return 3;
	}
	
	private int setRightMax(int col) {
		if(col == 8)
			return 0;
		if(col == 7)
			return 1;
		if(col == 6)
			return 2;
		return 3;
	}
	
	private static boolean contains(ArrayList<Node> arr, int row, int col) {
		for(int i = 0; i < arr.size(); i++) {
			if(arr.get(i).getRow() == row && arr.get(i).getCol() == col)
				return true;
		}
		return false;
	}
	
	private static Node findNode(ArrayList<Node> arr, int row, int col) {
//		printArrList(arr);
		for(int i = 0; i < arr.size(); i++) {
//			System.out.println("row: " + arr.get(i).getRow() + "   col: " + arr.get(i).getCol());
			if(arr.get(i).getRow() == row && arr.get(i).getCol() == col)
				return arr.get(i);
		}
		//This shouldn't happen as we should only be searching map, which has all nodes.
		System.out.println("Somehow made it through find node without finding the node. Returning Null");
		return null;
	}
	
	public static void printArrList(ArrayList<Node> arr) {
		for(int i = 0; i < arr.size(); i++) {
			arr.get(i).printNode();
		}
	}
	
	public ArrayList<Node> copyArrList(ArrayList<Node> arr){
		ArrayList<Node> copy = new ArrayList<Node>();
		for(int i = 0; i < arr.size(); i++) {
			copy.add(arr.get(i));
		}
		return copy;
		
	}
}
